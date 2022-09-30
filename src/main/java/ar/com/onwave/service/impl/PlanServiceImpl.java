package ar.com.onwave.service.impl;

import ar.com.onwave.repository.PlanRepository;
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
    private PlanRepository planRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PlanModel> getPlans(String keyword, Boolean activo) {
        if(activo && keyword != null){
            return planRepository.findByNombreContainsAndActivo(keyword, activo);
        }else if (activo){
            return planRepository.findByActivo(true);
        }else if (activo == false){
            return planRepository.findAll();
        }else{
            return planRepository.findAll();
        }
    }

    @Override
    @Transactional
    public void addPlan(PlanModel planModel) {
        planRepository.save(planModel);
    }

    @Override
    @Transactional
    public void removePlan(PlanModel planModel) {
        planRepository.delete(planModel);
    }

    @Override
    @Transactional(readOnly = true)
    public PlanModel getPlan(PlanModel planModel) {
        return planRepository.findById(planModel.getId()).orElse(null);
    }

    @Override
    public Page<PlanModel> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 11);
        return planRepository.findPlanModelsByActivo(true, pageable);
    }

    @Override
    public Page<PlanModel> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber -1, 11, sort);
        return planRepository.findPlanModelsByActivo(true, pageable);
    }
}
