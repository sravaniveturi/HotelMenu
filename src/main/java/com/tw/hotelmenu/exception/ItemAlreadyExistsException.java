package com.tw.hotelmenu.exception;

public class ItemAlreadyExistsException extends Exception {

    public ItemAlreadyExistsException() {
        super("Item already exists in repository");
    }
}
