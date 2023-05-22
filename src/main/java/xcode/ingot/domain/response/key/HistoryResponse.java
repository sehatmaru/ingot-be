package xcode.ingot.domain.response.key;

import lombok.Getter;
import lombok.Setter;
import xcode.ingot.domain.enums.EventEnum;

import java.util.Date;

@Getter
@Setter
public class HistoryResponse {
    private String secureId;
    private EventEnum event;
    private Date time;
}
