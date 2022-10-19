package com.emretaskin.provinceservice.exception;

public class ProvinceAlreadyExistException extends RuntimeException{
    public ProvinceAlreadyExistException(String msg){
        super(msg);
    }
}
