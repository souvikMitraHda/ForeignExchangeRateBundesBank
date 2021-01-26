package com.fundsaccess.ForeignExchange.dao;


import com.fundsaccess.ForeignExchange.pojo.ForeignExchange;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ForeignExchangeDao {

    List<ForeignExchange> getAllByCurrency(String currency);

    List<ForeignExchange> saveForeignExchange(List<ForeignExchange> foreignExchangeList);

    ForeignExchange getForeignExchangeByCurrencyAndDate(String currency, LocalDate localDate);

}
