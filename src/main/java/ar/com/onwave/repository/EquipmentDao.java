package ar.com.onwave.repository;

import ar.com.onwave.repository.model.EquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentDao extends JpaRepository<EquipmentModel, Long> {
    List<EquipmentModel> findByImeiContainsAndActivo(String keyword, Boolean activo);
    List<EquipmentModel> findByActivo(Boolean active);

}
