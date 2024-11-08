package ao.com.costs.costswebapi.controller;

import ao.com.costs.costswebapi.domain.User;
import ao.com.costs.costswebapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<User> getUserDetails(Authentication auth) throws Exception{
        if(auth.getPrincipal() != null){
            OAuth2User user = (OAuth2User) auth.getPrincipal();
            String email = user.getAttribute("email");

            User userDetails = this.userService.getUserByEmail(email);
            return ResponseEntity.ok().body(userDetails);

        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
