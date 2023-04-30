package com.gurus.mobility.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Aspect
@Component
@Slf4j
public class LoggingAspect {


    private static final Logger LOGGER =  LoggerFactory.getLogger(LoggingAspect.class);



    @AfterThrowing(pointcut = "execution(* com.gurus.mobility.*.*(..))", throwing = "ex")
    public void logException(Exception ex) {
        LOGGER.error("Exception thrown: ", ex);
    }



}


