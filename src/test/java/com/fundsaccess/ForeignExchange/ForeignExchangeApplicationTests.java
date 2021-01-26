package com.fundsaccess.ForeignExchange;

import com.fundsaccess.ForeignExchange.controller.ForeignExchangeController;
import com.fundsaccess.ForeignExchange.pojo.AvailableCurrencyDTO;
import com.fundsaccess.ForeignExchange.pojo.ErrorResponseDTO;
import com.fundsaccess.ForeignExchange.pojo.ForeignExchange;
import com.fundsaccess.ForeignExchange.service.ForeignExchangeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ForeignExchangeApplicationTests {

	@Autowired
	private ForeignExchangeController foreignExchangeController;

	@Autowired
	private ForeignExchangeService foreignExchangeService;

	@Test
	void contextLoads() throws Exception{
		assertThat(foreignExchangeController).isNotNull();
	}

	@Test
	void testGetAllCurrencies(){
		AvailableCurrencyDTO availableCurrencyDTO= new AvailableCurrencyDTO();
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		availableCurrencyDTO=foreignExchangeService.getAllCurrencies(errorResponseDTO);
		assertThat(!availableCurrencyDTO.getAvailableCurrency().isEmpty());
	}

	@Test
	void testGetAllForeignExchangeRates(){
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		String currency="AUD";
		List<ForeignExchange> foreignExchangeList =
				foreignExchangeService.getAllForeignExchangeRates(currency,errorResponseDTO);
		assertThat(!foreignExchangeList.isEmpty());
	}

	@Test
	void testGetForeignExchangeRatesByCurrencyAndDate(){
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		String currency="AUD";
		String localDate="2021-01-25";
		ForeignExchange foreignExchange= foreignExchangeService.getForeignExchangeRatesByCurrencyAndDate
				(currency,localDate,errorResponseDTO);
		assertThat(foreignExchange).isNotNull();
	}

	@Test
	void testGetAmountInEuros(){
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		String currency="AUD";
		String localDate="2021-01-25";
		String amount="10000";
		Double amountInEuros=foreignExchangeService.getAmountInEuros
				(currency,amount,localDate,errorResponseDTO);
		assertThat(amountInEuros.equals(112.8286133363421));
	}


}
