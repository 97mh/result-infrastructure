package org.infrastructure.result.exception;

import org.infrastructure.result.IBusinessCode;

public class BusinessCodeException extends RuntimeException {

    protected Integer code;

    public BusinessCodeException(IBusinessCode businessCode) {
        super(businessCode.getMessage());
        this.code = businessCode.getCode();
    }

    public BusinessCodeException(Integer code, String reason) {
        super(reason);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }


}
