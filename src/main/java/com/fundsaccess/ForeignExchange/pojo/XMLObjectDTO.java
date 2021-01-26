package com.fundsaccess.ForeignExchange.pojo;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
public class XMLObjectDTO {

    @JacksonXmlProperty(localName = "TIME_PERIOD")
    String timePeriod;

    @JacksonXmlProperty(localName = "OBS_VALUE")
    String obsValue;

    @JacksonXmlProperty(localName = "BBK_DIFF")
    String bbkDifference;

    @JacksonXmlProperty(localName = "BBK_OBS_STATUS")
    String bbkObsStatus;

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
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
}
