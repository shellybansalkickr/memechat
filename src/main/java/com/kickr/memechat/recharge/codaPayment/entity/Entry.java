package com.kickr.memechat.recharge.codaPayment.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Entry implements Serializable{
        private static final long serialVersionUID = 201203021L;
        private String key;
        private String value;

    public Entry() {
    }

    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
