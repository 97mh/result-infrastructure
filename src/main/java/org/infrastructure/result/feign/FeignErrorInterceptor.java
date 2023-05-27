package org.infrastructure.result.feign;

import com.alibaba.fastjson.JSON;
import com.google.common.io.CharStreams;
import feign.Request;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.infrastructure.result.exception.FeignRemoteException;
import org.infrastructure.result.CodedMessage;
import org.springframework.cloud.openfeign.FeignClient;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class FeignErrorInterceptor implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        Reader reader;
        String bodyStr;
        try {
            reader = response.body().asReader(StandardCharsets.UTF_8);
            bodyStr = CharStreams.toString(reader);
        } catch (IOException e) {
            return new ErrorDecoder.Default().decode(methodKey, response);
        }

        Request request = response.request();
        FeignClient feignClient = request.requestTemplate().methodMetadata()
                .targetType().getAnnotation(FeignClient.class);
        String name = Optional.ofNullable(feignClient.name()).orElse(feignClient.value());
        String url = request.url();

        CodedMessage codedMessage = JSON.parseObject(bodyStr, CodedMessage.class);
        throw new FeignRemoteException(codedMessage.getCode(), codedMessage.getMessage(), name, url);
    }


}
