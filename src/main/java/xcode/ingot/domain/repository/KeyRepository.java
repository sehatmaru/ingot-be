package xcode.ingot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xcode.ingot.domain.enums.KeyTypeEnum;
import xcode.ingot.domain.model.KeyModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeyRepository extends JpaRepository<KeyModel, String> {
    Optional<KeyModel> findBySecureIdAndDeletedAtIsNull(String secureId);

    Optional<List<KeyModel>> findAllByDeletedAtIsNull();

    Optional<List<KeyModel>> findAllByNameAndDeletedAtIsNull(String name);

    Optional<List<KeyModel>> findAllByKeyTypeAndDeletedAtIsNull(KeyTypeEnum type);

    Optional<List<KeyModel>> findAllByUserSecureIdAndDeletedAtIsNull(String secureId);

}
