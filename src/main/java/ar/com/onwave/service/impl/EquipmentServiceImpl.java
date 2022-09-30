package ar.com.onwave.service.impl;

import ar.com.onwave.repository.EquipmentRepository;
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
    private EquipmentRepository equipmentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EquipmentModel> getEquipments(String keyword, Boolean activo) {
        if(activo && keyword != null){
            return equipmentRepository.findByImeiContainsAndActivo(keyword, activo);
        }else if (activo){
            return equipmentRepository.findByActivo(true);
        }else if (activo == false){
            return equipmentRepository.findAll();
        }else{
            return equipmentRepository.findAll();
        }
    }

    @Override
    @Transactional
    public void addEquipment(EquipmentModel equipmentModel) {
        equipmentRepository.save(equipmentModel);
    }

    @Override
    @Transactional
    public void removeEquipment(EquipmentModel equipmentModel) {
        equipmentRepository.delete(equipmentModel);
    }

    @Override
    @Transactional(readOnly = true)
    public EquipmentModel getEquipment(EquipmentModel equipmentModel) {
        return equipmentRepository.findById(equipmentModel.getId()).orElse(null);
    }

    @Override
    public Page<EquipmentModel> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 11);
        return equipmentRepository.findEquipmentModelsByActivo(true, pageable);
    }

    @Override
    public Page<EquipmentModel> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber -1, 11, sort);
        return equipmentRepository.findEquipmentModelsByActivo(true, pageable);
    }
}
