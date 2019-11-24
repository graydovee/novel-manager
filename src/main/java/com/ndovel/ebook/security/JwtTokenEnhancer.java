package com.ndovel.ebook.security;

import com.ndovel.ebook.model.entity.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Map<String, Object> map = new HashMap<>();

        User u = (User) authentication.getPrincipal();
        map.put("create_time", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(u.getCreateTime()));
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(map);

        return accessToken;
    }
}
