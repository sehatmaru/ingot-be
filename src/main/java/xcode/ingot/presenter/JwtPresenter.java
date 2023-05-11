package xcode.ingot.presenter;

import xcode.ingot.domain.model.UserModel;

public interface JwtPresenter {
    String generateToken(UserModel user);
}
