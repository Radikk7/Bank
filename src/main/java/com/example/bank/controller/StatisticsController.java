package com.example.bank.controller;

import com.example.bank.models.TransfersHistory;
import com.example.bank.repository.TransfersHistoryRepository;
import com.example.bank.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
public class StatisticsController {
    @Autowired
    TransfersHistoryRepository transfersHistoryRepository;
    @Autowired
    StatisticService statisticService;
    @GetMapping("/statistics/{id}")
    public String statistica(@PathVariable(name = "id")Long id,Model model){
        model.addAttribute("maxvalue","");
        model.addAttribute("heddenId",id);
        return "statistics";
    }

    @PostMapping("/maxvalue")
    public String maxValueByPeriod(@RequestParam(name = "heddenId")Long id, @RequestParam(name = "after",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date after,
                                   @RequestParam(name = "before" ,required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date before ,  Model model){
        String a = statisticService.maxValueByPeriod(id,after,before);
       model.addAttribute("maxvalue",a);
       return "statisticsoutput";
    }


    @PostMapping("/postuplenie")// Отношение поступлений к тратам
    public String postupleniePoOtnosheniyKTratam(@RequestParam(name = "heddenId")Long id,Model model){

        String a = statisticService.postupleniePoOtnosheniyKTratam(id);
        model.addAttribute("admission",a);

         System.out.println(a);
        return "statisticsoutput";
    }
    @PostMapping("tratyperiod")// Средние траты за период
    public String averageSpending(@RequestParam(name = "heddenId")Long id, @RequestParam(name = "after")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date after,
                                  @RequestParam(name = "before")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date before, Model model){
        String result = statisticService.averageSpending(id,after,before);
        model.addAttribute("averageSpending",result);
        return "statisticsoutput";
    }


}
