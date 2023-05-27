package org.infrastructure.result.autoconfigure;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.infrastructure.result.SpringContext;
import org.infrastructure.result.feign.FeignErrorInterceptor;
import org.infrastructure.result.feign.FeignRequestInterceptor;
import org.infrastructure.result.i18n.RequestLocaleResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
@Import(ExceptionInterceptorImportSelector.class)
public class ResultAutoConfiguration {


    @Bean
    @ConditionalOnProperty(name = "enable.multi.language", havingValue = "true")
    @ConditionalOnMissingBean(LocaleResolver.class)
    public LocaleResolver requestLocaleResolver() {
        return new RequestLocaleResolver();
    }

    @Value("${enable.multi.language}")
    public void setEnableMultiLanguage(Boolean enableMultiLanguage) {
        SpringContext.enableMultiLanguage = enableMultiLanguage;
    }

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
