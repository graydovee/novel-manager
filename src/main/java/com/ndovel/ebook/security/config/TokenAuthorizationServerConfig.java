package com.ndovel.ebook.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TokenAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsServiceImpl;

    private JwtAccessTokenConverter jwtAccessTokenConverter;

    private TokenEnhancer jwtTokenEnhancer;

    private PasswordEncoder passwordEncoder;

    public TokenAuthorizationServerConfig(AuthenticationManager authenticationManager, UserDetailsService userDetailsServiceImpl, JwtAccessTokenConverter jwtAccessTokenConverter, TokenEnhancer jwtTokenEnhancer, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.jwtTokenEnhancer = jwtTokenEnhancer;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {


        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<>();
        enhancers.add(jwtTokenEnhancer);
        enhancers.add(jwtAccessTokenConverter);
        tokenEnhancerChain.setTokenEnhancers(enhancers);

        endpoints.tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsServiceImpl)
                .accessTokenConverter(jwtAccessTokenConverter);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("e-book")
                .secret(passwordEncoder.encode("123456"))//Basic ZS1ib29rOjEyMzQ1Ng==
                .accessTokenValiditySeconds(7200)//有效期7200秒
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("all");
    }
}
