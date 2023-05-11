package xcode.ingot.domain.request.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditProfileRequest {
    private String fullname;
    private String email;

    public EditProfileRequest() {
    }
}
