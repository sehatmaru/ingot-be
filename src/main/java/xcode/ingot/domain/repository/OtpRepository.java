package xcode.ingot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xcode.ingot.domain.model.OtpModel;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<OtpModel, String> {
    Optional<OtpModel> findByUserSecureIdAndVerifiedIsFalse(String secureId);

    boolean existsByUserSecureIdAndVerifiedIsFalse(String secureId);

}
