package com.fundsaccess.ForeignExchange.pojo;

import com.fundsaccess.ForeignExchange.enums.ErrorEnum;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponseDTO {

    List<String> errorMessages=new ArrayList<>();

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void addErrorMessage(String errorMessage){
        this.errorMessages.add(errorMessage);
    }
    
}
