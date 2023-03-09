package com.gurus.mobility.controller.user;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.gurus.mobility.payload.request.*;
import com.gurus.mobility.payload.response.*;
import com.gurus.mobility.service.User.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.gurus.mobility.exception.TokenRefreshException;
import com.gurus.mobility.entity.user.*;
import com.gurus.mobility.repository.User.RoleRepository;
import com.gurus.mobility.repository.User.UserRepository;
import com.gurus.mobility.security.jwt.JwtUtils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IUserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    EmailService emailService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Long id = userDetails.getId();
        User user = userRepository.findById(id).get();
        if (user.getVerified() == false) {
            ResponseEntity.badRequest().body(new MessageResponse("Your account is desactivated!"));
            System.out.println("Your account is desactivated!");
        } else {
            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

            ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                    .body(new UserInfoResponse(userDetails.getId(),
                            userDetails.getUsername(),
                            userDetails.getEmail(),
                            roles));
        }
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getIdentifiant(), signUpRequest.getUserName(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_ETUDIANT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "prop":
                        Role propRole = roleRepository.findByName(ERole.OWNER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(propRole);

                        break;
                    case "uni":
                        Role uniRole = roleRepository.findByName(ERole.ROLE_UNIVERSITE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(uniRole);

                        break;
                    case "ens":
                        Role ensRole = roleRepository.findByName(ERole.ROLE_ENSEIGNANT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(ensRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_ETUDIANT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userService.addUser(user);
        String verifcode = user.getVerificationCode();
        emailService.sendMail(user.getEmail(), "Verification","This is your verification code:  " +verifcode+"    You're welcome!");
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
        String refreshToken = jwtUtils.getJwtRefreshFromCookies(request);

        if ((refreshToken != null) && (refreshToken.length() > 0)) {
            return refreshTokenService.findByToken(refreshToken)
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);

                        return ResponseEntity.ok()
                                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                                .body(new MessageResponse("Token is refreshed successfully!"));
                    })
                    .orElseThrow(() -> new TokenRefreshException(refreshToken,
                            "Refresh token is not in database!"));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Refresh Token is empty!"));
    }

    @PostMapping("/signout/{userId}")
    public ResponseEntity<?> logoutUser(@PathVariable Long userId) {
        //Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //if (principle.toString() != "anonymousUser") {
        //  Long userId = ((UserDetailsImpl) principle).getId();
        //  refreshTokenService.deleteByUserId(userId);
        //   }
        refreshTokenService.deleteByUserId(userId);
        ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();
        ResponseCookie jwtRefreshCookie = jwtUtils.getCleanJwtRefreshCookie();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }


    @GetMapping("/verify")
    public String verifyUser(@RequestParam String code) {
        return userService.verify(code);

    }

    @PostMapping("/forgot-password/{verif}")
    public void forgotPassword(@RequestBody String email, @PathVariable int verif) throws UnsupportedEncodingException, MessagingException {

        String verifcode = userService.forgotPassword(email);
        if (verif == 0)
            emailService.sendMail(email, "forgot passwrod verification", verifcode);
        else
            sendSMS(verifcode);
    }

    @PutMapping("/reset-password")
    public String resetPassword(@RequestBody ResetPasswordRequest rpr) {
        return userService.resetPassword(rpr.getToken(), rpr.getPassword());
    }

    @GetMapping(value = "/sendSMS")
    public ResponseEntity<String> sendSMS(String msg) {
        var sid = "ACfb68709b4f45aa6b53fd1e70e5772af7";
        var authToken = "de6d5949031f6e205b0e029d8203540a";
        Twilio.init(sid, authToken);

        Message.creator(new PhoneNumber("+216 54 963 533"),
                new PhoneNumber("+12764092941"), msg).create();

        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }
}

