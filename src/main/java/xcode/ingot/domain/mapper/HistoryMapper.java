package xcode.ingot.domain.mapper;

import xcode.ingot.domain.model.HistoryModel;
import xcode.ingot.domain.response.key.HistoryResponse;

import java.util.ArrayList;
import java.util.List;

public class HistoryMapper {
    public List<HistoryResponse> historyModelListToHistoryResponseList(List<HistoryModel> models) {
        List<HistoryResponse> result = new ArrayList<>();

        for (HistoryModel model : models) {
            if (model != null) {
                HistoryResponse response = new HistoryResponse();
                response.setSecureId(model.getSecureId());
                response.setEvent(model.getEvent());
                response.setTime(model.getCreatedAt());

                result.add(response);
            }
        }

        return result;
    }

}
