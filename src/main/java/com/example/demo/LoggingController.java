package com.example.demo;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.LoggingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class LoggingController {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);
    
    @Autowired
    LoggingService loggingService;
    
    private static ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @RequestMapping("/")
    public String index() throws JsonProcessingException {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
        
        
        
        Map<String, String> user = new HashMap<String, String>();
        user.put("user_id", "87656");
        user.put("SSN", "786445563");
        user.put("address", "22 Street");
        user.put("firstName", "John");
        user.put("lastName", "Doe");
        user.put("Country", "U.S.");
        user.put("ip_address", "192.168.1.1");
        user.put("email_id", "rameez@gmail.com");
        
        String jsonStr = objectMapper.writeValueAsString(user);
        
        logger.info("User JSON: {}", jsonStr);
        
        //loggingService.index();

        return "Howdy! Check out the Logs to see the output...";
    }
}