package xcode.ingot.domain.mapper;

//import xcode.notes.notes.domain.model.CurrentAuth;
//import xcode.notes.notes.domain.model.NoteModel;
//import xcode.notes.notes.domain.request.note.CreateEditNoteRequest;
//import xcode.notes.notes.domain.response.note.DetailNoteResponse;
//import xcode.notes.notes.domain.response.note.NoteResponse;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static xcode.notes.notes.shared.Utils.generateSecureId;

public class NoteMapper {
//    public NoteModel createEditRequestToNoteModel(CreateEditNoteRequest request) {
//        if (request != null) {
//            NoteModel model = new NoteModel();
//            model.setSecureId(generateSecureId());
//            model.setOwnerSecureId(CurrentAuth.get().getAuthSecureId());
//            model.setTitle(request.getTitle());
//            model.setContent(request.getContent());
//            model.setLocked(request.isLocked());
//            model.setPassword(request.getPassword());
//            model.setCreatedAt(new Date());
//            model.setUpdatedAt(new Date());
//
//            return model;
//        } else {
//            return null;
//        }
//    }
//
//    public NoteResponse noteModelToNoteResponse(NoteModel model) {
//        if (model != null) {
//            NoteResponse response = new NoteResponse();
//            response.setSecureId(model.getSecureId());
//            response.setTitle(model.getTitle());
//            response.setLocked(model.isLocked());
//            response.setLastEdited(model.getUpdatedAt());
//
//            return response;
//        } else {
//            return null;
//        }
//    }
//
//    public DetailNoteResponse noteModelToDetailNoteResponse(NoteModel model) {
//        if (model != null) {
//            DetailNoteResponse response = new DetailNoteResponse();
//            response.setSecureId(model.getSecureId());
//            response.setTitle(model.getTitle());
//            response.setContent(model.getContent());
//            response.setLocked(model.isLocked());
//            response.setLastEdited(model.getUpdatedAt());
//
//            return response;
//        } else {
//            return null;
//        }
//    }
//
//    public List<NoteResponse> noteModelListToNoteResponseList(List<NoteModel> models) {
//        List<NoteResponse> result = new ArrayList<>();
//
//        for (NoteModel model : models) {
//            result.add(noteModelToNoteResponse(model));
//        }
//
//        return result;
//    }
//
//    public NoteModel setNoteByRequest(CreateEditNoteRequest request, NoteModel model) {
//        if (model != null) {
//            model.setTitle(request.getTitle());
//            model.setContent(request.getContent());
//            model.setUpdatedAt(new Date());
//
//            return model;
//        } else {
//            return null;
//        }
//    }
}
