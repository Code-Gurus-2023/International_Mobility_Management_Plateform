package com.gurus.mobility.repository.AlertRepositories;

import com.gurus.mobility.entity.alert.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
}
