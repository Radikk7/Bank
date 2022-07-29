package com.example.bank.service;


import com.example.bank.dto.Responce;
import com.example.bank.models.TransfersHistory;
import com.example.bank.repository.CardRepository;
import com.example.bank.repository.TransfersHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterService {
    @Autowired
    TransfersHistoryRepository transfersHistoryRepository;
    @Autowired
    CardRepository cardRepository;

    //@RequestParam("after") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date after,
    //                               @RequestParam("before") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date before,
    //                                 @RequestParam(name = "heddenId")Long id, Model model)


    public List<TransfersHistory> getTransferListBetweenDates(Date after,Date before,Long id){
        List<TransfersHistory> historyRepositoryList= transfersHistoryRepository.findByDateAfterAndDateBefore(after,before);
        historyRepositoryList= historyRepositoryList.stream().filter(x-> x.getCardotpravlenie().getId().equals(id)||
                x.getCardPoluchenie().getId().equals(id)).collect(Collectors.toList());

        return historyRepositoryList;
    }

    public List<TransfersHistory> cardFilter(String testOrder,Long id){
        return transfersHistoryRepository.
                findByCardPoluchenie_CardNumberAndCardotpravlenie_Id(testOrder,id);
    }
    //@RequestParam(name = "min") BigDecimal bigDecimal,@RequestParam(name = "max")BigDecimal bigDecimal1,
    //                                 @RequestParam(name = "heddenId")Long id,Model model){//фильтр по сумме

    public List<TransfersHistory> filterBySum(BigDecimal bigDecimal,BigDecimal bigDecimal1,Long id){
        return transfersHistoryRepository.findBySumBetweenAndCardotpravlenie_Id(bigDecimal,bigDecimal1,id);
    }





}
