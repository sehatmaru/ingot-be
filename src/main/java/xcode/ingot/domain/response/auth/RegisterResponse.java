package xcode.ingot.domain.response.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private String temporaryToken;
}
