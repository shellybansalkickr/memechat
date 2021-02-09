package com.kickr.memechat.recharge.controller;

import com.kickr.memechat.recharge.entity.RechargeParams;
import com.kickr.memechat.recharge.service.TransactionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;

    private static final Logger logger = LoggerFactory.getLogger(TransactionsController.class);

    @GetMapping("/api/fetchTransactionByDate")
    public ResponseEntity<List<RechargeParams>> showAllTransactions(@RequestParam(name="date" ,required = false) String date ){
        try{
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            // you can change format of date
            if(date==null ||date.isEmpty() ){
                date = formatter.format(new Date());
            }
            Date date1 = formatter.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            Date date2 = formatter.parse((formatter.format(calendar.getTime())));

            List list=transactionsService.findByDate(date1,date2);
            return new ResponseEntity<List<RechargeParams>>(list, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
