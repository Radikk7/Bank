package com.example.bank.service;

import com.example.bank.models.TransfersHistory;
import com.example.bank.repository.TransfersHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class StatisticService {
    @Autowired
    TransfersHistoryRepository transfersHistoryRepository;
    @Autowired
    CardService cardService;


    public String maxValueByPeriod(Long id, Date after, Date before) {
        String a;
        if (after == null || before == null) {
            return "Incorrect period";
        }

        List<TransfersHistory> transfersHistoryList = getHistoryList(id, after, before);
        if (transfersHistoryList.size() == 0) {
            return "No transactions during this period";

        }
        TransfersHistory transfersHistory = transfersHistoryList.stream().sorted((x, y) -> y.getSum().compareTo(x.getSum())).findFirst().get();
        a = "Max value in your period," + transfersHistory + "!";
        return a;
    }

    public List<TransfersHistory> getHistoryList(Long id, Date after, Date before) {
        return transfersHistoryRepository.findByDateAfterAndDateBeforeAndCardotpravlenie_Id(after, before, id);
    }

    public String postupleniePoOtnosheniyKTratam(Long id) {
        List<TransfersHistory> transfersHistoryList = cardService.historyListFindAllByCardotpravlenie_Id(id);
        List<TransfersHistory> transfersHistoryList1 = cardService.historyListFindByCardPoluchenie_Id(id);
        Double sumOtpravlenie = transfersHistoryList.stream().mapToDouble(x -> x.getSum().doubleValue()).sum();
        Double sumPoluchenie = transfersHistoryList1.stream().mapToDouble(x -> x.getSum().doubleValue()).sum();
        return "The amount of spending is " + sumOtpravlenie + "  " + "The amount received is " + sumPoluchenie;
    }

    //public String averageSpending(@RequestParam(name = "heddenId")Long id,
    // @RequestParam(name = "after")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date after,
    //                                  @RequestParam(name = "before")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date before,
    //                                  Model model){
    //        List<TransfersHistory>transfersHistoryList=transfersHistoryRepository.findByDateAfterAndDateBeforeAndCardotpravlenie_Id(after,before,id);


    public String averageSpending(Long id, Date after, Date before) {
        List<TransfersHistory>transfersHistoryList= getListFindByDateAfterAndDateBeforeAndCardotpravlenie_Id(id,after,before);
        Double a = transfersHistoryList.stream().mapToDouble(x-> x.getSum().doubleValue()).sum();
        double sumspending= a/sumDate(after,before);
        String result = String.format("%.2f",sumspending);
        return "Expenses for the period = " + result;
    }

    public List<TransfersHistory> getListFindByDateAfterAndDateBeforeAndCardotpravlenie_Id(Long id, Date after, Date before) {
        return transfersHistoryRepository.findByDateAfterAndDateBeforeAndCardotpravlenie_Id(after, before, id);
    }

    public Long sumDate(Date after, Date before){
        Long dates = before.getTime() -after.getTime();
        long days = TimeUnit.MILLISECONDS.toDays(dates);
        return days;
    }

}
