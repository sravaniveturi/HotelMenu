package com.tw.hotelmenu.advice;

import com.tw.hotelmenu.exception.InvalidItemFieldsException;
import com.tw.hotelmenu.exception.ItemAlreadyExistsException;
import com.tw.hotelmenu.exception.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> handleItemNotFoundException(ItemNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidItemFieldsException.class)
    public  ResponseEntity<String> handleInvalidItemFieldsException(InvalidItemFieldsException exception){
        return  new ResponseEntity<>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public  ResponseEntity<String> handleItemAlreadyExistsException(ItemAlreadyExistsException exception){
        return  new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
