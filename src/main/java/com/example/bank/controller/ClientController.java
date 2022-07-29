package com.example.bank.controller;


import com.example.bank.models.Card;
import com.example.bank.models.Client;
import com.example.bank.models.Role;
import com.example.bank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.bank.repository.ClientRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class ClientController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientService clientService;

    @PostMapping("/addclient")
    public String addClient( @RequestParam(name = "surname",required = false)
            String surname, @RequestParam(name = "name",required = false)String name, @RequestParam(name = "patronymic",required = false)String patronymic,
                            @RequestParam(name = "username",required = false)String username, @RequestParam(name = "password",required = false)String password, Model model){

        if (clientService.checkClient(username)){
            String eror = "This login already exists";
            model.addAttribute("eror",eror);
            return "addclient";
        }
        else {
            model.addAttribute("eror","");
        }

        if (surname ==null || name ==null || patronymic ==null || username == null || password==null){

            return "redirect:/";
        }
        clientService.createClient(surname,name,patronymic,username,password);
        return "personalpage";
    }


    @GetMapping("/addclient")
    public String newClient(){
        return "addClient";
    }

 // @GetMapping("/addcard")
 // public String addCard(@AuthenticationPrincipal Client client){
 //     return "addCard";
 // }
    @GetMapping("/login")
    public String login(){
        return "login";
    }



}
