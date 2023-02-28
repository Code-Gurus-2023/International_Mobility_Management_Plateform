package com.gurus.mobility.controller;

import com.gurus.mobility.entity.alert.Alert;
import com.gurus.mobility.entity.user.ERole;
import com.gurus.mobility.repository.UserRepository;
import com.gurus.mobility.service.AlertServices.IAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alert/")
public class AlertController {
    @Autowired
    private IAlertService iAlertService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/save")
    public ResponseEntity ajouterAlert (@RequestBody Alert alert){
        iAlertService.createAlert(alert);
        return new ResponseEntity<>("alert registred successefully", HttpStatus.OK);
    }
    @GetMapping("{idUser}")
    public ResponseEntity<List<Alert>> getUserAlerts(@PathVariable("idUser")int idUser){
        return new ResponseEntity<>(iAlertService.getAlertsByUser(idUser),HttpStatus.OK);
    }
    @GetMapping("allalerts/{idUser}")
    public ResponseEntity<List<Alert>> getAllAlerts(@PathVariable("idUser")int idUser){
        if(!userRepository.findById(idUser).get().getRoles().contains(ERole.ROLE_ADMIN))
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(iAlertService.getAlertsByUser(idUser),HttpStatus.OK);
    }

    @DeleteMapping("{idUser}/{idAlert}")
    public ResponseEntity deleteAlert(@PathVariable("idUser")int idUser, @PathVariable("idAlert")int idAlert){
        
    }
}
