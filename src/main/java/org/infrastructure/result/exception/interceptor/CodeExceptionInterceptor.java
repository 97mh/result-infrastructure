package org.infrastructure.result.exception.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.infrastructure.result.exception.BusinessCodeException;
import org.infrastructure.result.exception.FeignRemoteException;
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

@Order(100)
@RestControllerAdvice
public class CodeExceptionInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeExceptionInterceptor.class);

    @Order(102)
    @ExceptionHandler(BusinessCodeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public CodedMessage<Void> process(HttpServletRequest request, BusinessCodeException exception) {
        String uri = request.getRequestURI();
        String feignRequest = request.getHeader("feign-request");
        // 非feign请求
        if (StringUtils.isBlank(feignRequest) || !feignRequest.equals("true")) {
            LOGGER.warn("An exception occurred while processing the request for {}", uri, exception);
            return R.failure(exception);
        }

        String remoteClient = request.getHeader("remote-client");
        LOGGER.error("An exception occurred when {} [Client] called {}", remoteClient, uri, exception);
        return R.failure(exception);
    }

    @Order(101)
    @ExceptionHandler(FeignRemoteException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public CodedMessage<Void> process(HttpServletRequest request, FeignRemoteException exception) {
        String uri = request.getRequestURI();
        String source = "";
        String remoteClient = request.getHeader("remote-client");
        if (StringUtils.isNotBlank(remoteClient)) {
            source = "source " + remoteClient;
        }

        LOGGER.error("An exception occurred while processing the {} request. Reason: The {} service for {} is error. {}",
                uri, exception.getRemoteAddress(), exception.getServerApplicationName(), source, exception);
        return R.failure(exception);
    }

}
