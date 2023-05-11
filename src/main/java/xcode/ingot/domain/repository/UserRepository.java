package xcode.ingot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xcode.ingot.domain.model.UserModel;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    Optional<UserModel> findByUsernameAndDeletedAtIsNull(String username);
    
    Optional<UserModel> findBySecureIdAndDeletedAtIsNull(String secureId);
}
