package ar.com.onwave.service.impl;

import ar.com.onwave.repository.BusinessDao;
import ar.com.onwave.repository.model.BusinessModel;
import ar.com.onwave.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessDao businessDao;

    @Override
    @Transactional(readOnly = true)
    public List<BusinessModel> getBusinesses() {
        return businessDao.findAll();
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
}
