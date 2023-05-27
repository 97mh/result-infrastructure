package org.infrastructure.result.exception.interceptor;

import org.infrastructure.result.exception.TokenExpiredException;
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

import javax.servlet.http.HttpServletRequest;

@Order(98)
@RestControllerAdvice
public class TokenExpiredExceptionInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenExpiredExceptionInterceptor.class);

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CodedMessage<Void> process(HttpServletRequest request, TokenExpiredException exception) {
        LOGGER.warn(request.getRequestURI(), exception);
        return R.custom(BusinessCodes.TOKEN_EXPIRED);
    }



}
