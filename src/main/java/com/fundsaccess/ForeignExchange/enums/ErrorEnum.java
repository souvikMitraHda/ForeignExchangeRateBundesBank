package com.fundsaccess.ForeignExchange.enums;

public enum ErrorEnum {
    IO_EXCEPTION("ERROR::Invalid URL."),
    JSON_PARSING_EXCEPTION("ERROR::Could not parse XML."),
    UNKNOWN_CURRENCY("ERROR::Unknown Currency");




    private String errorDesc;

    public String getErrorDesc() {
        return this.errorDesc;
    }

    private ErrorEnum(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
