package com.wm;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        logger.info("advice init ---" );
        dataBinder.setFieldDefaultPrefix("advice");
        dataBinder.addCustomFormatter(new DateFormatter("yyyy/MM/dd"));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity exec() {
        logger.info("advice exec ---" );
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("exec");
    }
}
