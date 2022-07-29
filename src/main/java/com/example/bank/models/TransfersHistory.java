package com.example.bank.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class TransfersHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private BigDecimal sum;
    private Currency currency;
    @OneToOne
    private Card cardotpravlenie;// карта отправителя
    @OneToOne
    private Card cardPoluchenie; // карта получателя
    private String checkNumber;




    public TransfersHistory() {
    }

    public TransfersHistory(Long id, Date date, BigDecimal sum, Currency currency, Card cardotpravlenie, Card cardPoluchenie) {
        this.id = id;
        this.date = date;
        this.sum = sum;
        this.currency = currency;
        this.cardotpravlenie=cardotpravlenie;
        this.cardPoluchenie = cardPoluchenie;
    }

    public TransfersHistory(Date date, BigDecimal sum, Currency currency, Card cardotpravlenie, Card cardPoluchenie) {
        this.date = date;
        this.sum = sum;
        this.currency = currency;
        this.cardotpravlenie = cardotpravlenie;
        this.cardPoluchenie = cardPoluchenie;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Card getCardotpravlenie() {
        return cardotpravlenie;
    }


    public void setCardotpravlenie(Card cardotpravlenie) {
        this.cardotpravlenie = cardotpravlenie;
    }

    public Card getCardPoluchenie() {
        return cardPoluchenie;
    }

    public void setCardPoluchenie(Card cardPoluchenie) {
        this.cardPoluchenie = cardPoluchenie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

    }
     public String toString(){
        return id + " " + date + " " + sum + " " + currency + " " + cardotpravlenie.getId() + " " + cardPoluchenie.getId();
    }



}
