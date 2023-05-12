package xcode.ingot.domain.request.key;

import lombok.Getter;
import lombok.Setter;
import xcode.ingot.domain.enums.KeyTypeEnum;

@Getter
@Setter
public class ListKeyRequest {
    private String search = "";
    private KeyTypeEnum keyType;

    public ListKeyRequest() {
    }
}
