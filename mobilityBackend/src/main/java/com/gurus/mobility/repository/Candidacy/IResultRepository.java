package com.gurus.mobility.repository.Candidacy;


import com.gurus.mobility.entity.Candidacy.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IResultRepository extends JpaRepository<Result, Integer> {

}