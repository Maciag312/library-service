package pwr.awt.demo.infrastructure.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import pwr.awt.demo.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="MYUSER")
public class UserTuple {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name="user_seq", sequenceName="user_seq", allocationSize=1)

    private Long id;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String account;
    private String name;
    @ElementCollection(fetch = FetchType.EAGER)
    private  List<RoleTuple> roles;


    static UserTuple from(User user){
        return new UserTuple(user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getAccount(),
                user.getName(),
                user.getRoles()==null?new ArrayList<>() :
                        user.getRoles().stream().map(RoleTuple::from).collect(Collectors.toList())
                );
    }
    User toDomain(){
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .account(account)
                .name(name)
                .roles(roles==null?new ArrayList<>():roles.stream().map(RoleTuple::toDomain).collect(Collectors.toList()))
                .build();
    }
}
