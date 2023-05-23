package xcode.ingot.domain.request.key;

import lombok.Getter;
import lombok.Setter;
import xcode.ingot.domain.enums.CategoryEnum;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ListKeyRequest {
    @NotBlank()
    private String search = "";

    private CategoryEnum category;

    public ListKeyRequest() {
    }
}
