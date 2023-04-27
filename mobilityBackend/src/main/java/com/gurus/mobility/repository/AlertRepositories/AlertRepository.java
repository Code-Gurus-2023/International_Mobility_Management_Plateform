package com.gurus.mobility.repository.AlertRepositories;

import com.gurus.mobility.entity.alert.Alert;
import com.gurus.mobility.entity.alert.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    public List<Alert> findByAlrtTarget(Target tr);
}
