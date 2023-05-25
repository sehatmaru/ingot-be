package xcode.ingot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xcode.ingot.domain.model.KeyModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeyRepository extends JpaRepository<KeyModel, String> {
    Optional<KeyModel> findBySecureIdAndDeletedAtIsNull(String secureId);

    Optional<List<KeyModel>> findAllByUserSecureIdAndDeletedAtIsNullOrderByUpdatedAtDesc(String secureId);

}
