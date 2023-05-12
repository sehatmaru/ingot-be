package xcode.ingot.domain.request.key;

import lombok.Getter;
import lombok.Setter;
import xcode.ingot.domain.request.BaseRequest;

@Getter
@Setter
public class OpenKeyRequest extends BaseRequest {
    private String masterPassword;

    public OpenKeyRequest() {
    }
}
