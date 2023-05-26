package xcode.ingot.domain.response.key;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenKeyResponse extends KeyResponse {
    private String password;
}
