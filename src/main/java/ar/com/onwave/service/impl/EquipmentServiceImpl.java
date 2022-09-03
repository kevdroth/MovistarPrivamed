package ar.com.onwave.service.impl;

import ar.com.onwave.repository.EquipmentDao;
import ar.com.onwave.repository.model.EquipmentModel;
import ar.com.onwave.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentDao equipmentDao;

    @Override
    @Transactional(readOnly = true)
    public List<EquipmentModel> getEquipments() {
        return equipmentDao.findAll();
    }

    @Override
    @Transactional
    public void addEquipment(EquipmentModel equipmentModel) {
        equipmentDao.save(equipmentModel);
    }

    @Override
    @Transactional
    public void removeEquipment(EquipmentModel equipmentModel) {
        equipmentDao.delete(equipmentModel);
    }

    @Override
    @Transactional(readOnly = true)
    public EquipmentModel getEquipment(EquipmentModel equipmentModel) {
        return equipmentDao.findById(equipmentModel.getId()).orElse(null);
    }
}
