package xcode.ingot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xcode.ingot.domain.enums.CategoryEnum;
import xcode.ingot.domain.enums.EventEnum;
import xcode.ingot.domain.mapper.KeyMapper;
import xcode.ingot.domain.model.CurrentUser;
import xcode.ingot.domain.model.KeyModel;
import xcode.ingot.domain.repository.KeyRepository;
import xcode.ingot.domain.request.BaseRequest;
import xcode.ingot.domain.request.key.CreateEditKeyRequest;
import xcode.ingot.domain.request.key.ListKeyRequest;
import xcode.ingot.domain.request.key.OpenDeleteKeyRequest;
import xcode.ingot.domain.response.BaseResponse;
import xcode.ingot.domain.response.key.CreateEditKeyResponse;
import xcode.ingot.domain.response.key.KeyResponse;
import xcode.ingot.domain.response.key.ListKeyResponse;
import xcode.ingot.domain.response.key.OpenKeyResponse;
import xcode.ingot.exception.AppException;
import xcode.ingot.presenter.KeyPresenter;

import java.util.*;
import java.util.stream.Collectors;

import static xcode.ingot.shared.ResponseCode.*;

@Service
public class KeyService implements KeyPresenter {

    @Autowired
    private UserService userService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private KeyRepository keyRepository;

    private final KeyMapper keyMapper = new KeyMapper();

    @Override
    public BaseResponse<CreateEditKeyResponse> create(CreateEditKeyRequest request) {
        BaseResponse<CreateEditKeyResponse> response = new BaseResponse<>();

        try {
            KeyModel model = keyMapper.createEditRequestToKeyModel(request);
            keyRepository.save(model);

            historyService.addHistory(EventEnum.CREATE_KEY, null);

            response.setSuccess(keyMapper.keyModelToCreateEditKeyResponse(model));
        } catch (Exception e) {
            throw new AppException(e.toString());
        }

        return response;
    }

    @Override
    public BaseResponse<CreateEditKeyResponse> edit(CreateEditKeyRequest request) {
        BaseResponse<CreateEditKeyResponse> response = new BaseResponse<>();

        Optional<KeyModel> model = keyRepository.findBySecureIdAndDeletedAtIsNull(request.getSecureId());

        if (model.isEmpty() || !isBelonging(model.get().getUserSecureId())) {
            throw new AppException(NOT_FOUND_MESSAGE);
        }

        try {
            model = Optional.ofNullable(keyMapper.setKeyByRequest(request, model.get()));

            if (model.isPresent()) {
                keyRepository.save(model.get());

                historyService.addHistory(EventEnum.EDIT_KEY, null);

                response.setSuccess(keyMapper.keyModelToCreateEditKeyResponse(model.get()));
            }
        } catch (Exception e) {
            throw new AppException(e.toString());
        }

        return response;
    }

    @Override
    public BaseResponse<KeyResponse> getDetail(BaseRequest request) {
        BaseResponse<KeyResponse> response = new BaseResponse<>();

        Optional<KeyModel> model = keyRepository.findBySecureIdAndDeletedAtIsNull(request.getSecureId());

        if (model.isEmpty() || !isBelonging(model.get().getUserSecureId())) {
            throw new AppException(NOT_FOUND_MESSAGE);
        }

        response.setSuccess(keyMapper.keyModelToKeyResponse(model.get(), true));

        return response;
    }

    @Override
    public BaseResponse<ListKeyResponse> getList(ListKeyRequest request) {
        BaseResponse<ListKeyResponse> response = new BaseResponse<>();
        ListKeyResponse result = new ListKeyResponse();

        try {
            Optional<List<KeyModel>> models = keyRepository.findAllByUserSecureIdAndDeletedAtIsNull(CurrentUser.get().getUserSecureId());

            if (models.isPresent()) {
                List<KeyModel> filteredModels = models.get().stream()
                        .filter(data -> data.getName().toLowerCase().contains(request.getSearch()))
                        .filter(data -> request.getCategory() == CategoryEnum.ALL || data.getCategory() == request.getCategory())
                        .collect(Collectors.toList());

                List<KeyResponse> list = keyMapper.keyModelListToKeyResponseList(filteredModels);

                result.setData(list);
                result.setTotalData(list.size());
            }

            response.setSuccess(result);
        } catch (Exception e) {
            throw new AppException(e.toString());
        }

        return response;
    }

    @Override
    public BaseResponse<OpenKeyResponse> openKey(OpenDeleteKeyRequest request) {
        BaseResponse<OpenKeyResponse> response = new BaseResponse<>();

        KeyModel model = findBySecureId(request.getSecureId(), request.getPassword());

        try {
            historyService.addHistory(EventEnum.OPEN_KEY, null);

            response.setSuccess(keyMapper.keyModelToOpenKeyResponse(model));
        } catch (Exception e) {
            throw new AppException(e.toString());
        }

        return response;
    }

    @Override
    public BaseResponse<Boolean> deleteKey(OpenDeleteKeyRequest request) {
        BaseResponse<Boolean> response = new BaseResponse<>();

        KeyModel model = findBySecureId(request.getSecureId(), request.getPassword());

        try {
            model.setDeletedAt(new Date());

            keyRepository.save(model);
            historyService.addHistory(EventEnum.DELETE_KEY, null);

            response.setSuccess(true);
        } catch (Exception e) {
            throw new AppException(e.toString());
        }

        return response;
    }

    @Override
    public BaseResponse<List<CategoryEnum>> getKeyTypeList() {
        BaseResponse<List<CategoryEnum>> response = new BaseResponse<>();
        List<CategoryEnum> list = Arrays.asList(CategoryEnum.values());

        response.setSuccess(list);

        return response;
    }

    private boolean isBelonging(String secureId) {
        return secureId.equals(CurrentUser.get().getUserSecureId());
    }

    private KeyModel findBySecureId(String secureId, String password) {

        Optional<KeyModel> model = keyRepository.findBySecureIdAndDeletedAtIsNull(secureId);

        if (model.isEmpty() || !isBelonging(model.get().getUserSecureId())) {
            throw new AppException(NOT_FOUND_MESSAGE);
        }

        if (!userService.getCurrentUserPassword().equals(password)) {
            throw new AppException(INVALID_PASSWORD);
        }

        return model.get();
    }
}
