package com.codigomorsa.Tutorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RutasHandler {

    private Logger logger = LoggerFactory.getLogger(RutasHandler.class);

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleNullPointer(NullPointerException exception) {
        logger.error(exception.getMessage());
        return "Internal error. Contact support";
    }


}
