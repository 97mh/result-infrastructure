package org.infrastructure.result.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.infrastructure.result.SpringContext;
import org.infrastructure.result.i18n.RequestLocaleResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Locale;

public class FeignRequestInterceptor implements RequestInterceptor {

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void apply(RequestTemplate template) {
        template.header("feign-request", "true");
        template.header("remote-client", applicationName);
        template.header("locale", SpringContext.getHeader(RequestLocaleResolver.PARAM));
    }

}
