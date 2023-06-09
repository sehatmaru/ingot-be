package xcode.ingot.presenter;

import xcode.ingot.domain.enums.CategoryEnum;
import xcode.ingot.domain.request.BaseRequest;
import xcode.ingot.domain.request.key.CreateEditKeyRequest;
import xcode.ingot.domain.request.key.ListKeyRequest;
import xcode.ingot.domain.response.BaseResponse;
import xcode.ingot.domain.response.key.*;

import java.util.List;

public interface KeyPresenter {
    BaseResponse<CreateEditKeyResponse> create(CreateEditKeyRequest request);
    BaseResponse<CreateEditKeyResponse> edit(CreateEditKeyRequest request);
    BaseResponse<KeyResponse> getDetail(BaseRequest request);
    BaseResponse<ListKeyResponse> getList(ListKeyRequest request);
    BaseResponse<OpenKeyResponse> openKey(BaseRequest request);
    BaseResponse<Boolean> deleteKey(BaseRequest request);
    BaseResponse<List<CategoryEnum>> getCategoryList();
    BaseResponse<PasswordResponse> copyKeyPassword(BaseRequest request);
    BaseResponse<PasswordResponse> generatePassword();
}
