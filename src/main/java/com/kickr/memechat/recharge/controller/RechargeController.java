package com.kickr.memechat.recharge.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kickr.memechat.recharge.codaPayment.entity.InitResult;
import com.kickr.memechat.recharge.codaPayment.service.CodaService;
import com.kickr.memechat.recharge.entity.RechargeParams;
import com.kickr.memechat.recharge.service.RechargeService;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.ObjectName;
import java.util.HashMap;
import java.util.Map;


@RestController
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    @Autowired
    private CodaService codaService;

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);



    @PostMapping(value="/api/rechargeApi")
    public ResponseEntity<Map<String,String>> rechargeApi(@ModelAttribute RechargeParams data)  {
        Map<String ,String> data1=new HashMap<>();
        try{
            if(data.getPaymentVia()==null ){
                throw new NullPointerException("payment via empty");
            }
            if(data.getPaymentVia().equals(RechargeParams.PaymentVia.CODA)){
                InitResult initResult =codaService.initTxn(data);
                data1.put("txnId",""+initResult.getTxnId());
                return new ResponseEntity<>(data1,HttpStatus.OK);
            }


            data1 =  rechargeService.createHash(data);
            return new ResponseEntity<>(data1, HttpStatus.OK);


        }catch (Exception e){
            logger.error(e.toString());
            return  new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value="/api/setStatus")
    public ResponseEntity<String> setStatusOfPayU(@RequestBody JsonNode payMoneyResponse ){
        if(payMoneyResponse.isEmpty()||payMoneyResponse.get("response").isEmpty()){
            throw  new NullPointerException();
        }

        try{
            rechargeService.setStatusByPayU(payMoneyResponse.get("response").get("response"),payMoneyResponse.get("txnid"));
            return  new ResponseEntity("status set",HttpStatus.OK);
        }
        catch (Exception e){
            logger.error(e.toString());
            return  new ResponseEntity(null,HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping(value = "/api/getStatusFromCoda")
    public ResponseEntity<String> getTransactionStatusFromCoda(@RequestParam Long txnId) {
        try{
            codaService.inquiryTxn(txnId);
            return new ResponseEntity<>("DB status updated",HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(),HttpStatus.BAD_REQUEST);
        }
    }



}

