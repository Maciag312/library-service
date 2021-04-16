package pwr.awt.demo.domain.user;

import lombok.*;

import java.util.List;

@Getter
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;
    private String account;
    private List<Role> roles;
    private String name;
}
