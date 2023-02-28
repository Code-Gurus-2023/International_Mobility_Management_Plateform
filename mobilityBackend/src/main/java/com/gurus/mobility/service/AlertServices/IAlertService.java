package com.gurus.mobility.service.AlertServices;

import com.gurus.mobility.entity.alert.Alert;
import com.gurus.mobility.repository.AlertRepositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IAlertService {
    public void createAlert(Alert alert);
    public List<Alert> getAlertsByUser(int userId);
}
