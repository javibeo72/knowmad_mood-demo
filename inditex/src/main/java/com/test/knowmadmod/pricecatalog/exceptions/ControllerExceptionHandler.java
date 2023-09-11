package com.test.knowmadmod.pricecatalog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	final String ERROR_MESSAGES ="Error Messages";
	final String TYPE_LONG="java.lang.Long";
	final String TYPE_DATE_TIME="java.time.LocalDateTime";
	final String ERROR_DESCRIPTION="Description";
	
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException exception) {
    	
        Map<String, Object> response = new HashMap<>();

        if (exception.hasFieldErrors()) {
        	
        	Map<String, String> errors = new HashMap<>();
            
            exception.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });

            response.put(ERROR_MESSAGES, errors);
        }

        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
  
    } 
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleAllOtherErrors(HttpMessageNotReadableException formatException) {
    	
    	Map<String, Object> response = new HashMap<>();
    	
        String error = formatException.getMessage().toString();
        
        String formatErrorMessage = messageSource.getMessage("validation.parser.error", null, LocaleContextHolder.getLocale());
        
        Map<String, Object> errors = new HashMap<>();
        
        if (!error.isEmpty()) {
        
        	if (error.contains(TYPE_LONG)) {
        		formatErrorMessage = messageSource.getMessage("validation.parser.error.field.numeric", null, LocaleContextHolder.getLocale());
        	} else if (error.contains(TYPE_DATE_TIME)) {
        		formatErrorMessage = messageSource.getMessage("validation.parser.error.field.datetime", null, LocaleContextHolder.getLocale());
        	}
        	
        	errors.put(ERROR_DESCRIPTION, formatErrorMessage);
        	
        }
        
        response.put(ERROR_MESSAGES, errors);
        
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }	
    
}