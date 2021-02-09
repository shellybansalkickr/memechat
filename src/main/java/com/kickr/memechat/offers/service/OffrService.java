package com.kickr.memechat.offers.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kickr.memechat.offers.entity.Offers;
import com.kickr.memechat.offers.repository.OffersRepo;
import com.kickr.memechat.recharge.repository.RechargeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OffrService {
    @Autowired
    OffersRepo offersRepo;

    public Map<Integer, Double> getCurrentOffers() throws Exception {
        //fetching offers
        List<Offers> offersList = offersRepo.findAll();
        if (offersList == null || offersList.isEmpty()) {
            throw  new NullPointerException("offer list returns null");
        }
        Double dollarToInr = getDollarToRupees();
        Map<Integer, Double> offerMap = new HashMap<>();
        for (Offers offer : offersList
        ) {
            offerMap.put(offer.getCoinValue(), new Double(offer.getDollarValue() * dollarToInr));
        }
        return offerMap;


    }

    /*
    * uses an open source api to fetch current dollar to rupees rate*/
    public Double getDollarToRupees() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://free.currconv.com/api/v7/convert?q=USD_INR&compact=ultra&apiKey=bd0afe2d97ec9c15d4cc";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseEntity.getBody());
        return root.path("USD_INR").asDouble();

    }
}


