package app.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data

@Builder
public class UserRegisterRequest {

    @Size(min = 3,message = "Username must be at least 3 characters")
    private String username;
    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    private UserRole userRole;
}
