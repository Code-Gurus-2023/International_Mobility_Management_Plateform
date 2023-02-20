package com.gurus.mobility.service;

import com.gurus.mobility.entity.alert.Alert;
import com.gurus.mobility.entity.claim.Response;
import org.springframework.http.ResponseEntity;

public interface IAlertService {
public void createAlert(Alert alert);

}
