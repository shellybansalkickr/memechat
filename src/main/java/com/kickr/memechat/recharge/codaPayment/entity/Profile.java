package com.kickr.memechat.recharge.codaPayment.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class Profile implements Serializable {
    private static final long serialVersionUID = 201203021L;
    private List<Entry> entry;

    public Profile() {
    }

    public Profile(List<Entry> entry) {
        this.entry = entry;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }
}
