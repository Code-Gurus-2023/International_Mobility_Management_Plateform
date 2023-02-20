package com.gurus.mobility.service;

import com.gurus.mobility.entity.alert.Alert;
import com.gurus.mobility.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AlertServiceImpl implements IAlertService{
    @Autowired
    private AlertRepository alertRepository;
    @Override
    public void createAlert(Alert alert) {
        alertRepository.save(alert);
    }
}
