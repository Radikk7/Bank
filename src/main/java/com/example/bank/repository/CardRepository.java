package com.example.bank.repository;

import com.example.bank.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {
    Card findByCardNumber(String Cardnumber);

}
