package xcode.ingot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xcode.ingot.domain.enums.KeyTypeEnum;
import xcode.ingot.domain.mapper.KeyMapper;
import xcode.ingot.domain.model.CurrentUser;
import xcode.ingot.domain.model.KeyModel;
import xcode.ingot.domain.repository.KeyRepository;
import xcode.ingot.domain.request.BaseRequest;
import xcode.ingot.domain.request.key.CreateEditKeyRequest;
import xcode.ingot.domain.request.key.ListKeyRequest;
import xcode.ingot.domain.request.key.OpenKeyRequest;
import xcode.ingot.domain.response.BaseResponse;
import xcode.ingot.domain.response.key.CreateEditKeyResponse;
import xcode.ingot.domain.response.key.KeyResponse;
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
    private KeyRepository keyRepository;

    private final KeyMapper keyMapper = new KeyMapper();

    @Override
    public BaseResponse<CreateEditKeyResponse> create(CreateEditKeyRequest request) {
        BaseResponse<CreateEditKeyResponse> response = new BaseResponse<>();

        if (request.getName().isEmpty() || Objects.isNull(request.getKeyType())
            || request.getUsername().isEmpty() || request.getPassword().isEmpty()) {
            throw new AppException(PARAMS_ERROR_MESSAGE);
        }

        try {
            KeyModel model = keyMapper.createEditRequestToKeyModel(request);
            keyRepository.save(model);

            response.setSuccess(keyMapper.keyModelToCreateEditKeyResponse(model));
        } catch (Exception e) {
            throw new AppException(e.toString());
        }

        return response;
    }

    @Override
    public BaseResponse<CreateEditKeyResponse> edit(CreateEditKeyRequest request) {
        BaseResponse<CreateEditKeyResponse> response = new BaseResponse<>();

        if (request.getSecureId().isEmpty()) {
            throw new AppException(PARAMS_ERROR_MESSAGE);
        }

        Optional<KeyModel> model = keyRepository.findBySecureIdAndDeletedAtIsNull(request.getSecureId());

        if (model.isEmpty() || !isBelonging(model.get().getUserSecureId())) {
            throw new AppException(NOT_FOUND_MESSAGE);
        }

        try {
            model = Optional.ofNullable(keyMapper.setKeyByRequest(request, model.get()));

            if (model.isPresent()) {
                keyRepository.save(model.get());
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

        if (request.getSecureId().isEmpty()) {
            throw new AppException(PARAMS_ERROR_MESSAGE);
        }

        Optional<KeyModel> model = keyRepository.findBySecureIdAndDeletedAtIsNull(request.getSecureId());

        if (model.isEmpty() || !isBelonging(model.get().getUserSecureId())) {
            throw new AppException(NOT_FOUND_MESSAGE);
        }

        response.setSuccess(keyMapper.keyModelToKeyResponse(model.get(), true));

        return response;
    }

    @Override
    public BaseResponse<List<KeyResponse>> getList(ListKeyRequest request) {
        BaseResponse<List<KeyResponse>> response = new BaseResponse<>();
        List<KeyResponse> result = new ArrayList<>();

        try {
            Optional<List<KeyModel>> models = keyRepository.findAllByUserSecureIdAndDeletedAtIsNull(CurrentUser.get().getUserSecureId());

            if (models.isPresent()) {
                List<KeyModel> filteredModels = models.get().stream()
                        .filter(data -> data.getName().toLowerCase().contains(request.getSearch())
                                && (request.getKeyType() == null || data.getKeyType() == request.getKeyType()))
                        .collect(Collectors.toList());

                result = keyMapper.keyModelListToKeyResponseList(filteredModels);
            }

            response.setSuccess(result);
        } catch (Exception e) {
            throw new AppException(e.toString());
        }

        return response;
    }

    @Override
    public BaseResponse<OpenKeyResponse> openKey(OpenKeyRequest request) {
        BaseResponse<OpenKeyResponse> response = new BaseResponse<>();

        if (request.getSecureId().isEmpty()) {
            throw new AppException(PARAMS_ERROR_MESSAGE);
        }

        Optional<KeyModel> model = keyRepository.findBySecureIdAndDeletedAtIsNull(request.getSecureId());

        if (model.isEmpty() || !isBelonging(model.get().getUserSecureId())) {
            throw new AppException(NOT_FOUND_MESSAGE);
        }

        if (!userService.getCurrentUserPassword().equals(request.getPassword())) {
            throw new AppException(INVALID_PASSWORD);
        }

        response.setSuccess(keyMapper.keyModelToOpenKeyResponse(model.get()));

        return response;
    }

    @Override
    public BaseResponse<List<KeyTypeEnum>> getKeyTypeList() {
        BaseResponse<List<KeyTypeEnum>> response = new BaseResponse<>();
        List<KeyTypeEnum> list = Arrays.asList(KeyTypeEnum.values());

        response.setSuccess(list);

        return response;
    }

    private boolean isBelonging(String secureId) {
        return secureId.equals(CurrentUser.get().getUserSecureId());
    }
}
