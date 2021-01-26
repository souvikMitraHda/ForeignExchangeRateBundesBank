package com.fundsaccess.ForeignExchange.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fundsaccess.ForeignExchange.enums.ErrorEnum;
import com.fundsaccess.ForeignExchange.pojo.AvailableCurrencyDTO;
import com.fundsaccess.ForeignExchange.pojo.ErrorResponseDTO;
import com.fundsaccess.ForeignExchange.pojo.XMLObjectDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Component
public class DataParsingUtility {

    private final String docLink= "https://www.bundesbank.de/dynamic/action/de/statistiken/zeitreihen-datenbanken/" +
            "zeitreihen-datenbank/759778/759778?listId=www_s331_b01012_3";
    private String foreignExchangeDataUrl="";
    private String tsId="";
    private String csvContent="";

    /*
    This method does the following steps:
        1. gets the HTML of the URL provided
        2. parses the HTML
        3. Finds the available currencies from one of the columns
     */
    public AvailableCurrencyDTO getAvailableCurrencies(ErrorResponseDTO errorResponseDTO){
        AvailableCurrencyDTO availableCurrencyDTO = new AvailableCurrencyDTO();
        try {
            Document document = Jsoup.connect
                    (docLink).get();

            Elements elements=document.getElementsByTag("tr");
            for(Element element:elements){
                if(element.getElementsByTag("td").size()!=0){
                    availableCurrencyDTO.getAvailableCurrency().
                            add((element.getElementsByTag("td").get(1).text().split("=.... ")[1].split(" / ")[0]));
                }


            }
        } catch (IOException e) {
            errorResponseDTO.addErrorMessage(ErrorEnum.IO_EXCEPTION.getErrorDesc());
        }
        return availableCurrencyDTO;
    }

    /*
    This method does the following:
    1. edits the URL to be called based on the currency.
    2. maps data from retrieved XML to list of Objects
     */
    public List<XMLObjectDTO> getXmlObjects(String foreignCurrency,ErrorResponseDTO errorResponseDTO) {
        List<XMLObjectDTO> xmlObjectDTOList = new ArrayList<>();
        XmlMapper xmlMapper= new XmlMapper();
        tsId="tsId=BBEX3.D."+foreignCurrency+".EUR.BB.AC.000";
        foreignExchangeDataUrl="https://www.bundesbank.de/statistic-rmi/StatisticDownload?"+tsId+
                "&its_fileFormat=sdmx&mode=its";
        try {
            csvContent=Jsoup.connect
                    (foreignExchangeDataUrl).get().
                    getElementsByTag("bbk:Series").html();
        } catch (IOException e) {
            errorResponseDTO.addErrorMessage(ErrorEnum.UNKNOWN_CURRENCY.getErrorDesc());
        }
        if(errorResponseDTO.getErrorMessages().isEmpty()){
            String[] xmlRows=csvContent.replace("bbk:Obs","bbk").split("\n");
            Stream.of(xmlRows).filter(element->!element.isEmpty()).forEach(stringToken->
            {
                try {
                    xmlObjectDTOList.add(xmlMapper.readValue(stringToken,XMLObjectDTO.class));
                } catch (JsonProcessingException e) {
                    errorResponseDTO.addErrorMessage(ErrorEnum.JSON_PARSING_EXCEPTION.getErrorDesc());
                }
            });
        }

        return xmlObjectDTOList;
    }
}
