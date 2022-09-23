package ar.com.onwave.service.impl;

import ar.com.onwave.repository.BusinessDao;
import ar.com.onwave.repository.model.BusinessModel;
import ar.com.onwave.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessDao businessDao;

    @Override
    @Transactional(readOnly = true)
    public List<BusinessModel> getBusinesses(String keyword, Boolean activo) {
        if(activo && keyword != null){
            return businessDao.findByNombreContainsAndActivo(keyword, activo);
        }else if (activo){
            return businessDao.findByActivo(true);
        }else if (activo == false){
            return businessDao.findAll();
        }else{
            return businessDao.findAll();
        }
    }

    @Override
    public void addBusiness(BusinessModel businessModel) {
        businessDao.save(businessModel);
    }

    @Override
    public void removeBusiness(BusinessModel businessModel) {
        businessDao.delete(businessModel);
    }

    @Override
    @Transactional(readOnly = true)
    public BusinessModel getBusiness(BusinessModel businessModel) {
        return businessDao.findById(businessModel.getId()).orElse(null);
    }

    @Override
    public Page<BusinessModel> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 11);
        return businessDao.findBusinessModelsByActivo(true, pageable);
    }

    @Override
    public Page<BusinessModel> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber -1, 11, sort);
        return businessDao.findBusinessModelsByActivo(true, pageable);
    }
}
