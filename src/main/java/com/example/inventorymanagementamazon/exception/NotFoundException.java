package com.example.inventorymanagementamazon.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(final String message){
        super(message);
    }

}
