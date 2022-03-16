package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.LoggingController;

@Service
public class LoggingService {
	
	Logger logger = LoggerFactory.getLogger(LoggingService.class);
	
	public String index() {
        logger.trace("LoggingService TRACE Message");
        logger.debug("LoggingService DEBUG Message");
        logger.info("LoggingService INFO Message");
        logger.warn("LoggingService WARN Message");
        logger.error("LoggingService ERROR Message");

        return "Howdy! Check out the Logs to see the output...";
    }

}
