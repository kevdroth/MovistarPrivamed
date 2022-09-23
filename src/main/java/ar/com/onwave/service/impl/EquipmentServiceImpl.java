package ar.com.onwave.service.impl;

import ar.com.onwave.repository.EquipmentDao;
import ar.com.onwave.repository.model.EquipmentModel;
import ar.com.onwave.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentDao equipmentDao;

    @Override
    @Transactional(readOnly = true)
    public List<EquipmentModel> getEquipments(String keyword, Boolean activo) {
        if(activo && keyword != null){
            return equipmentDao.findByImeiContainsAndActivo(keyword, activo);
        }else if (activo){
            return equipmentDao.findByActivo(true);
        }else if (activo == false){
            return equipmentDao.findAll();
        }else{
            return equipmentDao.findAll();
        }
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

    @Override
    public Page<EquipmentModel> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 11);
        return equipmentDao.findEquipmentModelsByActivo(true, pageable);
    }

    @Override
    public Page<EquipmentModel> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber -1, 11, sort);
        return equipmentDao.findEquipmentModelsByActivo(true, pageable);
    }
}
