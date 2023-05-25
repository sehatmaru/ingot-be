package xcode.ingot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xcode.ingot.domain.model.ResetModel;

import java.util.List;

@Repository
public interface ResetRepository extends JpaRepository<ResetModel, String> {
    List<ResetModel> findByCodeAndVerifiedIsFalse(String code);

}
