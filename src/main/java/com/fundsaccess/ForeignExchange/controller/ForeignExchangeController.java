package com.fundsaccess.ForeignExchange.controller;


import com.fundsaccess.ForeignExchange.pojo.AvailableCurrencyDTO;
import com.fundsaccess.ForeignExchange.pojo.ErrorResponseDTO;
import com.fundsaccess.ForeignExchange.pojo.ForeignExchange;
import com.fundsaccess.ForeignExchange.service.ForeignExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ForeignExchangeController {

    @Autowired
    ForeignExchangeService foreignExchangeService;

    /*
     get a list of all available currencies
     */
    @GetMapping("/getAllCurrencies")
    ResponseEntity<AvailableCurrencyDTO> getAllCurrencies(){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        AvailableCurrencyDTO availableCurrencyDTO= foreignExchangeService.getAllCurrencies(errorResponseDTO);
        if(!errorResponseDTO.getErrorMessages().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(availableCurrencyDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(availableCurrencyDTO);
    }

    /*
     get all EUR-FX exchange rates at all available dates as a collection.
     Details for the currency once searched for, is saved in the Database, so that
     the next transaction on the same takes lesser time.
     */
    @GetMapping("/getAllExchangeRates/{currency}")
    ResponseEntity<List<ForeignExchange>> getExchangeRatesForAllDates(@PathVariable String currency){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        List<ForeignExchange> foreignExchangeList=foreignExchangeService.getAllForeignExchangeRates(currency,errorResponseDTO);
        if(!errorResponseDTO.getErrorMessages().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(foreignExchangeList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(foreignExchangeList);
    }

    /*
      get the EUR-FX exchange rate at particular day.
      Details for the currency once searched for, is saved in the Database, so that
     the next transaction on the same takes lesser time.
     */
    @GetMapping("/getExchangeRatesForDate/{currency}/{date}")
    ResponseEntity<ForeignExchange> getExchangeRatesForDate(@PathVariable String currency,
                                                                  @PathVariable String date){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        ForeignExchange foreignExchangeList=foreignExchangeService.getForeignExchangeRatesByCurrencyAndDate(currency,date,errorResponseDTO);
        if(!errorResponseDTO.getErrorMessages().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(foreignExchangeList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(foreignExchangeList);
    }

    /*
     get a foreign exchange amount converted to EUR on a particular day
     */
    @GetMapping("/getExchangeRatesForDate/{currency}/{date}/{amount}")
    ResponseEntity<Double> getConvertedAmount(@PathVariable String currency,
                                                             @PathVariable String date,
                                                             @PathVariable String amount){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        Double convertedAmount=foreignExchangeService.getAmountInEuros(currency,amount,date,errorResponseDTO);
        if(!errorResponseDTO.getErrorMessages().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(convertedAmount);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertedAmount);
    }

}
