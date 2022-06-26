package com.tw.hotelmenu.exception;

public class InvalidItemFieldsException extends Exception {

    public InvalidItemFieldsException() {
        super("Item name cannot be null and price cannot be less than zero.");
    }
}
