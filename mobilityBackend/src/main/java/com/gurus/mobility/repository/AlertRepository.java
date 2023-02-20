package com.gurus.mobility.repository;

import com.gurus.mobility.entity.alert.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
}
