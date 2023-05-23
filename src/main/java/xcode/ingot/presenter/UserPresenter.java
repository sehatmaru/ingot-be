package xcode.ingot.presenter;

import xcode.ingot.domain.request.auth.*;
import xcode.ingot.domain.response.BaseResponse;
import xcode.ingot.domain.response.auth.LoginResponse;
import xcode.ingot.domain.response.auth.RegisterResponse;
import xcode.ingot.domain.response.auth.VerifyOtpResponse;

public interface UserPresenter {
    BaseResponse<LoginResponse> login(LoginRequest request);
    BaseResponse<RegisterResponse> register(RegisterRequest request);
    BaseResponse<VerifyOtpResponse> verifyOtp(VerifyOtpRequest request);
    BaseResponse<Boolean> resendOtp();
    BaseResponse<LoginResponse> getProfile();
    BaseResponse<Boolean> editProfile(EditProfileRequest request);
    BaseResponse<Boolean> changePassword(ChangePasswordRequest request);
    BaseResponse<Boolean> logout();
}
