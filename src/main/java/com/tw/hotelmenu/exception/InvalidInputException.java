package com.tw.hotelmenu.exception;

public class InvalidInputException extends RuntimeException{

    public InvalidInputException() {
        super(" Item name and price should not be empty");
    }
}
