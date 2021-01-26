package com.fundsaccess.ForeignExchange.pojo;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="FOREIGN_EXCHANGE")
public class ForeignExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    Long id;

    @Column(name="CURRENCY")
    String currency;

    @Column(name="OBS_VALUE")
    String obsValue;

    @Column(name="BBK_DIFF")
    String bbkDifference;

    @Column(name="BBK_OBS_STATUS")
    String bbkObsStatus;

    @Column(name="DATE")
    LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObsValue() {
        return obsValue;
    }

    public void setObsValue(String obsValue) {
        this.obsValue = obsValue;
    }

    public String getBbkDifference() {
        return bbkDifference;
    }

    public void setBbkDifference(String bbkDifference) {
        this.bbkDifference = bbkDifference;
    }

    public String getBbkObsStatus() {
        return bbkObsStatus;
    }

    public void setBbkObsStatus(String bbkObsStatus) {
        this.bbkObsStatus = bbkObsStatus;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ForeignExchange(String currency, String obsValue, String bbkDifference, String bbkObsStatus, LocalDate date) {
        this.currency = currency;
        this.obsValue = obsValue;
        this.bbkDifference = bbkDifference;
        this.bbkObsStatus = bbkObsStatus;
        this.date = date;
    }

    public ForeignExchange() {
    }
}
