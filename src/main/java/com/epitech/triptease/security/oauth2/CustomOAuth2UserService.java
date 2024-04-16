package com.epitech.triptease.security.oauth2;

import com.epitech.triptease.entity.User;
import com.epitech.triptease.repository.UserRepository;
import com.epitech.triptease.security.CustomUserDetails;
import com.epitech.triptease.security.SecurityConstants;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    private final List<OAuth2UserInfoExtractor> oAuth2UserInfoExtractors;

    public CustomOAuth2UserService(UserRepository userRepository, List<OAuth2UserInfoExtractor> oAuth2UserInfoExtractors) {
        this.userRepository = userRepository;
        this.oAuth2UserInfoExtractors = oAuth2UserInfoExtractors;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Optional<OAuth2UserInfoExtractor> oAuth2UserInfoExtractorOptional = oAuth2UserInfoExtractors.stream()
                .filter(oAuth2UserInfoExtractor -> oAuth2UserInfoExtractor.accepts(userRequest))
                .findFirst();
        if (oAuth2UserInfoExtractorOptional.isEmpty()) {
            throw new InternalAuthenticationServiceException("The OAuth2 provider is not supported yet");
        }

        CustomUserDetails customUserDetails = oAuth2UserInfoExtractorOptional.get().extractUserInfo(oAuth2User);
        User user = upsertUser(customUserDetails);
        customUserDetails.setId(user.getId());
        return customUserDetails;
    }

    private User upsertUser(CustomUserDetails customUserDetails) {
        Optional<User> userOptional = userRepository.findByUsername(customUserDetails.getUsername());
        User user;
        if (userOptional.isEmpty()) {
            user = new User();
            user.setUsername(customUserDetails.getUsername());
            user.setName(customUserDetails.getName());
            user.setEmail(customUserDetails.getEmail());
            user.setProvider(customUserDetails.getProvider().toString());
            user.setRole(SecurityConstants.ROLE_USER);
        } else {
            user = userOptional.get();
            user.setEmail(customUserDetails.getEmail());
        }
        return userRepository.save(user);
    }
}
