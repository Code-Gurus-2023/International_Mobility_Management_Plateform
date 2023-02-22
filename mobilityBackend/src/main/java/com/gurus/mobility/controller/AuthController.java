package com.gurus.mobility.controller;

import com.gurus.mobility.entity.user.ERole;
import com.gurus.mobility.entity.user.Role;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.payload.request.LoginRequest;
import com.gurus.mobility.payload.request.SignupRequest;
import com.gurus.mobility.payload.response.JwtResponse;
import com.gurus.mobility.payload.response.MessageResponse;
import com.gurus.mobility.repository.RoleRepository;
import com.gurus.mobility.repository.UserRepository;
import com.gurus.mobility.security.jwt.JwtUtils;
import com.gurus.mobility.service.UserDetailsImpl;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getIdentifiant(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.Identifiant(),
                signUpRequest.getUsername(),
               signUpRequest.getEmail(),
              encoder.encode(signUpRequest.getPassword()));

        //String uuid = UUID.randomUUID().toString().substring(0, 10);
       // User user = new User();
       // user.setIdentifiant(uuid);
       // user.setUserName(signUpRequest.getUsername());
        //user.setEmail(signUpRequest.getEmail());
        //user.setPassword(encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                        case "user" -> {
                        Role etudiantRole = roleRepository.findByName(ERole.ROLE_ETUDIANT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        Role universiteRole = roleRepository.findByName(ERole.ROLE_UNIVERSITE)
                                        .orElseThrow(() -> new RuntimeException("Error : Role is not found"));
                        roles.add(etudiantRole);
                        roles.add(universiteRole);
                    }
                    case "prop" -> {
                        Role propRole = roleRepository.findByName(ERole.ROLE_PROPRIETAIRE_LOGEMENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(propRole);
                    }
                    default -> {
                        Role enseignantRole = roleRepository.findByName(ERole.ROLE_ENSEIGNANT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(enseignantRole);
                    }
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
