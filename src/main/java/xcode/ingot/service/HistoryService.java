package xcode.ingot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xcode.ingot.domain.enums.EventEnum;
import xcode.ingot.domain.mapper.HistoryMapper;
import xcode.ingot.domain.model.CurrentUser;
import xcode.ingot.domain.model.HistoryModel;
import xcode.ingot.domain.repository.HistoryRepository;
import xcode.ingot.domain.response.BaseResponse;
import xcode.ingot.domain.response.key.HistoryResponse;
import xcode.ingot.exception.AppException;
import xcode.ingot.presenter.HistoryPresenter;

import java.util.*;

import static xcode.ingot.shared.Utils.generateSecureId;

@Service
public class HistoryService implements HistoryPresenter {

    @Autowired
    HistoryRepository historyRepository;

    private final HistoryMapper historyMapper = new HistoryMapper();

    @Override
    public BaseResponse<List<HistoryResponse>> getList() {
        BaseResponse<List<HistoryResponse>> response = new BaseResponse<>();
        List<HistoryResponse> result = new ArrayList<>();

        try {
            Optional<List<HistoryModel>> models = historyRepository.findByUserSecureIdOrderByCreatedAtDesc(CurrentUser.get().getUserSecureId());

            if (models.isPresent()) {
                result = historyMapper.historyModelListToHistoryResponseList(models.get());
            }

            response.setSuccess(result);
        } catch (Exception e) {
            throw new AppException(e.toString());
        }

        return response;
    }

    public void addHistory(EventEnum event, String secureId) {
        String userSecureId = secureId != null ? secureId : CurrentUser.get().getUserSecureId();
        try {
            HistoryModel model = new HistoryModel();
            model.setSecureId(generateSecureId());
            model.setUserSecureId(userSecureId);
            model.setCreatedAt(new Date());
            model.setEvent(event);

            historyRepository.save(model);
        } catch (Exception e) {
            throw new AppException(e.toString());
        }
    }
}
