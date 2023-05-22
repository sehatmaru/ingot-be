package xcode.ingot.domain.request.key;

import lombok.Getter;
import lombok.Setter;
import xcode.ingot.domain.request.BaseRequest;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OpenDeleteKeyRequest extends BaseRequest {
    @NotBlank()
    private String password;

    public OpenDeleteKeyRequest() {
    }
}
