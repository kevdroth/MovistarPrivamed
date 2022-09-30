package ar.com.onwave.repository;

import ar.com.onwave.repository.model.PlanModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<PlanModel, Long> {
    List<PlanModel> findByNombreContainsAndActivo(String keyword, Boolean activo);
    List<PlanModel> findByActivo(Boolean active);
    Page<PlanModel> findPlanModelsByActivo(Boolean active, Pageable pageable);
}
