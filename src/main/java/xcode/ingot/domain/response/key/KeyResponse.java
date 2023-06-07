package xcode.ingot.domain.response.key;

import lombok.Getter;
import lombok.Setter;
import xcode.ingot.domain.enums.CategoryEnum;

@Getter
@Setter
public class KeyResponse {
    private String secureId;
    private String name;
    private CategoryEnum category;
    private String note;
    private String username;
    private String linkUrl;
}
