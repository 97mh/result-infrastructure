package org.infrastructure.result.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.infrastructure.result.i18n.LocaleUtils;
import org.springframework.beans.factory.annotation.Value;

public class FeignRequestInterceptor implements RequestInterceptor {

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void apply(RequestTemplate template) {
        template.header("feign-request", "true");
        template.header("remote-client", applicationName);
        template.header("locale", LocaleUtils.getLocale().toString());
    }

}
