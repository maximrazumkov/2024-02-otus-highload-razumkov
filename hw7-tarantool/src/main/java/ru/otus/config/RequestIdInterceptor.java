package ru.otus.config;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RequestIdInterceptor implements ClientHttpRequestInterceptor {
    private static final String REQUEST_ID_HEADER = "X-Request-ID";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        String requestId = MDC.get(REQUEST_ID_HEADER);
        if (requestId != null) {
            request.getHeaders().set(REQUEST_ID_HEADER, requestId);
        }
        return execution.execute(request, body);
    }
}
