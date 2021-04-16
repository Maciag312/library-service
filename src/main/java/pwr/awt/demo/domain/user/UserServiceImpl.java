package pwr.awt.demo.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pwr.awt.demo.api.dto.UserDetailsDTO;
import pwr.awt.demo.infrastructure.security.jwt.JwtTokenProvider;

import java.util.LinkedList;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public User createUser(UserService.Command.CreateUser createUserCommand) {
        if(userRepository.findByEmail(createUserCommand.getEmail()).isPresent())
            throw new IllegalArgumentException("User with that username already exists");

        LinkedList<Role> roles = new LinkedList<>();
        roles.add(Role.ROLE_CLIENT);
        User user = User.builder()
                .email(createUserCommand.getEmail())
                .password(passwordEncoder.encode(createUserCommand.getPassword()))
                .roles(roles)
                .name(createUserCommand.getName())
                .build();
        return userRepository.save(user);
    }

    @Override
    public String login(Query.Login login) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        return jwtTokenProvider.createToken(login.getEmail(), userRepository.findByEmail(login.getEmail()).get().getRoles());
    }
    public User search(Query.Search searchQuery) {
        return userRepository.findByEmail(searchQuery.getEmail()).get();
    }
    @Override
    public User whoami(){
        return search(()->SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public UserDetailsDTO getUserDetails() {
        User user = whoami();
        return new UserDetailsDTO(user.getEmail(), user.getName());

    }
}
