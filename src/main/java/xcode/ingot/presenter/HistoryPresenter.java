package xcode.ingot.presenter;

import xcode.ingot.domain.response.BaseResponse;
import xcode.ingot.domain.response.key.HistoryResponse;

import java.util.List;

public interface HistoryPresenter {
    BaseResponse<List<HistoryResponse>> getList();
}
