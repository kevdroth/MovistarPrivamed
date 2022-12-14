package ar.com.onwave.service;

import ar.com.onwave.repository.model.EquipmentModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EquipmentService {
    List<EquipmentModel> getEquipments(String keyword, Boolean activo);
    void addEquipment(EquipmentModel equipmentModel);
    void removeEquipment(EquipmentModel equipmentModel);
    EquipmentModel getEquipment(EquipmentModel equipmentModel);
    Page<EquipmentModel> findPage(int pageNumber);
    Page<EquipmentModel> findAllWithSort(String field, String direction, int pageNumber);
}
