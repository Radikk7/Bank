package com.example.bank.controller;

import com.example.bank.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ExchangeController {
    @Autowired
    ExchangeService exchangeService;

   @PostMapping("/histogram")
   public String exchange(@RequestParam(name = "period")String period,
                          @RequestParam(name = "currency")String currencyCod, Model model) throws URISyntaxException, IOException, InterruptedException {

       Set<Map.Entry<String, Double>> entrySet = exchangeService.exchangeDiagram(period,currencyCod);
       model.addAttribute("currency",entrySet.stream().map(x-> x.getValue()).collect(Collectors.toList()));
       model.addAttribute("dates",entrySet.stream().map(x-> x.getKey()).collect(Collectors.toList()));
       model.addAttribute("namecurrency",currencyCod);
       return "lk";
   }
   }


