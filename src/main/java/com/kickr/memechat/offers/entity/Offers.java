package com.kickr.memechat.offers.entity;


import com.sun.istack.NotNull;

import javax.persistence.*;


@Entity
@Table(name = "offers")
public class Offers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer coinValue;
    @NotNull
    private Integer dollarValue;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCoinValue(Integer coinValue) {
        this.coinValue = coinValue;
    }

    public void setDollarValue(Integer dollarValue) {
        this.dollarValue = dollarValue;
    }

    public Long getId() {
        return id;
    }

    public Integer getCoinValue() {
        return coinValue;
    }

    public Integer getDollarValue() {
        return dollarValue;
    }

    public Offers(Long id, Integer coinValue, Integer dollarValue) {
        this.id = id;
        this.coinValue = coinValue;
        this.dollarValue = dollarValue;
    }

    public Offers() {
    }
}
