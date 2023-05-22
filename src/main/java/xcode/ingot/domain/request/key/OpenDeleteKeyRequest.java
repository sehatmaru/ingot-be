package xcode.ingot.domain.request.key;

import lombok.Getter;
import lombok.Setter;
import xcode.ingot.domain.request.BaseRequest;

@Getter
@Setter
public class OpenDeleteKeyRequest extends BaseRequest {
    private String password;

    public OpenDeleteKeyRequest() {
    }
}
