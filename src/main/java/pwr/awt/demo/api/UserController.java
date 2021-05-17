package pwr.awt.demo.api;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pwr.awt.demo.api.dto.UserDTO;
import pwr.awt.demo.api.dto.UserDetailsDTO;
import pwr.awt.demo.api.dto.UserLoginDTO;
import pwr.awt.demo.domain.user.User;
import pwr.awt.demo.domain.user.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Api( tags = "User")
@CrossOrigin("http://loclahost:3000")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public User createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @PostMapping("/signin")
    public String login(@RequestBody UserLoginDTO userDTO){
        return userService.login(userDTO);
    }

    @GetMapping("/me")
    public UserDetailsDTO getUserDetails(){
        return userService.getUserDetails();
    }

}
