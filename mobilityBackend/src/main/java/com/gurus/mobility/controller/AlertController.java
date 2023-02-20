package com.gurus.mobility.controller;

import com.gurus.mobility.entity.alert.Alert;
import com.gurus.mobility.service.IAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Alert")
public class AlertController {
    @Autowired
    private IAlertService iAlertService;

    @PostMapping("/save")
    public void ajouterAlert (@RequestBody Alert alert){
       iAlertService.createAlert(alert);
    }
}
