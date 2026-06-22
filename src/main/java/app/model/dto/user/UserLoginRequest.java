package app.model.dto.user;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserLoginRequest {

    @Size(min = 3,message = "Username must be at least 3 characters")
    private String Username;
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
