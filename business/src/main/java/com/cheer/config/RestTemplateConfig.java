package com.cheer.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 提供RestTemplate支持
 * 由于使用基于JDK的 {@link org.springframework.http.client.SimpleClientHttpRequestFactory} 进行网络传输会导致
 * 发送带有参数的HTTP Post请求时出现400错误，所以底层使用apache httpclient进行网络传输
 *
 * @author cheer
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(@Qualifier("clientHttpRequestFactory") ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(100)
                .setMaxConnPerRoute(50)
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        // 不设置这个 传大文件会内存溢出
        factory.setBufferRequestBody(false);

        factory.setConnectionRequestTimeout(3000);
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(3000);

        return factory;
    }
}
