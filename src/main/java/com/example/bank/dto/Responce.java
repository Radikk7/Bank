package com.example.bank.dto;


import com.example.bank.models.Currency;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Responce {
    public Date date;
    public BigDecimal sum;
    public Currency currency;
    public String recipientCard;


    public Responce() {
    }

    public Responce(Date date, BigDecimal sum, Currency currency, String recipientCard) {
        this.date = date;
        this.sum = sum;
        this.currency = currency;
        this.recipientCard = recipientCard;
    }

    public String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'  'HH:mm");
        String a = simpleDateFormat.format(date);
        return a;
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

    public String getRecipientCard() {
        return recipientCard;
    }

    public void setRecipientCard(String recipientCard) {
        this.recipientCard = recipientCard;
    }
}
