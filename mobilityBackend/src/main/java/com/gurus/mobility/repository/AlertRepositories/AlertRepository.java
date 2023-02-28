package com.gurus.mobility.repository.AlertRepositories;

import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.entity.alert.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

}
