package ar.com.onwave.service;

import ar.com.onwave.repository.model.BusinessModel;

import java.util.List;

public interface BusinessService {

    List<BusinessModel> getBusinesses();
    void addBusiness(BusinessModel businessModel);
    void removeBusiness(BusinessModel businessModel);
    BusinessModel getBusiness(BusinessModel businessModel);

}
