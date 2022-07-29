package com.example.bank.controller;

import com.example.bank.dto.Responce;
import com.example.bank.models.*;
import com.example.bank.models.Currency;
import com.example.bank.repository.CardRepository;
import com.example.bank.repository.ClientRepository;
import com.example.bank.repository.TransfersHistoryRepository;
import com.example.bank.service.CardService;
import com.example.bank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Controller
public class CardController {
    // @Autowired
    // CardRepository cardRepository;
    @Autowired
    CardService cardService;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TransfersHistoryRepository transfersHistoryRepository;
    @Autowired
    ClientService clientService;


    @PostMapping("/newcard")
    public String newCard(@AuthenticationPrincipal Client client, @RequestParam(name = "name") String name, @RequestParam(name = "cardnumber") String cardnumber,
                          @RequestParam(name = "date") String date, @RequestParam(name = "cvv") String cvv, @RequestParam(name = "balance") BigDecimal balance,
                          @RequestParam(name = "currency") String currency, Model model) {
        cardService.newCard2(name, cardnumber, date, cvv, balance, currency, client);
        return "personalarea";
    }

    @GetMapping("/addcard")
    public String addcard() {
        return "creditcard";
    }


    @GetMapping({"/lk"})
    public String clientPersona1l(@AuthenticationPrincipal Client client, Model model) {
        List<Card> list = clientService.clientPersonal(client);
        model.addAttribute("cardsofclients", list);
        model.addAttribute("clientname", client.getName());
        model.addAttribute("clientsurname", client.getSurname());
        model.addAttribute("clientpatronymic", client.getPatronymic());
        return "lk";
    }


    @GetMapping("/about/{id}")
    public String aboutClient(@PathVariable(name = "id") Long id, Model model) {
        Card card = clientService.aboutClient(id);
        model.addAttribute("balancecard", card.getBalance());
        model.addAttribute("currencycard", card.getCurrency());
        model.addAttribute("cardnumber", card.getCardNumber());
        model.addAttribute("id", id);
        return "sendform";
    }

    @PostMapping("/checkclientdata")
    public String checkclienrt(@RequestParam(name = "cardnumber") String cardnumber,
                               @RequestParam(name = "heddenId") Long id, Model model) {
        Client client = clientService.checkclienrt(cardnumber, id);
        if (client == null) {
            return "redirect:/lk";
        }
        String surname = client.getSurname();
        String name = client.getName();
        String patronymic = client.getPatronymic();
        String fio = surname + " " + name + " " + patronymic;
        Card card1 = cardService.findCardById(id);
        Card card = cardService.findCardByNumber(cardnumber);
        model.addAttribute("clientinfocard", fio);
        model.addAttribute("cardnumber", card.getCardNumber());
        model.addAttribute("id", id);
        model.addAttribute("money", card1.getBalance());
        return "sendmoney";
    }

    @PostMapping("/sumtransfer")
    public String transfersum(@RequestParam(name = "name") String name, @RequestParam(name = "heddenId") Long id,
                              @RequestParam(name = "cardnumber") String cardnumber,
                              @RequestParam(name = "sum") BigDecimal sum) throws IOException {
        cardService.transfersum(name, id, cardnumber, sum);
        return "redirect:/lk";
    }


    @GetMapping("/history/{id}")
    public String cardstatistic(@PathVariable(name = "id") Long id, Model model) {
        List<TransfersHistory> transfersHistoryList = cardService.cardstatistic(id);
        List<Responce> responceList = cardService.responceList(transfersHistoryList);

        Set<String> stringList = cardService.getAllTransfersByCard(id);

        model.addAttribute("cards", stringList);
        model.addAttribute("transfers", responceList);
        model.addAttribute("heddenId", id);
        return "transfershistory";
    }


    @GetMapping("/cardtransfer")
    public String transfersCards() {
        return "";
    }
}


