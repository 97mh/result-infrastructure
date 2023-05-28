package org.infrastructure.result.autoconfigure;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.infrastructure.result.i18n.LocaleUtils;
import org.infrastructure.result.i18n.MultiTerminalLocaleChangeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;


@Order(100)
@Configuration
@ConditionalOnProperty(name = "enable.multi.language", havingValue = "true")
public class WebSupportAutoConfiguration implements WebMvcConfigurer {

    @Value("${enable.multi.language}")
    public void setEnableMultiLanguage(Boolean enableMultiLanguage) {
        LocaleUtils.enableMultiLanguage = enableMultiLanguage;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        LocaleUtils.messageSource = messageSource;
    }

    @Bean
    @ConditionalOnMissingBean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.CHINA);
        cookieLocaleResolver.setCookieName("locale");
        cookieLocaleResolver.setCookiePath("/");
        // 3天有效
        cookieLocaleResolver.setCookieMaxAge(86400 * 3);
        return cookieLocaleResolver;
    }

    @Bean
    @ConditionalOnMissingBean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        return new MultiTerminalLocaleChangeInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

}
