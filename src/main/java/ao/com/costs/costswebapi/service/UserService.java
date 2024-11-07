package ao.com.costs.costswebapi.service;

import ao.com.costs.costswebapi.domain.User;
import ao.com.costs.costswebapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByEmail(String email) throws Exception{
        return this.userRepository.findByEmail(email).orElseThrow(
                () -> new Exception("We couldn't load your data !")
        );
    }
}
