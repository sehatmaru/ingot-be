package xcode.ingot.domain.response.key;

import lombok.Getter;
import lombok.Setter;
import xcode.ingot.domain.enums.KeyTypeEnum;

@Getter
@Setter
public class KeyResponse {
    private String secureId;
    private String name;
    private KeyTypeEnum keyType;
    private String note;
    private String username;
}
