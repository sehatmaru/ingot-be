package xcode.ingot.domain.mapper;

import xcode.ingot.domain.model.UserModel;
import xcode.ingot.domain.request.auth.ChangePasswordRequest;
import xcode.ingot.domain.request.auth.EditProfileRequest;
import xcode.ingot.domain.request.auth.RegisterRequest;
import xcode.ingot.domain.response.auth.LoginResponse;
import xcode.ingot.domain.response.auth.RegisterResponse;

import java.util.Date;

import static xcode.ingot.shared.Utils.encryptor;
import static xcode.ingot.shared.Utils.generateSecureId;

public class UserMapper {
    public LoginResponse userModelToLoginResponse(UserModel model, String accessToken) {
        if (model != null) {
            LoginResponse response = new LoginResponse();
            response.setSecureId(model.getSecureId());
            response.setFullname(model.getFullname());
            response.setEmail(model.getEmail());
            response.setUsername(model.getUsername());
            response.setAccessToken(accessToken);

            return response;
        } else {
            return null;
        }
    }

    public UserModel registerRequestToUserModel(RegisterRequest request) {
        if (request != null) {
            UserModel model = new UserModel();
            model.setSecureId(generateSecureId());
            model.setFullname(request.getFullname());
            model.setEmail(request.getEmail());
            model.setUsername(request.getUsername());
            model.setPassword(encryptor(request.getPassword(), true));
            model.setCreatedAt(new Date());

            return model;
        } else {
            return null;
        }
    }

    public UserModel editProfileRequestToUserModel(EditProfileRequest request, UserModel model) {
        if (request != null) {
            model.setFullname(request.getFullname());
            model.setEmail(request.getEmail());
            model.setUpdatedAt(new Date());

            return model;
        } else {
            return null;
        }
    }

    public UserModel changePasswordRequestToUserModel(ChangePasswordRequest request) {
        if (request != null) {
            UserModel model = new UserModel();
            model.setPassword(encryptor(request.getNewPassword(), true));
            model.setUpdatedAt(new Date());

            return model;
        } else {
            return null;
        }
    }

    public RegisterResponse userModelToRegisterResponse(UserModel model) {
        if (model != null) {
            RegisterResponse response = new RegisterResponse();
            response.setSecureId(model.getSecureId());
            response.setFullname(model.getFullname());
            response.setEmail(model.getEmail());
            response.setUsername(model.getUsername());

            return response;
        } else {
            return null;
        }
    }
}
