package ar.com.onwave.service;

import ar.com.onwave.repository.model.BusinessModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BusinessService {
    List<BusinessModel> getBusinesses(String keyword, Boolean activo);
    void addBusiness(BusinessModel businessModel);
    void removeBusiness(BusinessModel businessModel);
    BusinessModel getBusiness(BusinessModel businessModel);
    Page<BusinessModel> findPage(int pageNumber);
    Page<BusinessModel> findAllWithSort(String field, String direction, int pageNumber);
}
