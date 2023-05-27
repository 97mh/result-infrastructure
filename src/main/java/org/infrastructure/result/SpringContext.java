package org.infrastructure.result;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SpringContext {

    public static Boolean enableMultiLanguage;

    public static MessageSource messageSource;

    public static String getLocaleText(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    public static String getHeader(String key) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }

        return requestAttributes.getRequest().getHeader(key);
    }

}
