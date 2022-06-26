package com.tw.hotelmenu.exception;


public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException() {
        super("Item is is not present");
    }
}
