package xcode.ingot.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import xcode.ingot.domain.model.UserModel;
import xcode.ingot.presenter.JwtPresenter;

import java.util.Date;

import static xcode.ingot.shared.Utils.getTomorrowDate;

@Service
public class JwtService implements JwtPresenter {

    @Override
    public String generateToken(UserModel user) {
        return Jwts.builder()
                .setSubject(user.getSecureId())
                .setIssuedAt(new Date())
                .setExpiration(getTomorrowDate())
                .signWith(SignatureAlgorithm.HS256, "xcode")
                .compact();
    }
}
