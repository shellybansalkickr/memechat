package com.kickr.memechat.offers.controller;

import com.kickr.memechat.offers.entity.Offers;
import com.kickr.memechat.offers.service.OffrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OffersController {
    @Autowired
    private OffrService offrService;

    private static final Logger logger = LoggerFactory.getLogger(OffersController.class);
    @GetMapping(value="/api/fetchRechargeOffers")
    public ResponseEntity<List<Offers>> getCurrentOffers(){
        try{
            return  new ResponseEntity<>(offrService.getCurrentOffers(), HttpStatus.OK);
        }
        catch (Exception e){
            /*if(!e.getMessage().isEmpty()){
                logger.error(e.getMessage());
            }else{
                logger.error(e.);
            }*/
            logger.error(e.toString());

            return  new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }


    }

}
