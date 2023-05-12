package xcode.ingot.presenter;

import xcode.ingot.domain.enums.KeyTypeEnum;
import xcode.ingot.domain.request.BaseRequest;
import xcode.ingot.domain.request.key.CreateEditKeyRequest;
import xcode.ingot.domain.request.key.ListKeyRequest;
import xcode.ingot.domain.request.key.OpenKeyRequest;
import xcode.ingot.domain.response.BaseResponse;
import xcode.ingot.domain.response.key.CreateEditKeyResponse;
import xcode.ingot.domain.response.key.KeyResponse;
import xcode.ingot.domain.response.key.OpenKeyResponse;

import java.util.List;

public interface KeyPresenter {
    BaseResponse<CreateEditKeyResponse> create(CreateEditKeyRequest request);
    BaseResponse<CreateEditKeyResponse> edit(CreateEditKeyRequest request);
    BaseResponse<KeyResponse> getDetail(BaseRequest request);
    BaseResponse<List<KeyResponse>> getList(ListKeyRequest request);
    BaseResponse<OpenKeyResponse> openKey(OpenKeyRequest request);
    BaseResponse<List<KeyTypeEnum>> getKeyTypeList();
}
