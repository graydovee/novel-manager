package com.ndovel.ebook.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndovel.ebook.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

@Slf4j
@Component("tokenAuthenticationSuccessHandler")
public class TokenAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper objectMapper;

    private ClientDetailsService clientDetailsService;

    private PasswordEncoder passwordEncoder;

    private AuthorizationServerTokenServices authorizationServerTokenServices;


    public TokenAuthenticationSuccessHandler(ObjectMapper objectMapper, ClientDetailsService clientDetailsService, PasswordEncoder passwordEncoder, AuthorizationServerTokenServices authorizationServerTokenServices) {
        this.objectMapper = objectMapper;
        this.clientDetailsService = clientDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.authorizationServerTokenServices = authorizationServerTokenServices;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Basic ")){
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }

        String[] tokens =  extractAndDecodeHeader(header);
        assert tokens.length == 2;

        String clientId = tokens[0];
        String clientSecret = tokens[1];

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        if(clientDetails == null){
            throw new UnapprovedClientAuthenticationException("ClientId对应的信息不存在");
        } else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException("Client信息不匹配");
        }

        TokenRequest tokenRequest = new TokenRequest(new HashMap<>(),clientId, clientDetails.getScope(), "all");

        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(token));

    }

    private String[] extractAndDecodeHeader(String header) {
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decoded;

        decoded = Base64.getDecoder().decode(base64Token);

        String token = new String(decoded, StandardCharsets.UTF_8);

        int delim = token.indexOf(":");

        if(delim == -1)
            throw new BadCredentialsException("Invalid basic authentication token");

        return new String[] {token.substring(0,delim), token.substring(delim + 1)};
    }
}
