package com.healthy.config;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import static java.util.Collections.emptyList;

import com.auth0.jwt.JWT;

@Component
@Order(1)
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    Logger logger = Logger.getLogger(JwtTokenAuthenticationFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = this.getToken(request);
        logger.info("filter token: " + token);
        if(token==null)
        {
            filterChain.doFilter(request, response);
            return;
        }

        Algorithm algorithm = Algorithm.HMAC256(SecurityTokenConfig.TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(SecurityTokenConfig.TOKEN_ISSUER).build();
        DecodedJWT jwt = verifier.verify(token);

        String email =jwt.getId();
        logger.info("filter email: " + email);
        request.setAttribute("rsEmail", email);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(email, null, emptyList()));
        filterChain.doFilter(request, response);

    }

    private String getToken(HttpServletRequest request) {
        if (request.getParameter(SecurityTokenConfig.TOKEN_NAME) != null) {
            return request.getParameter(SecurityTokenConfig.TOKEN_NAME);
        }
        if (request.getHeader(SecurityTokenConfig.TOKEN_NAME) != null) {
            return request.getHeader(SecurityTokenConfig.TOKEN_NAME);
        }
        if (request.getHeader("Authorization") != null) {
            return request.getHeader("Authorization").replaceAll("Bearer", "").trim();
        }
        if(WebUtils.getCookie(request, SecurityTokenConfig.TOKEN_NAME)!=null) {
            return WebUtils.getCookie(request, SecurityTokenConfig.TOKEN_NAME).getValue();
        }
        return null;
    }

}
