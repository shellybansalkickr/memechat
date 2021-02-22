package com.kickr.memechat.recharge.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.kickr.memechat.recharge.entity.RechargeParams;
import com.kickr.memechat.recharge.repository.RechargeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Map;

@Service
public class RechargeService {
    @Autowired
    private RechargeRepo rechargeRepo;

    public Map<String, String> createHash(RechargeParams rechargeParams) throws Exception {
        if (rechargeParams.equals(null)) {
            throw  new NullPointerException("rechargeParams empty");
        }
        JavaIntegrationPayU javaIntegrationPayU = new JavaIntegrationPayU();
        Map<String, String> newMap = javaIntegrationPayU.hashCalMethod(rechargeParams);
        rechargeParams.setStatus("REDIRECTED");
        rechargeParams.setTxnTime(new Timestamp(System.currentTimeMillis()));
        rechargeRepo.save(rechargeParams);
        return newMap;
    }

    public void setStatusByPayU(JsonNode jsonNode,JsonNode txnid){
            if(jsonNode.get("txnStatus")!=null && jsonNode.get("txnStatus").asText().equals("CANCEL")){
                rechargeRepo.setStatusByPayU(txnid.asText(),jsonNode.get("txnStatus").asText(),jsonNode.toString(),new Timestamp(System.currentTimeMillis()));
                return;
            }
        rechargeRepo.setStatusByPayU(txnid.asText(),jsonNode.get("txnStatus").asText(),jsonNode.toString(),new Timestamp(System.currentTimeMillis()));

    }


}
