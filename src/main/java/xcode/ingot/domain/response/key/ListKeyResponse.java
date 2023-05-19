package xcode.ingot.domain.response.key;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListKeyResponse {
    private List<KeyResponse> data;
    private int totalData;
}
