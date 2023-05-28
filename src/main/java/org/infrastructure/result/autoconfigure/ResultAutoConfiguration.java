package org.infrastructure.result.autoconfigure;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.infrastructure.result.feign.FeignErrorInterceptor;
import org.infrastructure.result.feign.FeignRequestInterceptor;
import org.infrastructure.result.i18n.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

@Configuration
@Import(ExceptionInterceptorImportSelector.class)
public class ResultAutoConfiguration {

    @Bean
    public RequestInterceptor innerRequestInterceptor() {
        return new FeignRequestInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean(ErrorDecoder.class)
    public ErrorDecoder customErrorDecoder() {
        return new FeignErrorInterceptor();
    }

}
