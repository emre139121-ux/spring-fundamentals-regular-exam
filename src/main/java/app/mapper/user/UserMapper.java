package app.mapper.user;

import app.model.dto.user.UserDto;
import app.model.dto.user.UserRegisterRequest;
import app.model.dto.user.UserRole;
import app.model.entity.user.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserMapper {

    public static User toEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .build();
    }

    public static User toUserEntity(UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return null;
        }

        return User.builder()
                .username(userRegisterRequest.getUsername())
                .email(userRegisterRequest.getEmail())
                .password(userRegisterRequest.getPassword())
                .role(userRegisterRequest.getUserRole())
                .build();
    }

    public static UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
