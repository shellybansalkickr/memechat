package com.kickr.memechat.recharge.codaPayment.entity;


import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ItemInfo implements Serializable {
    private static final long serialVersionUID = 201203021L;
    private String code;
    private String name;
    private double price = 0.0D;
    private short type = 1;

    public ItemInfo() {
    }

    public ItemInfo(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public short getType() {
        return this.type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
