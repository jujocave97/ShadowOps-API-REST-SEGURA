package com.es.shadowOps.error;


import com.es.shadowOps.error.errores.BadRequestException;
import com.es.shadowOps.error.errores.DuplicatedException;
import com.es.shadowOps.error.errores.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.security.GeneralSecurityException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageForClient handleBadRequest(HttpServletRequest request, Exception e){
        return new ErrorMessageForClient(e.getMessage(),request.getRequestURI());
    }

    @ExceptionHandler({NotFoundException.class, NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageForClient handleNotFound(HttpServletRequest request, Exception e){
        return new ErrorMessageForClient(e.getMessage(),request.getRequestURI());
    }

    @ExceptionHandler({DuplicatedException.class, DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMessageForClient handleDuplicated(HttpServletRequest request, Exception e){
        return new ErrorMessageForClient(e.getMessage(),request.getRequestURI());
    }



    @ExceptionHandler({GeneralSecurityException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessageForClient handleGeneric(HttpServletRequest request, Exception e){
        return new ErrorMessageForClient(e.getMessage(),request.getRequestURI());
    }

}
