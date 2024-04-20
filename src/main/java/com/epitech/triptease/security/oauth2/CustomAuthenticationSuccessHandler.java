package com.epitech.triptease.security.oauth2;

import com.epitech.triptease.security.TokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;

    @Value("${triptease.oauth2.redirectUri}")
    private String redirectUri;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        super.clearAuthenticationAttributes(request);
    }

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = redirectUri.isEmpty() ?
                determineTargetUrl(request, response, authentication) : redirectUri;

        String token = tokenProvider.generateJwtToken(authentication);
        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setMaxAge(3600);
        tokenCookie.setPath("/");
        response.addCookie(tokenCookie);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
