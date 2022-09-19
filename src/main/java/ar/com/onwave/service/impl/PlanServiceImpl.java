package ar.com.onwave.service.impl;

import ar.com.onwave.repository.PlanDao;
import ar.com.onwave.repository.model.PlanModel;
import ar.com.onwave.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanDao planDao;

    @Override
    @Transactional(readOnly = true)
    public List<PlanModel> getPlans(String keyword, Boolean activo) {
        if(activo && keyword != null){
            return planDao.findByNombreContainsAndActivo(keyword, activo);
        }else if (activo){
            return planDao.findByActivo(true);
        }else if (activo == false){
            return planDao.findAll();
        }else{
            return planDao.findAll();
        }
    }

    @Override
    @Transactional
    public void addPlan(PlanModel planModel) {
        planDao.save(planModel);
    }

    @Override
    @Transactional
    public void removePlan(PlanModel planModel) {
        planDao.delete(planModel);
    }

    @Override
    @Transactional(readOnly = true)
    public PlanModel getPlan(PlanModel planModel) {
        return planDao.findById(planModel.getId()).orElse(null);
    }

    @Override
    public Page<PlanModel> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 10);
        return planDao.findAll(pageable);
    }

    @Override
    public Page<PlanModel> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber -1, 10, sort);
        return planDao.findAll(pageable);
    }
}
