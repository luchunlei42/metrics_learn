package com.chunlei.account.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public class OAuth2FeignRequestInterceptor implements RequestInterceptor {
    @Autowired
    private OAuth2RestTemplate clientCredentialsRestTemplate;

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", getAuthorizationToken());
    }

    private String getAuthorizationToken() {
        OAuth2AccessToken accessToken =   clientCredentialsRestTemplate.getAccessToken();
        return String.format("%s %s", accessToken.getTokenType(), accessToken.getValue());
    }
}
