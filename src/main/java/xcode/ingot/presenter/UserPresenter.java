package xcode.ingot.presenter;

import xcode.ingot.domain.request.auth.*;
import xcode.ingot.domain.response.BaseResponse;
import xcode.ingot.domain.response.auth.LoginResponse;
import xcode.ingot.domain.response.auth.RegisterResponse;

public interface UserPresenter {
    BaseResponse<LoginResponse> login(LoginRequest request);
    BaseResponse<RegisterResponse> register(RegisterRequest request);
    BaseResponse<Boolean> verifyOtp(VerifyOtpRequest request);
    BaseResponse<Boolean> resendOtp();
    BaseResponse<LoginResponse> getProfile();
    BaseResponse<Boolean> editProfile(EditProfileRequest request);
    BaseResponse<Boolean> changePassword(ChangePasswordRequest request);
    BaseResponse<Boolean> logout();
    BaseResponse<Boolean> forgotPassword(ForgotPasswordRequest request);
    BaseResponse<Boolean> resetPassword(ResetPasswordRequest request);
}
