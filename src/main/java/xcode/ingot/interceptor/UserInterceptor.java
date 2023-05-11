package xcode.ingot.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xcode.ingot.domain.model.CurrentUser;
import xcode.ingot.domain.model.TokenModel;
import xcode.ingot.domain.repository.TokenRepository;
import xcode.ingot.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static xcode.ingot.shared.ResponseCode.TOKEN_ERROR_MESSAGE;

@Slf4j
@Component
@EnableAsync
public class UserInterceptor implements HandlerInterceptor {

  @Autowired
  TokenRepository tokenRepository;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    String token = request.getHeader("Authorization");

    if (token != null) {
      Optional<TokenModel> tokenModel = tokenRepository.findByToken(token.substring(7));

      if (tokenModel.isPresent()) {
        if (!tokenModel.get().isValid()) {
          throw new AppException(TOKEN_ERROR_MESSAGE);
        }

        CurrentUser.set(tokenModel.get());
      } else {
        throw new AppException(TOKEN_ERROR_MESSAGE);
      }
    }

    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    CurrentUser.remove();
  }

}
