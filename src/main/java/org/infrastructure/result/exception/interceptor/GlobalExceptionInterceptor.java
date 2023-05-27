package org.infrastructure.result.exception.interceptor;

import org.infrastructure.result.BusinessCodes;
import org.infrastructure.result.CodedMessage;
import org.infrastructure.result.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Order
@RestControllerAdvice
public class GlobalExceptionInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionInterceptor.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public CodedMessage<Void> processThrowable(HttpServletRequest request, Throwable throwable) throws Throwable {
        if (throwable instanceof ServletException) {
            throw throwable;
        }

        LOGGER.error("An unknown exception occurred while processing the request for {}",
                request.getRequestURI(), throwable);
        return R.custom(BusinessCodes.SERVER_ERROR);
    }

}
