package com.example.demo.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull  HttpServletResponse response,
             @NonNull  FilterChain filterChain)
             throws ServletException, IOException {
final String authHeader = request.getHeader("authorization");
final String jwt;
final String userEmail;
if (authHeader == null || authHeader.startsWith("Bearer ")){
    filterChain.doFilter(request,response);
    return;
}
jwt= authHeader.substring(7);
userEmail = jwtService.extractUsername(jwt)  ; //todo extractthe useremail from token;
    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
    if (jwtService.isTokenValid(jwt,userDetails)) {
        UsernamePasswordAuthenticationToken authenticationToken = new  UsernamePasswordAuthenticationToken(
                userDetails,
                null,userDetails.getAuthorities()
        );
authenticationToken.setDetails(
        new WebAuthenticationDetailsSource().buildDetails(request)

);
SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    }
filterChain.doFilter(request,response);
    }
    }


