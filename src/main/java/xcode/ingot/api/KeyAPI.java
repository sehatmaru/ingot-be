package xcode.ingot.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xcode.ingot.domain.enums.KeyTypeEnum;
import xcode.ingot.domain.request.BaseRequest;
import xcode.ingot.domain.request.key.CreateEditKeyRequest;
import xcode.ingot.domain.request.key.ListKeyRequest;
import xcode.ingot.domain.request.key.OpenDeleteKeyRequest;
import xcode.ingot.domain.response.BaseResponse;
import xcode.ingot.domain.response.key.CreateEditKeyResponse;
import xcode.ingot.domain.response.key.KeyResponse;
import xcode.ingot.domain.response.key.ListKeyResponse;
import xcode.ingot.domain.response.key.OpenKeyResponse;
import xcode.ingot.presenter.KeyPresenter;

import java.util.List;

@RestController
@RequestMapping(value = "key")
public class KeyAPI {
    
    final KeyPresenter keyPresenter;

    public KeyAPI(KeyPresenter keyPresenter) {
        this.keyPresenter = keyPresenter;
    }

    @PostMapping("/create")
    ResponseEntity<BaseResponse<CreateEditKeyResponse>> create(@RequestBody @Validated CreateEditKeyRequest request) {
        BaseResponse<CreateEditKeyResponse> response = keyPresenter.create(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/edit")
    ResponseEntity<BaseResponse<CreateEditKeyResponse>> edit(@RequestBody @Validated CreateEditKeyRequest request) {
        BaseResponse<CreateEditKeyResponse> response = keyPresenter.edit(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/list")
    ResponseEntity<BaseResponse<ListKeyResponse>> getList(@RequestBody @Validated ListKeyRequest request) {
        BaseResponse<ListKeyResponse> response = keyPresenter.getList(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/open")
    ResponseEntity<BaseResponse<OpenKeyResponse>> openKey(@RequestBody @Validated OpenDeleteKeyRequest request) {
        BaseResponse<OpenKeyResponse> response = keyPresenter.openKey(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/detail")
    ResponseEntity<BaseResponse<KeyResponse>> detail(@RequestBody @Validated BaseRequest request) {
        BaseResponse<KeyResponse> response = keyPresenter.getDetail(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/type/list")
    ResponseEntity<BaseResponse<List<KeyTypeEnum>>> getKeyTypeList() {
        BaseResponse<List<KeyTypeEnum>> response = keyPresenter.getKeyTypeList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/delete")
    ResponseEntity<BaseResponse<Boolean>> deleteKey(@RequestBody @Validated OpenDeleteKeyRequest request) {
        BaseResponse<Boolean> response = keyPresenter.deleteKey(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
