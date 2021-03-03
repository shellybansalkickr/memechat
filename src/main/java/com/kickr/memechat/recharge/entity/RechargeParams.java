package com.kickr.memechat.recharge.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name="transactions")
public class RechargeParams implements Serializable {

    @Id
    @NotNull
    private String txnId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String number;
    private String memeId;
    private Float amount;
    private Integer coins;
    private String status;
    @Column(columnDefinition = "LONGTEXT")
    private String payUMoneyString;
    private String countryName;
    @Column(columnDefinition = "LONGTEXT")
    private String codaPayString;
    @Enumerated(EnumType.STRING)
    private PaymentVia paymentVia;
    public static enum PaymentVia {
        PAYU,CODA;
    }


    public String getPayUMoneyString() {
        return payUMoneyString;
    }

    public void setPayUMoneyString(String payUMoneyString) {
        this.payUMoneyString = payUMoneyString;
    }

    private Timestamp txnTime;

    public RechargeParams() {

    }

    public RechargeParams(String firstName, String lastName, String emailId, String number, String memeId, Float amount, Integer coins,String countryName,PaymentVia paymentVia ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.number = number;
        this.memeId = memeId;
        this.amount = amount;
        this.coins = coins;
        this.countryName=countryName;
        this.paymentVia=paymentVia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMemeId() {
        return memeId;
    }

    public void setMemeId(String memeId) {
        this.memeId = memeId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public Timestamp getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(Timestamp txnTime) {
        this.txnTime = txnTime;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCodaPayString() {
        return codaPayString;
    }

    public void setCodaPayString(String codaPayString) {
        this.codaPayString = codaPayString;
    }

    public PaymentVia getPaymentVia() {
        return paymentVia;
    }

    public void setPaymentVia(PaymentVia paymentVia) {
        this.paymentVia = paymentVia;
    }
}
