package com.gurus.mobility.service.AlertServices;

import com.gurus.mobility.entity.alert.Alert;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.repository.AlertRepositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IAlertService {
    public boolean createAlert(Alert alert, Long userId);
    public List<Alert> getAlertsByUser(Long userId);
    public void sendAlerts ();
    public List<Alert> getAllAlerts();
    public void updateAlert(Long id, Alert alert);
    public void deleteAlert(Long alert);
    public boolean getUseridBylertid(User user, long id);

}
