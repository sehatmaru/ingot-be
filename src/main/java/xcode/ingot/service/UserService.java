package xcode.ingot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xcode.ingot.domain.mapper.UserMapper;
import xcode.ingot.domain.model.CurrentUser;
import xcode.ingot.domain.model.TokenModel;
import xcode.ingot.domain.model.UserModel;
import xcode.ingot.domain.repository.TokenRepository;
import xcode.ingot.domain.repository.UserRepository;
import xcode.ingot.domain.request.auth.ChangePasswordRequest;
import xcode.ingot.domain.request.auth.EditProfileRequest;
import xcode.ingot.domain.request.auth.LoginRequest;
import xcode.ingot.domain.request.auth.RegisterRequest;
import xcode.ingot.domain.response.BaseResponse;
import xcode.ingot.domain.response.auth.LoginResponse;
import xcode.ingot.domain.response.auth.RegisterResponse;
import xcode.ingot.exception.AppException;
import xcode.ingot.presenter.UserPresenter;


import java.util.Objects;
import java.util.Optional;

import static xcode.ingot.shared.ResponseCode.*;
import static xcode.ingot.shared.Utils.encryptor;


@Service
public class UserService implements UserPresenter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    private final UserMapper userMapper = new UserMapper();

    @Override
    public BaseResponse<LoginResponse> login(LoginRequest request) {
        BaseResponse<LoginResponse> response = new BaseResponse<>();

        if (request.getUsername().isEmpty() && request.getPassword().isEmpty()) {
            throw new AppException(PARAMS_ERROR_MESSAGE);
        }

        Optional<UserModel> model = userRepository.findByUsernameAndDeletedAtIsNull(request.getUsername());

        if (model.isEmpty() || !Objects.equals(encryptor(model.get().getPassword(), false), request.getPassword())) {
            throw new AppException(AUTH_ERROR_MESSAGE);
        }

        String token = jwtService.generateToken(model.get());
        tokenRepository.save(new TokenModel(
                token,
                model.get().getSecureId()
        ));

        response.setSuccess(userMapper.userModelToLoginResponse(model.get(), token));

        return response;
    }

    @Override
    public BaseResponse<RegisterResponse> register(RegisterRequest request) {
        BaseResponse<RegisterResponse> response = new BaseResponse<>();

        if (request.getUsername().isEmpty() || request.getPassword().isEmpty()
                || request.getFullname().isEmpty() || request.getEmail().isEmpty()) {
            throw new AppException(PARAMS_ERROR_MESSAGE);
        }

        if (userRepository.findByUsernameAndDeletedAtIsNull(request.getUsername()).isPresent()) {
            throw new AppException(EXIST_MESSAGE);
        }

        try {
            UserModel model = userMapper.registerRequestToUserModel(request);
            userRepository.save(model);
            response.setSuccess(userMapper.userModelToRegisterResponse(model));
        } catch (Exception e) {
            throw new AppException(e.toString());
        }

        return response;
    }

    @Override
    public BaseResponse<Boolean> logout() {
        BaseResponse<Boolean> response = new BaseResponse<>();

        Optional<TokenModel> tokenModel = tokenRepository.findByToken(CurrentUser.get().getToken());

        if (tokenModel.isEmpty()) {
            throw new AppException(NOT_FOUND_MESSAGE);
        }

        try {
            tokenModel.ifPresent(tokenRepository::delete);

            response.setSuccess(true);
        } catch (Exception e) {
            throw new AppException(e.toString());
        }

        return response;
    }

    @Override
    public BaseResponse<LoginResponse> getProfile() {
        BaseResponse<LoginResponse> response = new BaseResponse<>();

        Optional<UserModel> model = userRepository.findBySecureIdAndDeletedAtIsNull(CurrentUser.get().getUserSecureId());

        if (model.isEmpty()) {
            throw new AppException(AUTH_ERROR_MESSAGE);
        }

        response.setSuccess(userMapper.userModelToLoginResponse(model.get(), null));

        return response;
    }

    @Override
    public BaseResponse<Boolean> editProfile(EditProfileRequest request) {
        BaseResponse<Boolean> response = new BaseResponse<>();

        if (request.getFullname().isEmpty() || request.getEmail().isEmpty()) {
            throw new AppException(PARAMS_ERROR_MESSAGE);
        }

        Optional<UserModel> model = userRepository.findBySecureIdAndDeletedAtIsNull(CurrentUser.get().getUserSecureId());

        if (model.isEmpty()) {
            throw new AppException(NOT_FOUND_MESSAGE);
        }

        try {
            userRepository.save(userMapper.editProfileRequestToUserModel(request, model.get()));

            response.setSuccess(true);
        } catch (Exception e) {
            throw new AppException(e.toString());
        }

        return response;
    }

    @Override
    public BaseResponse<Boolean> changePassword(ChangePasswordRequest request) {
        BaseResponse<Boolean> response = new BaseResponse<>();

        if (request.getOldPassword().isEmpty() || request.getNewPassword().isEmpty()) {
            throw new AppException(PARAMS_ERROR_MESSAGE);
        }

        Optional<UserModel> model = userRepository.findBySecureIdAndDeletedAtIsNull(CurrentUser.get().getUserSecureId());

        if (model.isEmpty()) {
            throw new AppException(NOT_FOUND_MESSAGE);
        }

        if (!Objects.equals(encryptor(model.get().getPassword(), false), request.getOldPassword())) {
            throw new AppException(AUTH_ERROR_MESSAGE);
        }

        try {
            userRepository.save(userMapper.changePasswordRequestToUserModel(request));

            response.setSuccess(true);
        } catch (Exception e) {
            throw new AppException(e.toString());
        }

        return response;
    }

    public String getCurrentUserPassword() {
        Optional<UserModel> userModel = userRepository.findBySecureIdAndDeletedAtIsNull(CurrentUser.get().getUserSecureId());

        return userModel.map(model -> encryptor(model.getPassword(), false)).orElse(null);
    }
}
