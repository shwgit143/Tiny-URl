package com.example25.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Urls NOT Found")
public class UrlNotFoundException  extends NullPointerException{

    public UrlNotFoundException(String s){
        super(s);
    }
}
