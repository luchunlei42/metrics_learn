package com.chunlei.account.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedAuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.*;

//@Service
public class CustomUserInfoTokenServices implements ResourceServerTokenServices {

    private final String userInfoEndpointUrl;
    private final String clientId;
    private static final String[] PRINCIPAL_KEYS = new String[] { "user", "username",
            "userid", "user_id", "login", "id", "name" };
    @Autowired
    @Qualifier("clientCredentialsRestTemplate")
    private OAuth2RestOperations restTemplate;
    private String tokenType = DefaultOAuth2AccessToken.BEARER_TYPE;
    private AuthoritiesExtractor authoritiesExtractor = new FixedAuthoritiesExtractor();

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        Map<String,Object> map = getMap(this.userInfoEndpointUrl,accessToken);
        if(map.containsKey("error")){
            throw new InvalidTokenException(accessToken);
        }
        return extractAuthentication(map);
    }

    private OAuth2Authentication extractAuthentication(Map<String, Object> map) {
        Object principal = getPrincipal(map);
        OAuth2Request request = getRequest(map);
        List<GrantedAuthority> authorities = this.authoritiesExtractor
                .extractAuthorities(map);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal
                ,"N/A",authorities);
        token.setDetails(map);
        return new OAuth2Authentication(request,token);
    }

    private OAuth2Request getRequest(Map<String, Object> map) {
        Map<String,Object> request = (Map<String, Object>) map.get("oauth2Request");
        String clientId = (String) request.get("clientId");
        Set<String> scope = new LinkedHashSet<>(request.containsKey("scope")?
                (Collection<String>) request.get("scope"): Collections.emptySet());
        return new OAuth2Request(null,clientId,null, true, new HashSet<>(scope),
                null, null, null, null);
    }

    private Object getPrincipal(Map<String, Object> map) {
        for(String key: PRINCIPAL_KEYS){
            if(map.containsKey(key)){
                return map.get(key);
            }
        }
        return "unknown";
    }

    @Override
    public OAuth2AccessToken readAccessToken(String s) {
        throw new UnsupportedOperationException("Not supported: read access token");
    }

    public CustomUserInfoTokenServices(String userInfoEndpointUrl, String clientId){
        this.userInfoEndpointUrl = userInfoEndpointUrl;
        this.clientId = clientId;
    }
    public void setRestTemplate(OAuth2RestOperations restTemplate){
        this.restTemplate = restTemplate;
    }
    private Map<String,Object> getMap(String path, String accessToken){
        try {
            OAuth2RestOperations restTemplate = this.restTemplate;
            if(restTemplate == null){
                BaseOAuth2ProtectedResourceDetails resource = new BaseOAuth2ProtectedResourceDetails();
                resource.setClientId(this.clientId);
                restTemplate = new OAuth2RestTemplate(resource);
            }
            OAuth2AccessToken existingToken = restTemplate.getOAuth2ClientContext()
                    .getAccessToken();
            if(existingToken == null || !accessToken.equals(existingToken.getValue())){
                DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(accessToken);
                token.setTokenType(this.tokenType);
                restTemplate.getOAuth2ClientContext().setAccessToken(token);
            }
            return restTemplate.getForEntity(path, Map.class).getBody();
        } catch (Exception ex) {
            return Collections.<String, Object>singletonMap("error",
                    "Could not fetch user details");
        }
    }
}
