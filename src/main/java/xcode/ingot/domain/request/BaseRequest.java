package xcode.ingot.domain.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BaseRequest {
    @NotBlank()
    private String secureId;

    public BaseRequest() {
    }
}
