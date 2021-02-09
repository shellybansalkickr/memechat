package com.kickr.memechat.recharge.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kickr.memechat.recharge.entity.RechargeParams;
import com.kickr.memechat.recharge.service.RechargeService;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);



    @PostMapping(value="/api/rechargeApi")
    public ResponseEntity<Map<String,String>> rechargeApi(@ModelAttribute RechargeParams data)  {

        Map<String ,String> data1;
        try{

            data1 =  rechargeService.createHash(data);
            return new ResponseEntity<>(data1, HttpStatus.OK);


        }catch (Exception e){
            logger.error(e.toString());
            return  new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/api/setStatus")
    public ResponseEntity<String> setStatusOfPayU(@RequestBody String payMoneyResponse ){
        if(payMoneyResponse.isEmpty()){

            throw  new NullPointerException();
        }
        try{
            ObjectMapper objectMapper = new ObjectMapper();
             JsonNode jsonNode = objectMapper.readTree(payMoneyResponse).get("response");

            rechargeService.setStatusByPayU(jsonNode);
            return  new ResponseEntity("status set",HttpStatus.OK);
        }
        catch (Exception e){
            logger.error(e.toString());
            return  new ResponseEntity(null,HttpStatus.BAD_REQUEST);
        }


    }


}

