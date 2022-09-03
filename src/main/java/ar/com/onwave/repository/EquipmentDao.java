package ar.com.onwave.repository;

import ar.com.onwave.repository.model.EquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentDao extends JpaRepository<EquipmentModel, Long> {

}
