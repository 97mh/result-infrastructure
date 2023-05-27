package org.infrastructure.result;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class SpringContext {

    public static Boolean enableMultiLanguage;

    public static MessageSource messageSource;

    public static String getLocaleText(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

}
