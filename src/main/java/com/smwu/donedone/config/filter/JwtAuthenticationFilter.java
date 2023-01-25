package com.smwu.donedone.config.filter;

import com.smwu.donedone.config.processor.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


}
