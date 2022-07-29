package com.example.bank.repository;

import com.example.bank.models.Card;
import com.example.bank.models.TransfersHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransfersHistoryRepository extends JpaRepository<TransfersHistory,Long> {
       public List<TransfersHistory> findByCardotpravlenieIdOrCardPoluchenieId(Long id,Long id1);
       public List<TransfersHistory> findByDateAfterAndDateBefore(Date after,Date before);
       public List<TransfersHistory> findAllByCardotpravlenie_Id(Long id);
       public List<TransfersHistory> findByCardPoluchenie_CardNumber(String cardnumber);
       public List<TransfersHistory> findByCardotpravlenie_CardNumber(String cardnumber);
       public List<TransfersHistory> findByCardotpravlenie_CardNumberAndCardPoluchenie_Id(String cardnumber,Long id);
       public List<TransfersHistory> findByCardPoluchenie_CardNumberAndCardotpravlenie_Id(String cardnumber, Long id);
       // public List<TransfersHistory> findBySumBetweenAndCardPoluchenie_Id(BigDecimal bigDecimal,BigDecimal bigDecimal1,Long id);
       public List<TransfersHistory> findBySumBetweenAndCardotpravlenie_Id(BigDecimal bigDecimal,BigDecimal bigDecimal1,Long id);

       public List<TransfersHistory> findByDateAfterAndDateBeforeAndCardotpravlenie_Id(Date date,Date date1,Long id);
       public List<TransfersHistory> findByCardotpravlenie_Id(Long id);
       public List<TransfersHistory> findByCardPoluchenie_Id(Long id);
}
