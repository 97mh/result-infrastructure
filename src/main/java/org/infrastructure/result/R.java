package org.infrastructure.result;

import org.infrastructure.result.exception.BusinessCodeException;

/**
 * 响应结果
 */
public interface R {

    static <T> CodedMessage<T> success() {
        return custom(BusinessCodes.SUCCESS);
    }

    static <T> CodedMessage<T> dataSuccess(T data) {
        CodedMessage<T> codedMessage = success();
        codedMessage.setData(data);
        return codedMessage;
    }


    static <T> CodedMessage<T> failure() {
        return custom(BusinessCodes.SERVER_ERROR);
    }

    static <T> CodedMessage<T> failure(String message) {
        return custom(BusinessCodes.SERVER_ERROR.getCode(), message);
    }

    static <T> CodedMessage<T> badRequest(String message) {
        return custom(BusinessCodes.PARAM_ERROR.getCode(), message);
    }

    static <T> CodedMessage<T> failure(BusinessCodeException businessCodeException) {
        return custom(businessCodeException.getCode(), businessCodeException.getMessage());
    }

    static <T> CodedMessage<T> custom(IBusinessCode businessCode) {
        return custom(businessCode.getCode(), businessCode.getMessage());
    }

    static <T> CodedMessage<T> custom(Integer code, String message) {
        // 默认不使用多语言, 找配置条件
        Boolean enableMultiLanguage = SpringContext.enableMultiLanguage;
        if (enableMultiLanguage == null || !enableMultiLanguage) {
            return new CodedMessage<>(code, message);
        }

        // todo 在这里扩展多语言功能
        return new CodedMessage<>(code, message);
    }

}
