package com.example.bank.controller;


import com.example.bank.dto.Responce;
import com.example.bank.models.Card;
import com.example.bank.models.TransfersHistory;
import com.example.bank.repository.CardRepository;
import com.example.bank.repository.TransfersHistoryRepository;
import com.example.bank.service.CardService;
import com.example.bank.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FilterController {
    @Autowired
    TransfersHistoryRepository transfersHistoryRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    FilterService filterService;
    @Autowired
    CardService cardService;

    @PostMapping("/filterdate")
    public String dateController(@RequestParam("after") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date after,
                               @RequestParam("before") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date before,
                                 @RequestParam(name = "heddenId")Long id, Model model){// фильтр по дате
           List<Responce>responceList= cardService.responceList(filterService.getTransferListBetweenDates(after,before,id));

       model.addAttribute("transfers",responceList);
        model.addAttribute("heddenId",id);
       return "transfershistory";
    }
          @PostMapping("/filtebyrcard") // через хиден передать id  и тут через реквест парам отловить её
          public String cardFilter(@RequestParam(name = "testOrder")String testOrder,// удалил @PathVariable(name = "id")Long id,
                                   @RequestParam(name = "heddenId")Long id, Model model){
             List<Responce>responceList= cardService.responceList(filterService.cardFilter(testOrder,id));
              model.addAttribute("transfers",responceList);
              model.addAttribute("heddenId",id);
             return "transfershistory";
          }
          @PostMapping("/filterbysum")
         public String filterSum(@RequestParam(name = "min") BigDecimal bigDecimal,@RequestParam(name = "max")BigDecimal bigDecimal1,
                                 @RequestParam(name = "heddenId")Long id,Model model){//фильтр по сумме
              List<Responce>responceList= cardService.responceList(filterService.filterBySum(bigDecimal,bigDecimal1,id));

                model.addAttribute("transfers",responceList);
                model.addAttribute("heddenId",id);
              return "transfershistory";
          }


}
