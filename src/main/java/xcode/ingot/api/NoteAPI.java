package xcode.ingot.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "note")
public class NoteAPI {
    
//    final NotePresenter notePresenter;
//
//    public NoteAPI(NotePresenter notePresenter) {
//        this.notePresenter = notePresenter;
//    }
//
//    @PostMapping("/create")
//    ResponseEntity<BaseResponse<NoteResponse>> create(@RequestBody @Validated CreateEditNoteRequest request) {
//        BaseResponse<NoteResponse> response = notePresenter.create(request);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(response);
//    }
//
//    @PostMapping("/edit")
//    ResponseEntity<BaseResponse<NoteResponse>> edit(@RequestBody @Validated CreateEditNoteRequest request) {
//        BaseResponse<NoteResponse> response = notePresenter.edit(request);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(response);
//    }
//
//    @PostMapping("/list")
//    ResponseEntity<BaseResponse<ListNoteResponse>> getList(@RequestBody @Validated ListNoteRequest request) {
//        BaseResponse<ListNoteResponse> response = notePresenter.getList(request);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(response);
//    }
//
//    @PostMapping("/detail")
//    ResponseEntity<BaseResponse<DetailNoteResponse>> getDetail(@RequestBody @Validated DetailNoteRequest request) {
//        BaseResponse<DetailNoteResponse> response = notePresenter.getDetail(request);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(response);
//    }
//
//    @PostMapping("/delete")
//    ResponseEntity<BaseResponse<Boolean>> delete(@RequestBody @Validated DetailNoteRequest request) {
//        BaseResponse<Boolean> response = notePresenter.delete(request);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(response);
//    }
//
//    @PostMapping("/toggle-lock")
//    ResponseEntity<BaseResponse<Boolean>> toggleLock(@RequestBody @Validated ToggleLockRequest request) {
//        BaseResponse<Boolean> response = notePresenter.toggleLock(request);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(response);
//    }
}
