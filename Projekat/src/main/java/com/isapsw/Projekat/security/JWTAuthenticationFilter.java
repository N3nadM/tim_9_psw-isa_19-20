package com.isapsw.Projekat.security;

import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.isapsw.Projekat.security.Konstante.HEADER_BEARER_TOKEN;
import static com.isapsw.Projekat.security.Konstante.TOKEN_BEARER_PREFIX;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authToken = getJWTFromRequest(httpServletRequest);

        if(StringUtils.hasText(authToken)&& tokenProvider.validate(authToken)) {
            String username = tokenProvider.getUserUsernameFromJWT(authToken);
            UserDetails korisnikDetails = customUserDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(korisnikDetails, null, Collections.emptyList());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getJWTFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(HEADER_BEARER_TOKEN);

        if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith(TOKEN_BEARER_PREFIX)){
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }
}
