package xcode.ingot.domain.request.key;

import lombok.Getter;
import lombok.Setter;
import xcode.ingot.domain.enums.KeyTypeEnum;
import xcode.ingot.domain.request.BaseRequest;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateEditKeyRequest extends BaseRequest {
    @NotBlank()
    private String name;

    @NotBlank()
    private String password;

    @NotBlank()
    private KeyTypeEnum keyType;

    @NotBlank()
    private String note;

    @NotBlank()
    private String username;

    public CreateEditKeyRequest() {
    }
}
