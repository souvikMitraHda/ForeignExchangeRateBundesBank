package com.fundsaccess.ForeignExchange.repository;

import com.fundsaccess.ForeignExchange.pojo.ForeignExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ForeignExchangeRepository extends JpaRepository<ForeignExchange,Long> {
    List<ForeignExchange> findByCurrency(String currency);
    ForeignExchange findByCurrencyAndDate(String currency, LocalDate localDate);
}
