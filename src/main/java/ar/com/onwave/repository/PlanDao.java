package ar.com.onwave.repository;

import ar.com.onwave.repository.model.PlanModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanDao extends JpaRepository<PlanModel, Long> {
    List<PlanModel> findByNombreContainsAndActivo(String keyword, Boolean activo);
    List<PlanModel> findByActivo(Boolean active);
}
