package pwr.awt.demo.api.dto;

import lombok.*;
import pwr.awt.demo.domain.user.UserService;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements UserService.Command.CreateUser{
    private String name;
    private String email;
    private String password;

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getName() {
        return name;
    }


}
