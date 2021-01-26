package com.fundsaccess.ForeignExchange.service;


import com.fundsaccess.ForeignExchange.pojo.AvailableCurrencyDTO;
import com.fundsaccess.ForeignExchange.pojo.ErrorResponseDTO;
import com.fundsaccess.ForeignExchange.pojo.ForeignExchange;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ForeignExchangeService {
    AvailableCurrencyDTO getAllCurrencies(ErrorResponseDTO errorResponseDTO);

    List<ForeignExchange> getAllForeignExchangeRates(String currency,ErrorResponseDTO errorResponseDTO);

    ForeignExchange getForeignExchangeRatesByCurrencyAndDate(String currency,
                                                                   String localDate,
                                                                   ErrorResponseDTO errorResponseDTO);

    Double getAmountInEuros(String currency, String amount, String localDate, ErrorResponseDTO errorResponseDTO);
}
