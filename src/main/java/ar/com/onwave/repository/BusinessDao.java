package ar.com.onwave.repository;

import ar.com.onwave.repository.model.BusinessModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessDao extends JpaRepository<BusinessModel, Long> {
    List<BusinessModel> findByNombreContainsAndActivo(String keyword, Boolean activo);
    List<BusinessModel> findByActivo(Boolean active);
}
