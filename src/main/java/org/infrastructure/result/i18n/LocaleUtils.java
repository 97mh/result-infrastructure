package org.infrastructure.result.i18n;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class LocaleUtils {

    public static Boolean enableMultiLanguage;

    public static MessageSource messageSource;

    public static Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    public static String getLocaleText(String key) {
        return messageSource.getMessage(key, null, getLocale());
    }


}
