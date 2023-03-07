package com.gurus.mobility.controller;

import com.gurus.mobility.entity.alert.Alert;
import com.gurus.mobility.entity.user.ERole;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.repository.User.UserRepository;
import com.gurus.mobility.security.jwt.JwtUtils;
import com.gurus.mobility.service.AlertServices.IAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/alert")
public class AlertController {
    public User authorisation(){
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return userRepository.findByUserName(jwtUtils.getUserNameFromJwtToken(token)).get();
    }

    @Autowired
    private HttpServletRequest request;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private IAlertService iAlertService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public ResponseEntity ajouterAlert (@RequestBody Alert alert){
        User user= authorisation();
        if(user == null)
            return new ResponseEntity<>("you should be connected",HttpStatus.FORBIDDEN);
        iAlertService.createAlert(alert, user.getId());
        return new ResponseEntity<>("alert registred successefully", HttpStatus.OK);
    }
    @GetMapping("/alerts")
    public ResponseEntity<List<Alert>> getUserAlerts(){
        User user= authorisation();
        if(user == null)
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(iAlertService.getAllAlerts(),HttpStatus.OK);
    }
    @GetMapping("/myalerts")
    public ResponseEntity<List<Alert>> getAllAlerts(){
        User user= authorisation();
        if(user == null)
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(iAlertService.getAlertsByUser(user.getId()),HttpStatus.OK);
    }

    @DeleteMapping("{idUser}/{idAlert}")
    public ResponseEntity deleteAlert(@PathVariable("idUser")int idUser, @PathVariable("idAlert")int idAlert){
        return null;
    }
}
