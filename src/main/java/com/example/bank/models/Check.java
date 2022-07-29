package com.example.bank.models;


import java.math.BigDecimal;
import java.util.Date;

public class Check {
    private Date date;
    private String number;
    private BigDecimal sumOperation;
    private String cardNumber;
    private String cardClient;
    private String nameClient;

    public Check(Date date, String number, BigDecimal sumOperation, String cardNumber, String cardClient, String nameClient) {
        this.date = date;
        this.number = number;
        this.sumOperation = sumOperation;
        this.cardNumber = cardNumber;
        this.cardClient = cardClient;
        this.nameClient = nameClient;
    }

    public Check() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getSumOperation() {
        return sumOperation;
    }

    public void setSumOperation(BigDecimal sumOperation) {
        this.sumOperation = sumOperation;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardClient() {
        return cardClient;
    }

    public void setCardClient(String cardClient) {
        this.cardClient = cardClient;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }
}
