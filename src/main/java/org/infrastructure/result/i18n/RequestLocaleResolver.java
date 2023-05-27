package org.infrastructure.result.i18n;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

public class RequestLocaleResolver implements LocaleResolver {

    public static final String PARAM = "locale";

    private static final Map<String, Locale> localeMap = Maps.newConcurrentMap();


    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        // 优先从参数取, 没有的话在尝试一下请求头
        // 格式: zh_CN
        String tag = httpServletRequest.getParameter(PARAM);
        if (StringUtils.isBlank(tag)) {
            tag = httpServletRequest.getHeader(PARAM);
        }

        if (StringUtils.isBlank(tag)) {
            return Locale.getDefault();
        }

        Locale locale = localeMap.get(tag);
        if (locale == null) {
            locale = Locale.forLanguageTag(tag.replace("_", "-"));
            localeMap.putIfAbsent(tag, locale);
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse, Locale locale) {

    }

}
