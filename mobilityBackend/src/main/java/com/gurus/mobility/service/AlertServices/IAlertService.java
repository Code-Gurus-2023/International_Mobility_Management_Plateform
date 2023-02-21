package com.gurus.mobility.service.AlertServices;

import com.gurus.mobility.entity.alert.Alert;
import com.gurus.mobility.repository.AlertRepositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface IAlertService {
public void createAlert(Alert alert);

    @Service
    class AlertServiceImpl implements IAlertService {
        @Autowired
        private AlertRepository alertRepository;
        @Override
        public void createAlert(Alert alert) {
            alertRepository.save(alert);
        }
    }
}
