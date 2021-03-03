package com.kickr.memechat.recharge.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.kickr.memechat.recharge.entity.RechargeParams;
import com.kickr.memechat.recharge.repository.RechargeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Map;
import java.util.Random;

@Service
public class RechargeService {
    @Autowired
    private RechargeRepo rechargeRepo;

    public Map<String, String> createHash(RechargeParams rechargeParams) throws Exception {
        JavaIntegrationPayU javaIntegrationPayU = new JavaIntegrationPayU();
        Map<String, String> newMap = javaIntegrationPayU.hashCalMethod(rechargeParams);
        saveTransaction(rechargeParams,"REDIRECTED");
        return newMap;
    }

    public void setStatusByPayU(JsonNode jsonNode,JsonNode txnid){
            if(jsonNode.get("txnStatus")!=null && jsonNode.get("txnStatus").asText().equals("CANCEL")){
                rechargeRepo.setStatusByPayU(txnid.asText(),jsonNode.get("txnStatus").asText(),jsonNode.toString(),new Timestamp(System.currentTimeMillis()));
                return;
            }
        rechargeRepo.setStatusByPayU(txnid.asText(),jsonNode.get("txnStatus").asText(),jsonNode.toString(),new Timestamp(System.currentTimeMillis()));

    }

    public String createTxnId(){
        Random rand = new Random();
        String rndm = Integer.toString(rand.nextInt()& Integer.MAX_VALUE) + (System.currentTimeMillis() / 1000L);
        return  rndm;

    }

    public void saveTransaction(RechargeParams rechargeParams,String status){
        rechargeParams.setStatus(status);
        rechargeParams.setTxnTime(new Timestamp(System.currentTimeMillis()));
        rechargeRepo.save(rechargeParams);

    }


}
