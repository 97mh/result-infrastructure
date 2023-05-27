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

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

@Order(99)
@RestControllerAdvice
public class ViolationExceptionInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViolationExceptionInterceptor.class);

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public CodedMessage<Void> process(HttpServletRequest request, ValidationException e) {
        LOGGER.warn("Validation parameters failed when processing {} request", request.getRequestURI(), e);
        return R.custom(BusinessCodes.PARAM_ERROR);
    }

}
