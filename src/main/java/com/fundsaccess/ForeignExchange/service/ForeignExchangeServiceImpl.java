package com.fundsaccess.ForeignExchange.service;

import com.fundsaccess.ForeignExchange.dao.ForeignExchangeDao;
import com.fundsaccess.ForeignExchange.pojo.AvailableCurrencyDTO;
import com.fundsaccess.ForeignExchange.pojo.ErrorResponseDTO;
import com.fundsaccess.ForeignExchange.pojo.ForeignExchange;
import com.fundsaccess.ForeignExchange.pojo.XMLObjectDTO;
import com.fundsaccess.ForeignExchange.utility.DataParsingUtility;
import com.fundsaccess.ForeignExchange.utility.DateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ForeignExchangeServiceImpl implements ForeignExchangeService {

    @Autowired
    DataParsingUtility dataParsingUtility;

    @Autowired
    ForeignExchangeDao foreignExchangeDao;

    @Autowired
    DateUtility dateUtility;

    /*
    This method gets all available currencies
     */
    @Override
    public AvailableCurrencyDTO getAllCurrencies(ErrorResponseDTO errorResponseDTO) {
        return(dataParsingUtility.getAvailableCurrencies(errorResponseDTO));
    }

    /*
    This method gets all rates for a currency on available dates
     */
    @Override
    public List<ForeignExchange> getAllForeignExchangeRates(String currency, ErrorResponseDTO errorResponseDTO) {
        List<XMLObjectDTO> xmlObjectDTOList= new ArrayList<>();
        List<ForeignExchange> foreignExchangeList= new ArrayList<>();
        foreignExchangeList=foreignExchangeDao.getAllByCurrency(currency);
        if(foreignExchangeList.isEmpty()){
            xmlObjectDTOList=dataParsingUtility.getXmlObjects(currency,errorResponseDTO);
            if(errorResponseDTO.getErrorMessages().isEmpty()){
                return foreignExchangeDao.saveForeignExchange(convertDtoToPojo(currency,xmlObjectDTOList));
            }

        }
        return foreignExchangeList;
    }

    /*
    This method gets the rate for a currency ond a particular date
     */
    @Override
    public ForeignExchange getForeignExchangeRatesByCurrencyAndDate(String currency, String localDate,
                                                                          ErrorResponseDTO errorResponseDTO) {
        List<XMLObjectDTO> xmlObjectDTOList= new ArrayList<>();
        if(foreignExchangeDao.getAllByCurrency(currency).isEmpty()){
            xmlObjectDTOList=dataParsingUtility.getXmlObjects(currency,errorResponseDTO);
            if(errorResponseDTO.getErrorMessages().isEmpty()){
                foreignExchangeDao.saveForeignExchange(convertDtoToPojo(currency,xmlObjectDTOList));
            }
        }
        return foreignExchangeDao.getForeignExchangeByCurrencyAndDate(currency,
                dateUtility.getDateFromString(localDate));
    }

    /*
    This method gets the converted amount in Euros based on a currency, amount and date
     */
    @Override
    public Double getAmountInEuros(String currency,String amount, String localDate, ErrorResponseDTO errorResponseDTO) {
        ForeignExchange foreignExchange = new ForeignExchange();
        foreignExchange=getForeignExchangeRatesByCurrencyAndDate(currency,localDate,errorResponseDTO);
        if(!errorResponseDTO.getErrorMessages().isEmpty()){
            return -1.0;
        }
        return (Double.parseDouble(amount)/Double.parseDouble(foreignExchange.getObsValue()));

    }

    public List<ForeignExchange> convertDtoToPojo(String currency,List<XMLObjectDTO> xmlObjectDTOList){
        List<ForeignExchange> foreignExchangeList= new ArrayList<>();
        xmlObjectDTOList.stream().forEach(xmlObjectDTO -> foreignExchangeList.add
                (new ForeignExchange(currency,xmlObjectDTO.getObsValue(),xmlObjectDTO.getBbkDifference(),
                        xmlObjectDTO.getBbkObsStatus(),dateUtility.getDateFromString(xmlObjectDTO.getTimePeriod()))));
        return foreignExchangeList;
    }
}
