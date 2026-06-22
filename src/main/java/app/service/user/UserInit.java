package app.service.user;

import app.model.dto.user.UserDto;
import app.model.dto.user.UserRegisterRequest;
import app.model.dto.user.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserInit implements CommandLineRunner {

    private final UserService userService;

    public UserInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<UserDto> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            return;
        }

        UserRegisterRequest userRegisterRequest = UserRegisterRequest.builder()
                .username("defaultUser")
                .password("123456")
                .email("defaultEmail@example.com")
                .userRole(UserRole.ADMIN)
                .build();

        userService.register(userRegisterRequest);

        log.info("Default user created with username [%s] and password [%s].".formatted(
                userRegisterRequest.getUsername(), userRegisterRequest.getPassword()));
    }
}
