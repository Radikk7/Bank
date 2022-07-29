package com.example.bank.repository;

import com.example.bank.models.Card;
import com.example.bank.models.Client;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
    boolean existsClientsByUsername(String username);
    Client findAllByUsername(String username);
    Client findByCardsContains(Card card);
}
