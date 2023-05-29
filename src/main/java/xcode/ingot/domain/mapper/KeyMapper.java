package xcode.ingot.domain.mapper;

import xcode.ingot.domain.model.CurrentUser;
import xcode.ingot.domain.model.KeyModel;
import xcode.ingot.domain.request.key.CreateEditKeyRequest;
import xcode.ingot.domain.response.key.CreateEditKeyResponse;
import xcode.ingot.domain.response.key.KeyResponse;
import xcode.ingot.domain.response.key.OpenKeyResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static xcode.ingot.shared.Utils.*;

public class KeyMapper {
    public KeyModel createEditRequestToKeyModel(CreateEditKeyRequest request) {
        if (request != null) {
            KeyModel model = new KeyModel();
            model.setSecureId(generateSecureId());
            model.setName(request.getName());
            model.setNote(request.getNote());
            model.setCategory(request.getCategory());
            model.setUsername(request.getUsername());
            model.setUserSecureId(CurrentUser.get().getUserSecureId());
            model.setPassword(encryptor(request.getPassword(), true));
            model.setCreatedAt(new Date());
            model.setUpdatedAt(new Date());

            return model;
        } else {
            return null;
        }
    }

    public CreateEditKeyResponse keyModelToCreateEditKeyResponse(KeyModel model) {
        if (model != null) {
            CreateEditKeyResponse response = new CreateEditKeyResponse();
            response.setSecureId(model.getSecureId());
            response.setName(model.getName());

            return response;
        } else {
            return null;
        }
    }

    public KeyResponse keyModelToKeyResponse(KeyModel model, boolean isDetail) {
        if (model != null) {
            KeyResponse response = new KeyResponse();
            response.setSecureId(model.getSecureId());
            response.setName(model.getName());
            response.setUsername(mask(model.getUsername()));
            response.setCategory(model.getCategory());

            if (isDetail) {
                response.setNote(model.getNote());
            }

            return response;
        } else {
            return null;
        }
    }

    public OpenKeyResponse keyModelToOpenKeyResponse(KeyModel model) {
        if (model != null) {
            OpenKeyResponse response = new OpenKeyResponse();
            response.setPassword(encryptor(model.getPassword(), false));
            response.setUsername(model.getUsername());
            response.setCategory(model.getCategory());
            response.setName(model.getName());
            response.setNote(model.getNote());
            response.setSecureId(model.getSecureId());
            response.setLastEdited(model.getUpdatedAt());

            return response;
        } else {
            return null;
        }
    }

    public List<KeyResponse> keyModelListToKeyResponseList(List<KeyModel> models) {
        List<KeyResponse> result = new ArrayList<>();

        for (KeyModel model : models) {
            result.add(keyModelToKeyResponse(model, false));
        }

        return result;
    }

    public KeyModel setKeyByRequest(CreateEditKeyRequest request, KeyModel model) {
        if (model != null) {
            model.setPassword(encryptor(request.getPassword(), true));
            model.setCategory(request.getCategory());
            model.setUsername(request.getUsername());
            model.setNote(request.getNote());
            model.setName(request.getName());
            model.setUpdatedAt(new Date());

            return model;
        } else {
            return null;
        }
    }
}
