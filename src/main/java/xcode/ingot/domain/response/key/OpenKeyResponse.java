package xcode.ingot.domain.response.key;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OpenKeyResponse extends KeyResponse {
    private String password;
    private Date lastEdited;
}
