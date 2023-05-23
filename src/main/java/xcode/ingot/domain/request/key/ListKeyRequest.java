package xcode.ingot.domain.request.key;

import lombok.Getter;
import lombok.Setter;
import xcode.ingot.domain.enums.CategoryEnum;

@Getter
@Setter
public class ListKeyRequest {
    private String search = "";

    private CategoryEnum category;

    public ListKeyRequest() {
    }
}
