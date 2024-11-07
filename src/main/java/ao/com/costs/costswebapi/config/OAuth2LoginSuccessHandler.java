package ao.com.costs.costswebapi.config;

import ao.com.costs.costswebapi.domain.User;
import ao.com.costs.costswebapi.enums.AuthProvider;
import ao.com.costs.costswebapi.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauth2User = authToken.getPrincipal();

        String email = oauth2User.getAttribute("email") == null ? "":oauth2User.getAttribute("email");
        String username = oauth2User.getAttribute("name");

        // checks if the user logged with Google or GitHub
        String providerId = authToken.getAuthorizedClientRegistrationId().toLowerCase();
        AuthProvider provider;

        if("google".equals(providerId)){
            provider = AuthProvider.GOOGLE;
        }else if("github".equals(providerId)){
            provider = AuthProvider.GITHUB;
        }else{
            throw new IllegalArgumentException("Unknown Provider " + providerId);
        }

        User user = userRepository.findByEmail(email).orElse(null);

        if((user == null) && (provider == AuthProvider.GOOGLE)){
            user = new User();
            user.setEmail(email);
            user.setName(username);
            user.setAuthProvider(provider);
            user.setProjects(List.of());
            user.setGithubUsername(null);
            userRepository.save(user);

        } else if ((user == null) && (provider == AuthProvider.GITHUB)) {
            user = new User();
            user.setEmail(email);
            user.setName(username);
            user.setAuthProvider(provider);
            user.setGithubUsername(oauth2User.getAttribute("login"));
            userRepository.save(user);

        }

        response.sendRedirect("http://localhost:3333/home");
    }
}
