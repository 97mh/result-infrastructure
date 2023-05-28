package org.infrastructure.result.i18n;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MultiTerminalLocaleChangeInterceptor extends LocaleChangeInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String newLocale = request.getHeader(getParamName());
        if (StringUtils.isBlank(newLocale)) {
            newLocale = request.getParameter(getParamName());
        }

        if (StringUtils.isNotBlank(newLocale)) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver == null) {
                throw new IllegalStateException(
                        "No LocaleResolver found: not in a DispatcherServlet request?");
            }

//            if (newLocale.contains("_")) {
//                newLocale = newLocale.replace("_", "-");
//            }
//            Locale locale = Locale.forLanguageTag(newLocale);
            localeResolver.setLocale(request, response, parseLocaleValue(newLocale));
        }

        return true;
    }

}
