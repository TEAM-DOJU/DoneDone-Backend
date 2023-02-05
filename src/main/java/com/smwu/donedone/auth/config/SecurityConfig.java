package com.smwu.donedone.auth.config;

import com.smwu.donedone.auth.provider.JwtProvider;
import com.smwu.donedone.auth.security.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final CustomOAuth2UserService oAuth2UserService;

    public SecurityConfig(JwtProvider jwtProvider, final CustomOAuth2UserService oAuth2UserService) {
        this.jwtProvider = jwtProvider;
        this.oAuth2UserService = oAuth2UserService;
    }

    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter())
//                .addFilterBefore(new JwtAuthenticationFilter(http.getSharedObject(AuthenticationManager.class), jwtProvider),
//                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
//                .antMatchers("/api/**").hasRole(Authority.USER.name())
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().permitAll();

//        http.oauth2Login()
//                .userInfoEndpoint().userService(oAuth2UserService);

        return http.build();
    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
