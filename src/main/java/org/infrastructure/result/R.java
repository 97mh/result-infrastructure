package org.infrastructure.result;

import org.infrastructure.result.exception.BusinessCodeException;
import org.infrastructure.result.i18n.LocaleUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

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
        // 默认不使用多语言, 直接使用传递进来的语言文本
        Boolean enableMultiLanguage = LocaleUtils.enableMultiLanguage;
        if (enableMultiLanguage == null || !enableMultiLanguage) {
            return new CodedMessage<>(code, message);
        }

        String localeText = LocaleUtils.getLocaleText(code.toString());
        return new CodedMessage<>(code, localeText);
    }

}
