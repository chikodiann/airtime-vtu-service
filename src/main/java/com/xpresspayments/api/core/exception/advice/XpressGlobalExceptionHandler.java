package com.xpresspayments.api.core.exception.advice;

import com.xpresspayments.api.core.exception.AirtimePurchaseException;
import com.xpresspayments.api.core.exception.GenericException;
import com.xpresspayments.api.core.exception.InvalidCredentialsException;
import com.xpresspayments.api.core.exception.InvalidRequestException;
import com.xpresspayments.api.model.dto.base.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class XpressGlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public BaseResponse<?> invalidCredentialsException(InvalidCredentialsException exception) {
        return BaseResponse.builder().responseCode(HttpStatus.BAD_REQUEST.value())
                .responseMessage(exception.getMessage())
                .payload(null)
                .build();
    }

    @ExceptionHandler(InvalidRequestException.class)
    public BaseResponse<?> invalidRequestException(InvalidRequestException exception) {
        return BaseResponse.builder().responseCode(HttpStatus.BAD_REQUEST.value())
                .responseMessage(exception.getMessage())
                .payload(null)
                .build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public BaseResponse<?> usernameNotFoundException(UsernameNotFoundException exception) {
        return BaseResponse.builder().responseCode(HttpStatus.BAD_REQUEST.value())
                .responseMessage(exception.getMessage())
                .payload(null)
                .build();
    }

    @ExceptionHandler(GenericException.class)
    public BaseResponse<?> genericException(GenericException exception) {
        return BaseResponse.builder().responseCode(HttpStatus.BAD_REQUEST.value())
                .responseMessage(exception.getMessage())
                .payload(null)
                .build();
    }

    @ExceptionHandler(AirtimePurchaseException.class)
    public BaseResponse<?> airtimePurchaseException(AirtimePurchaseException exception) {
        return BaseResponse.builder().responseCode(HttpStatus.BAD_REQUEST.value())
                .responseMessage(exception.getMessage())
                .payload(null)
                .build();
    }
}
