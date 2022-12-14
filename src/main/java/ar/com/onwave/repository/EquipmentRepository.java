package ar.com.onwave.repository;

import ar.com.onwave.repository.model.EquipmentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<EquipmentModel, Long> {
    List<EquipmentModel> findByImeiContainsAndActivo(String keyword, Boolean activo);
    List<EquipmentModel> findByActivo(Boolean active);
    Page<EquipmentModel> findEquipmentModelsByActivo(Boolean active, Pageable pageable);
}
