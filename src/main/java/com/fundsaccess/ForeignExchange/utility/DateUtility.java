package com.fundsaccess.ForeignExchange.utility;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Component
public class DateUtility {
    public LocalDate getDateFromString(String dateAsString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date=LocalDate.parse(dateAsString,formatter);
        return date;
    }
}
