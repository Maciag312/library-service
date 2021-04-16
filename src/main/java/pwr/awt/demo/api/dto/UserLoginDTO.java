package pwr.awt.demo.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pwr.awt.demo.domain.user.UserService;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDTO implements UserService.Query.Login {
    private String email;
    private String password;
}
