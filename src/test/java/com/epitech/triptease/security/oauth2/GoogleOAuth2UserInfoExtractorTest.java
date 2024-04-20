package com.epitech.triptease.security.oauth2;

import com.epitech.triptease.security.CustomUserDetails;
import com.epitech.triptease.security.SecurityConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GoogleOAuth2UserInfoExtractorTest {

    @Mock
    private OAuth2User oAuth2User;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testExtractUserInfo_SuccessfulExtraction() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("email", "test@example.com");
        attributes.put("name", "Test User");

        when(oAuth2User.getAttributes()).thenReturn(attributes);

        GoogleOAuth2UserInfoExtractor extractor = new GoogleOAuth2UserInfoExtractor();
        CustomUserDetails userDetails = extractor.extractUserInfo(oAuth2User);

        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("Test User", userDetails.getName());
        assertEquals("test@example.com", userDetails.getEmail());
        assertEquals(OAuth2Provider.GOOGLE, userDetails.getProvider());
        assertEquals(Collections.singletonList(new SimpleGrantedAuthority(SecurityConstants.ROLE_USER)), userDetails.getAuthorities());
    }
}
