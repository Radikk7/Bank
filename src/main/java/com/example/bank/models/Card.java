package com.example.bank.models;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.List;


@Data
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Pattern(regexp = "^4[0-9]{12}(?:[0-9]{3})?$")
    private String cardNumber;
    @Pattern(regexp = "/^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$/")
    private String datecard;
    @Pattern(regexp = "^[0-9]{3}$")
    private String csv;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @OneToMany
    private List <TransfersHistory> transfersHistoryList;


    public Card(Long id, String name, String cardNumber, String datecard, String csv, BigDecimal balance, Currency currency) {
        this.id = id;
        this.name = name;
        this.cardNumber = cardNumber;
        this.datecard = datecard;
        this.csv = csv;
        this.balance = balance;
        this.currency = currency;
    }




    public Card(String name, String cardNumber, String datecard, String csv, BigDecimal balance, Currency currency) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.datecard = datecard;
        this.csv = csv;
        this.balance = balance;
        this.currency = currency;
    }

    public Card(String name, String cardNumber, String datecard, String csv) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.datecard = datecard;
        this.csv = csv;
    }

    public Card(String name, String cardNumber, String datecard, String csv, Currency currency) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.datecard = datecard;
        this.csv = csv;
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDatecard() {
        return datecard;
    }

    public void setDatecard(String datecard) {
        this.datecard = datecard;
    }

    public String getCsv() {
        return csv;
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





    public Card() {
    }
    // @Id
   // @GeneratedValue(strategy = GenerationType.AUTO);
   // private Long id;

    public String toString(){
        return id + " " + name + " " + cardNumber + " " + datecard + " " + csv + " " + balance + " "
                + currency ;
    }




}
