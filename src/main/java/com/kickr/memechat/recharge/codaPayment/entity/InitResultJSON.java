package com.kickr.memechat.recharge.codaPayment.entity;

import java.io.Serializable;

public class InitResultJSON implements Serializable {
    private static final long serialVersionUID = 201203024L;
    private long txnId = 0L;

    public InitResultJSON() {
    }

    public InitResultJSON(long txnId) {
        this.txnId = txnId;
    }

    public long getTxnId() {
        return this.txnId;
    }

    public void setTxnId(long txnId) {
        this.txnId = txnId;
    }
}
