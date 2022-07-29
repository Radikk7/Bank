package com.example.bank.service;

import com.example.bank.models.Card;
import com.example.bank.models.Client;
import com.example.bank.models.Role;
import com.example.bank.repository.CardRepository;
import com.example.bank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClientService implements UserDetailsService {
        @Autowired
    ClientRepository clientRepository;
        @Autowired
    CardRepository cardRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientRepository.findAllByUsername(username);
    }
    public List<Card> clientPersonal(Client client){
        Long idClient=client.getId();
        List<Card> list= clientRepository.findById(idClient).get().getCards();
        return list;
    }
    public Card aboutClient(Long id){
        Card card = cardRepository.findById(id).get();
        return card;
    }
    public Client checkclienrt(String cardnumber,Long id){
        Card card = cardRepository.findByCardNumber(cardnumber);
        Client client = clientRepository.findByCardsContains(card);
        if (client==null){
            return null;
        }
        return client;
    }


    public boolean checkClient(String username){
       return clientRepository.existsClientsByUsername(username);// есть ли такой клиент сразу проверка
    }
    //@RequestParam(name = "surname",required = false)
    //            String surname, @RequestParam(name = "name",required = false)String name,
    //            @RequestParam(name = "patronymic",required = false)String patronymic,
    //                            @RequestParam(name = "username",required = false)String username,
    //                            @RequestParam(name = "password",required = false)String password,
    public void createClient(String surname,String name,String patronymic,String username,String password){
        Client client1 = new Client(surname,name,patronymic,username,password);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.Admin);
        client1.setRoles(roleSet);
        client1.setActive(true);
        List<Card>cardList = new ArrayList<>();
        client1.setCards(cardList);

        clientRepository.save(client1);
    }


}
