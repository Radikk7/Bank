package com.example.bank.service;


import com.example.bank.dto.Responce;
import com.example.bank.models.*;
import com.example.bank.models.Currency;
import com.example.bank.repository.CardRepository;
import com.example.bank.repository.ClientRepository;
import com.example.bank.repository.TransfersHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TransfersHistoryRepository transfersHistoryRepository;
    @Value("${upload.path}")
    private String upload;


    public void file(Check check) throws IOException { //Создание чеков
        File file = new File(upload+ "/" + UUID.randomUUID());


       // multipartFile.transferTo(new File(upload + "/" + resultFileName));// сохраняет все на комп
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        bufferedWriter.write("Date of operation" + " " + check.getDate());
        bufferedWriter.newLine();
        bufferedWriter.write("Number of operation" + " " + check.getNumber());
        bufferedWriter.newLine();
        bufferedWriter.write("Sum of operation" + " " + check.getSumOperation());
        bufferedWriter.newLine();
        bufferedWriter.write("Your card number" + " " + check.getCardNumber());
        bufferedWriter.newLine();
        bufferedWriter.write("Client card number" + " " + check.getCardClient());
        bufferedWriter.newLine();
        bufferedWriter.write("Name client" + " " + check.getNameClient());
        bufferedWriter.flush();//записывает

    }

    public void newCard2(String name, String cardnumber, String date, String cvv, BigDecimal balance, String currency, Client client) {
        Card card = new Card(name, cardnumber, date, cvv, balance, Currency.valueOf(currency));
        List<Card> cardList = client.getCards();
        cardList.add(card);
        cardRepository.save(card);
        client.setCards(cardList);
        clientRepository.save(client);
    }

    public Card findCardById(Long id) {
        return cardRepository.findById(id).orElse(null);

    }

    public Card findCardByNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);

    }

    public void transfersum(String name, Long id, String cardnumber, BigDecimal sum) throws IOException {
        Card cardSend = cardRepository.findByCardNumber(cardnumber);// карта на которую деньги зачисляются
        Card cardWith = cardRepository.findById(id).orElse(null); // карта с которой деньги списываются
        String number = UUID.randomUUID().toString();
        number = number.substring(0, number.length() / 2);
        assert cardWith != null;
        if (cardWith.getBalance().compareTo(sum) >= 0) {
            Date date = new Date();

            TransfersHistory transfersHistory = new TransfersHistory(date, sum, cardWith.getCurrency(), cardWith, cardSend);
            transfersHistory.setCheckNumber(number);
            transfersHistoryRepository.save(transfersHistory);

            BigDecimal card1balance = cardWith.getBalance();


            BigDecimal sumCard1 = card1balance.subtract(sum);
            cardWith.setBalance(sumCard1);
            cardRepository.save(cardWith);


            List<TransfersHistory> transfersHistoryListSendCard = cardSend.getTransfersHistoryList();
            transfersHistoryListSendCard.add(transfersHistory);
            cardSend.setTransfersHistoryList(transfersHistoryListSendCard);

            BigDecimal balance = cardSend.getBalance();
            balance = balance.add(sum);
            cardSend.setBalance(balance);
            cardRepository.save(cardSend);

            Client client = clientRepository.findByCardsContains(cardSend);
            Check check = new Check(date, number, sum, cardWith.getCardNumber(), cardSend.getCardNumber(), client.getName());
            file(check);
        }
    }

    public List<TransfersHistory> cardstatistic(Long id) {
        return transfersHistoryRepository.findByCardotpravlenieIdOrCardPoluchenieId(id, id);
    }

    public List<Responce> responceList(List<TransfersHistory> transfersHistoryList) {
        List<Responce> responceList = new ArrayList<>();
        for (TransfersHistory i : transfersHistoryList) {
            Responce responce = new Responce();
            responce.setDate(i.getDate());
            responce.setSum(i.getSum());
            responce.setCurrency(i.getCurrency());
            responce.setRecipientCard(i.getCardPoluchenie().getCardNumber());
            responceList.add(responce);
        }
        return responceList;
    }

    public Set<String> getAllTransfersByCard(Long id) {
        List<TransfersHistory> list = transfersHistoryRepository.findAllByCardotpravlenie_Id(id);
        Set<String> stringList = new HashSet<>();
        for (TransfersHistory i : list) {
            String cardName = i.getCardPoluchenie().getCardNumber();
            stringList.add(cardName);
        }
        return stringList;
    }

    public List<TransfersHistory> historyListFindAllByCardotpravlenie_Id(Long id){
        return transfersHistoryRepository.findByCardotpravlenie_Id(id);
    }
    public List<TransfersHistory> historyListFindByCardPoluchenie_Id(Long id){
      return transfersHistoryRepository.findByCardPoluchenie_Id(id);
    }

}
