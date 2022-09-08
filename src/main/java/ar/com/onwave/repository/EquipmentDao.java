package ar.com.onwave.repository;

import ar.com.onwave.repository.model.EquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipmentDao extends JpaRepository<EquipmentModel, Long> {
    @Query("SELECT e FROM EquipmentModel e WHERE" + " CONCAT(e.imeiRegistrado, e.imeiTrafica, e.marcaTrafica, e.modeloTrafica, e.registrado, e.sim)" +
            " LIKE %?1% and e.estado=0")
    List<EquipmentModel> findAll(String keyword);

}
