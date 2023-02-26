package com.gurus.mobility.controller.user;

import com.gurus.mobility.Util.JwtUtil;
import com.gurus.mobility.dto.UserTokenDto;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.payload.request.LoginRequest;
import com.gurus.mobility.payload.response.MessageResponse;
import com.gurus.mobility.repository.User.UserRepository;
import com.gurus.mobility.service.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class AuthenticateController {

    @Autowired
    private final IUserService userService;
    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthenticateController(IUserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

   /* @PostMapping("/signup")
    public ResponseEntity<MessageResponse> Signup(@Valid @RequestBody User user) throws Exception {
        userService.addUser(user);
        return new ResponseEntity(user,HttpStatus.OK)  ;
    }*/

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> Signup(@Valid  @RequestBody User user) throws Exception {

        if (userRepository.existsByIdentifiant(user.getIdentifiant())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Identifiant is already taken!"));
        }

        if (userRepository.existsByUserName(user.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        userService.addUser(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    @PostMapping("/signin")
    public ResponseEntity<MessageResponse> generateToken(@RequestBody LoginRequest request) throws Exception{
        User user = this.userService.getUserByEmail(request.getEmail());
        if (!user.getActive()) {
            return new ResponseEntity<>(new MessageResponse("Fail -> Account is inactive!"), HttpStatus.FORBIDDEN);
        }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        User userr = userService.getUserByEmail(request.getEmail());
        System.out.println(userr);
        return new ResponseEntity(new UserTokenDto(jwtUtil.generateToken(request.getEmail()),userr),HttpStatus.OK)  ;
    }

}
