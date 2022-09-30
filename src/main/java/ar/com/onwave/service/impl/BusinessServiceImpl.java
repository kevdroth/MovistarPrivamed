package ar.com.onwave.service.impl;

import ar.com.onwave.repository.BusinessRepository;
import ar.com.onwave.repository.model.BusinessModel;
import ar.com.onwave.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BusinessModel> getBusinesses(String keyword, Boolean activo) {
        if(activo && keyword != null){
            return businessRepository.findByNombreContainsAndActivo(keyword, activo);
        }else if (activo){
            return businessRepository.findByActivo(true);
        }else if (activo == false){
            return businessRepository.findAll();
        }else{
            return businessRepository.findAll();
        }
    }

    @Override
    public void addBusiness(BusinessModel businessModel) {
        businessRepository.save(businessModel);
    }

    @Override
    public void removeBusiness(BusinessModel businessModel) {
        businessRepository.delete(businessModel);
    }

    @Override
    @Transactional(readOnly = true)
    public BusinessModel getBusiness(BusinessModel businessModel) {
        return businessRepository.findById(businessModel.getId()).orElse(null);
    }

    @Override
    public Page<BusinessModel> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 11);
        return businessRepository.findBusinessModelsByActivo(true, pageable);
    }

    @Override
    public Page<BusinessModel> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber -1, 11, sort);
        return businessRepository.findBusinessModelsByActivo(true, pageable);
    }
}
