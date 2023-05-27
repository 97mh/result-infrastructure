package org.infrastructure.result.autoconfigure;

import org.infrastructure.result.exception.interceptor.CodeExceptionInterceptor;
import org.infrastructure.result.exception.interceptor.GlobalExceptionInterceptor;
import org.infrastructure.result.exception.interceptor.TokenExpiredExceptionInterceptor;
import org.infrastructure.result.exception.interceptor.ViolationExceptionInterceptor;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class ExceptionInterceptorImportSelector implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{CodeExceptionInterceptor.class.getName(),
                GlobalExceptionInterceptor.class.getName(),
                TokenExpiredExceptionInterceptor.class.getName(),
                ViolationExceptionInterceptor.class.getName()};
    }


}
