package com.kickr.memechat.recharge.codaPayment.entity;

import java.io.Serializable;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InitResult implements Serializable {
    private static final long serialVersionUID = 201203024L;
    private short resultCode = 0;
    private String resultDesc = null;
    private long txnId = 0L;
    private Map<String, String> profile;

    public InitResult() {
    }

    public InitResult(long txnId, short resultCode, String resultDesc, Map<String, String> profile) {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.txnId = txnId;
        this.profile = profile;
    }

    public short getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(short resultCode) {
        this.resultCode = resultCode;
    }

    public long getTxnId() {
        return this.txnId;
    }

    public void setTxnId(long txnId) {
        this.txnId = txnId;
    }

    public Map<String, String> getProfile() {
        return this.profile;
    }

    public void setProfile(Map<String, String> profile) {
        this.profile = profile;
    }

    public String getResultDesc() {
        return this.resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }
}
