package com.tw.hotelmenu.advice;

import com.tw.hotelmenu.exception.InvalidInputException;
import com.tw.hotelmenu.exception.InvalidItemFieldsException;
import com.tw.hotelmenu.exception.ItemNotFoundException;
import com.tw.hotelmenu.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Item> handleItemNotFoundException(ItemNotFoundException exception){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Item> handleInvalidInputException(InvalidInputException exception){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidItemFieldsException.class)
    public  ResponseEntity<Item> handleInvalidItemFieldsException(InvalidItemFieldsException exception){
        return  new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
