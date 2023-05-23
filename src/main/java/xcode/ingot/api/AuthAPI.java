package xcode.ingot.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xcode.ingot.domain.request.auth.*;
import xcode.ingot.domain.response.BaseResponse;
import xcode.ingot.domain.response.auth.LoginResponse;
import xcode.ingot.domain.response.auth.RegisterResponse;
import xcode.ingot.domain.response.auth.VerifyOtpResponse;
import xcode.ingot.presenter.UserPresenter;

@Validated
@RestController
@RequestMapping(value = "auth")
public class AuthAPI {
    
    final UserPresenter userPresenter;

    public AuthAPI(UserPresenter userPresenter) {
        this.userPresenter = userPresenter;
    }

    @PostMapping("/login")
    ResponseEntity<BaseResponse<LoginResponse>> login(@RequestBody @Validated LoginRequest request) {
        BaseResponse<LoginResponse> response = userPresenter.login(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/register")
    ResponseEntity<BaseResponse<RegisterResponse>> register(@RequestBody @Validated RegisterRequest request) {
        BaseResponse<RegisterResponse> response = userPresenter.register(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/profile")
    ResponseEntity<BaseResponse<LoginResponse>> profile() {
        BaseResponse<LoginResponse> response = userPresenter.getProfile();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/edit-profile")
    ResponseEntity<BaseResponse<Boolean>> editProfile(@RequestBody @Validated EditProfileRequest request) {
        BaseResponse<Boolean> response = userPresenter.editProfile(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/change-password")
    ResponseEntity<BaseResponse<Boolean>> changePassword(@RequestBody @Validated ChangePasswordRequest request) {
        BaseResponse<Boolean> response = userPresenter.changePassword(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/logout")
    ResponseEntity<BaseResponse<Boolean>> logout() {
        BaseResponse<Boolean> response = userPresenter.logout();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/otp/verify")
    ResponseEntity<BaseResponse<VerifyOtpResponse>> verifyOtp(@RequestBody @Validated VerifyOtpRequest request) {
        BaseResponse<VerifyOtpResponse> response = userPresenter.verifyOtp(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/otp/resend")
    ResponseEntity<BaseResponse<Boolean>> resendOtp() {
        BaseResponse<Boolean> response = userPresenter.resendOtp();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
