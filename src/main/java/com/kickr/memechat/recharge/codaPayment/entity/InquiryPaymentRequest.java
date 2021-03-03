package com.kickr.memechat.recharge.codaPayment.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InquiryPaymentRequest implements Serializable {
    private static final long serialVersionUID = 201203021L;
    private long txnId;
    private String apiKey;

    public InquiryPaymentRequest() {
    }

    public InquiryPaymentRequest(long txnId, String apiKey) {
        this.txnId = txnId;
        this.setApiKey(apiKey);
    }

    public long getTxnId() {
        return this.txnId;
    }

    public void setTxnId(long txnId) {
        this.txnId = txnId;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
