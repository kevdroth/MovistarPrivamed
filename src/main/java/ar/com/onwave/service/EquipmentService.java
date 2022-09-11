package ar.com.onwave.service;

import ar.com.onwave.repository.model.EquipmentModel;

import java.util.List;

public interface EquipmentService {
    List<EquipmentModel> getEquipments(String keyword, Boolean activo);
    void addEquipment(EquipmentModel equipmentModel);
    void removeEquipment(EquipmentModel equipmentModel);
    EquipmentModel getEquipment(EquipmentModel equipmentModel);
}
