package ao.com.costs.costswebapi.service;

import ao.com.costs.costswebapi.domain.User;
import ao.com.costs.costswebapi.enums.AuthProvider;
import ao.com.costs.costswebapi.repository.UserRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public void processGoogleOAuthPostLogin(OAuth2User oAuth2User){
        String email = oAuth2User.getAttribute("email");
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null){
            user = new User();
            user.setEmail(email);
            user.setName(oAuth2User.getName());
            user.setAuthProvider(AuthProvider.GOOGLE);
            user.setProjects(List.of());
            userRepository.save(user);
        }
    }

    @Transactional
    public void processGithubOAuthPostLogin(OAuth2User oAuth2User){
        String email = oAuth2User.getAttribute("email");

        // If the email is not available, it uses GitHub username
        // Since the GitHub username is unique across the platform, it serves as a reliable identifier
        String login = oAuth2User.getAttribute("login");

        if(email == null && login == null){
            throw new IllegalArgumentException("Neither Github email nor Github username is available");
        }

        User user = email!= null ? userRepository.findByEmail(email).orElse(null)
                                 : userRepository.findByGithubUsername(login).orElse(null);

        if(user == null){
            user = new User();

            if(email != null){
                user.setEmail(email);
            }else{
                user.setEmail("");
            }


            user.setName(oAuth2User.getName());
            user.setAuthProvider(AuthProvider.GITHUB);
            user.setProjects(List.of());
            userRepository.save(user);

        }
    }
}
