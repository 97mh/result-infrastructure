package org.infrastructure.result;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

/**
 * 按照Http规范设计的响应码
 * 200 成功
 * 400 参数错误
 * 500 服务器错误
 */
public enum BusinessCodes implements IBusinessCode {

    SUCCESS(HttpStatus.OK.value(), "成功"),

    PARAM_ERROR(HttpStatus.BAD_REQUEST.value(),"参数错误"),

    TOKEN_EXPIRED(4001, "凭证已过期"),

    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),"服务器开小差了"),

    ;

    private final Integer code;

    private final String message;

    BusinessCodes(int code, String message){
        this.code = code;
        this.message = message;
    }

    public static BusinessCodes of(String name) {
        return Arrays.stream(values())
                .filter(c ->  c.name().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }


    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
