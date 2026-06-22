package app.model.dto.user;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class UserDto {

    private UUID id;
    private String username;
    private String email;
    private UserRole role;
}
