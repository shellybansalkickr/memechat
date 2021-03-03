package com.kickr.memechat.recharge.service;

import com.kickr.memechat.recharge.entity.RechargeParams;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletException;

public class JavaIntegrationPayU {

    private Integer error;

    public boolean empty(String s) {
        if (s == null || s.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public String hashCal(String type, String str) {
        byte[] hashseq = str.getBytes();
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest algorithm = MessageDigest.getInstance(type);
            algorithm.reset();
            algorithm.update(hashseq);
            byte messageDigest[] = algorithm.digest();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }

        } catch (NoSuchAlgorithmException nsae) {
        }
        return hexString.toString();
    }

    protected Map<String, String> hashCalMethod(RechargeParams request)
            throws ServletException, IOException {
		String key = "3fL6qLzg";
        String salt = "7ivQ6cLHtj";
        error = 0;
        String hashString = "";
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String> urlParams = new HashMap<String, String>();
        params.put("firstname",request.getFirstName());
        params.put("lastname",request.getLastName());
        params.put("email",request.getEmailId());
        params.put("amount",request.getAmount().toString());
        params.put("productinfo",request.getCoins().toString()+"coins");
        params.put("phone",request.getNumber());
        params.put("surl","/about");
        params.put("furl","/about");
        params.put("key",key);

        String txnid = "";
        if (empty(params.get("txnid"))) {
            Random rand = new Random();
            String rndm = Integer.toString(rand.nextInt()) + (System.currentTimeMillis() / 1000L);
            txnid = rndm;
            params.remove("txnid");
            params.put("txnid", txnid);
            txnid = hashCal("SHA-256", rndm).substring(0, 20);
        } else {
            txnid = params.get("txnid");
        }
        request.setTxnId(txnid);
        String hash = "";
        String otherPostParamSeq = "phone|surl|furl|lastname|curl|address1|address2|city|state|country|zipcode|pg";
        String hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
        if (empty(params.get("hash")) && params.size() > 0) {
            if (empty(params.get("key")) || empty(txnid) || empty(params.get("amount")) || empty(params.get("firstname")) || empty(params.get("email")) || empty(params.get("phone")) || empty(params.get("productinfo")) || empty(params.get("surl")) || empty(params.get("furl")) ) {
                error = 1;
            } else {
                
                String[] hashVarSeq = hashSequence.split("\\|");
                for (String part : hashVarSeq) {
                    if (part.equals("txnid")) {
                        hashString = hashString + txnid;
                        urlParams.put("txnid", txnid);
                    } else {
                        hashString = (empty(params.get(part))) ? hashString.concat("") : hashString.concat(params.get(part).trim());
                        urlParams.put(part, empty(params.get(part)) ? "" : params.get(part).trim());
                    }
                    hashString = hashString.concat("|");
                }
                hashString = hashString.concat(salt);
                hash = hashCal("SHA-512", hashString);
                String[] otherPostParamVarSeq = otherPostParamSeq.split("\\|");
                for (String part : otherPostParamVarSeq) {
                    urlParams.put(part, empty(params.get(part)) ? "" : params.get(part).trim());
                }

            }
        } else if (!empty(params.get("hash"))) {
            hash = params.get("hash");
        }

        urlParams.put("hash", hash);

        urlParams.put("hashString", hashString);
        return urlParams;
    }



}
