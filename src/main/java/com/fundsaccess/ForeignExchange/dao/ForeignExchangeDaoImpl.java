package com.fundsaccess.ForeignExchange.dao;

import com.fundsaccess.ForeignExchange.pojo.ForeignExchange;
import com.fundsaccess.ForeignExchange.repository.ForeignExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@Component
public class ForeignExchangeDaoImpl implements ForeignExchangeDao{

    @Autowired
    ForeignExchangeRepository foreignExchangeRepository;


    @Override
    public List<ForeignExchange> getAllByCurrency(String currency) {
        List<ForeignExchange> exchangeStoredList=  foreignExchangeRepository.findByCurrency(currency);
        return exchangeStoredList;
    }

    @Override
    @Transactional
    public List<ForeignExchange> saveForeignExchange(List<ForeignExchange> foreignExchangeList) {
        List<ForeignExchange> savedForeignExchangeList=foreignExchangeRepository.saveAll(foreignExchangeList);
        foreignExchangeRepository.flush();
        return savedForeignExchangeList;
    }

    @Override
    public ForeignExchange getForeignExchangeByCurrencyAndDate(String currency, LocalDate localDate) {
        return foreignExchangeRepository.findByCurrencyAndDate(currency,localDate);
    }
}
