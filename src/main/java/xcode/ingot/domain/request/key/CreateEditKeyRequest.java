package xcode.ingot.domain.request.key;

import lombok.Getter;
import lombok.Setter;
import xcode.ingot.domain.enums.KeyTypeEnum;
import xcode.ingot.domain.request.BaseRequest;

@Getter
@Setter
public class CreateEditKeyRequest extends BaseRequest {
    private String name;
    private String password;
    private KeyTypeEnum keyType;
    private String note;
    private String username;

    public CreateEditKeyRequest() {
    }
}
