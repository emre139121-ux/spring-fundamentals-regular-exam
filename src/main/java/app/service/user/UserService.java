package app.service.user;

import app.mapper.user.UserMapper;
import app.model.dto.user.UserDto;
import app.model.dto.user.UserLoginRequest;
import app.model.entity.user.User;
import app.repository.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserDto login(UserLoginRequest userLoginRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(userLoginRequest.getUsername());

        if (optionalUser.isEmpty() ||
                !passwordEncoder.matches(userLoginRequest.getPassword(), optionalUser.get().getPassword())
        ) {

            throw new RuntimeException("Username or password mismatch!");
        }

        return UserMapper.toUserDto(optionalUser.get());
    }
}
