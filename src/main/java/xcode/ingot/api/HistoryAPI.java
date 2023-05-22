package xcode.ingot.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xcode.ingot.domain.response.BaseResponse;
import xcode.ingot.domain.response.key.HistoryResponse;
import xcode.ingot.presenter.HistoryPresenter;

import java.util.List;

@Validated
@RestController
@RequestMapping(value = "history")
public class HistoryAPI {

    final HistoryPresenter historyPresenter;

    public HistoryAPI(HistoryPresenter historyPresenter) {
        this.historyPresenter = historyPresenter;
    }


    @GetMapping("/list")
    ResponseEntity<BaseResponse<List<HistoryResponse>>> getList() {
        BaseResponse<List<HistoryResponse>> response = historyPresenter.getList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

}
