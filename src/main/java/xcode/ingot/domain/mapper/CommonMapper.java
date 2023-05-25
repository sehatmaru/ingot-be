package xcode.ingot.domain.mapper;

import xcode.ingot.domain.model.ResetModel;

import java.util.Date;

import static xcode.ingot.shared.Utils.*;

public class CommonMapper {
    public ResetModel generateResetModel(String email, String code) {
        ResetModel model = new ResetModel();
        model.setSecureId(generateSecureId());
        model.setCode(code);
        model.setEmail(email);
        model.setExpireAt(getTemporaryDate());
        model.setCreatedAt(new Date());

        return model;
    }

}
