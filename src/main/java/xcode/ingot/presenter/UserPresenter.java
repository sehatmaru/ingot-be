package xcode.ingot.presenter;

import xcode.ingot.domain.request.auth.ChangePasswordRequest;
import xcode.ingot.domain.request.auth.EditProfileRequest;
import xcode.ingot.domain.request.auth.LoginRequest;
import xcode.ingot.domain.request.auth.RegisterRequest;
import xcode.ingot.domain.response.BaseResponse;
import xcode.ingot.domain.response.auth.LoginResponse;
import xcode.ingot.domain.response.auth.RegisterResponse;

public interface UserPresenter {
    BaseResponse<LoginResponse> login(LoginRequest request);
    BaseResponse<RegisterResponse> register(RegisterRequest request);
    BaseResponse<LoginResponse> getProfile();
    BaseResponse<Boolean> editProfile(EditProfileRequest request);
    BaseResponse<Boolean> changePassword(ChangePasswordRequest request);
    BaseResponse<Boolean> logout();
}
