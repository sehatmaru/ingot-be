package xcode.ingot.domain.request.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String fullname;
    private String username;
    private String email;
    private String password;

    public RegisterRequest() {
    }
}
