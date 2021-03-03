package com.kickr.memechat.recharge.codaPayment.service;

import com.kickr.memechat.recharge.codaPayment.HashUtils;
import com.kickr.memechat.recharge.codaPayment.CodaRestUtil;
import com.kickr.memechat.recharge.codaPayment.entity.*;
import com.kickr.memechat.recharge.entity.RechargeParams;
import com.kickr.memechat.recharge.repository.RechargeRepo;
import com.kickr.memechat.recharge.service.RechargeService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodaService {
    private static final Logger log = LoggerFactory.getLogger(CodaService.class);
    @Autowired
    private RechargeService rechargeService;

    @Autowired
    private RechargeRepo rechargeRepo;

    public  InitResult initTxn(RechargeParams rechargeParams) throws JSONException {
        rechargeParams.setTxnId(rechargeService.createTxnId());
        InitRequest initReq = new InitRequest();
        JSONObject country = CodaRestUtil.getCountry();
        initReq.setApiKey((String) country.getJSONObject(rechargeParams.getCountryName()).get("apikey"));
        initReq.setOrderId(rechargeParams.getTxnId());
        initReq.setCountry(((Integer) country.getJSONObject(rechargeParams.getCountryName()).get("code")).shortValue());
        initReq.setCurrency(((Integer) country.getJSONObject(rechargeParams.getCountryName()).get("currency")).shortValue());
        ItemInfo itemInfo = new ItemInfo(rechargeParams.getMemeId()+"memeId",rechargeParams.getCoins()+"coins",
                rechargeParams.getAmount());
        ArrayList<ItemInfo> arrayList = new ArrayList();
        arrayList.add(itemInfo);
        initReq.setItems(arrayList);
        HashMap<String,String> profile = new HashMap<>();
        profile.put("user_id",rechargeParams.getMemeId());
        profile.put("need_mno_id","yes");
        initReq.setProfile(profile);
        RestTemplate restTemplate = new RestTemplate();
        String url =CodaRestUtil.getAirtimeURL();
        ResponseEntity<InitResult> result=restTemplate.postForEntity(url+"/init",initReq,InitResult.class);
        if(result.getStatusCode().equals(HttpStatus.OK)){
            rechargeParams.setPayUMoneyString(String.valueOf(result.getBody().getTxnId()));
            rechargeService.saveTransaction(rechargeParams,"REDIRECTED");
        }
        else{
            rechargeService.saveTransaction(rechargeParams,"FAILED");
        }
        return result.getBody();
    }

    public void inquiryTxn(long txnId) throws Exception {

        List<RechargeParams> rechargeParams=rechargeRepo.findByCodaTxnId(String.valueOf(txnId));
        if(rechargeParams==null ||rechargeParams.isEmpty()){
            throw new NullPointerException("cannot find data in db for codaTxnId:"+txnId);
        }
        JSONObject country = CodaRestUtil.getCountry();
        InquiryPaymentRequest request = new InquiryPaymentRequest();
        request.setApiKey((String) country.getJSONObject(rechargeParams.get(0).getCountryName()).get("apikey"));
        request.setTxnId(txnId);
        RestTemplate restTemplate = new RestTemplate();
        String url =CodaRestUtil.getAirtimeURL();
        ResponseEntity<PaymentResult> result =restTemplate.postForEntity(url+"/inquiryPaymentResult",request,PaymentResult.class);
        log.debug(String.valueOf(new StringBuffer("[inquiryTxn] TxnId=").append(txnId).append(", Result=").append(result.getStatusCode())));
        if(result.getStatusCode().equals(HttpStatus.OK)){
            rechargeParams.get(0).setCodaPayString(result.getBody().toString());
            rechargeParams.get(0).setStatus(String.valueOf(result.getBody().getResultCode()));
            rechargeRepo.save(rechargeParams.get(0));
        }
        else{
            throw new Exception("Coda failed for txnId :"+txnId);
        }

    }

    public static boolean validateChecksum (HttpServletRequest request) {
        try {
            String txnId = request.getParameter("TxnId");
            String apiKey = CodaRestUtil.getApiKey(); // Add Merchant APIKey
            String orderId = request.getParameter("OrderId");
            String resultCode = request.getParameter("ResultCode");
            String checksum = request.getParameter("Checksum");

            String values = txnId + apiKey + orderId + resultCode;

            byte[] b = HashUtils.MD5(values);
            String sum = HashUtils.convertToHex(b);

            return sum.equals(checksum);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
