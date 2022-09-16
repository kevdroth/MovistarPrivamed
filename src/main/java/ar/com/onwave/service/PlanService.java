package ar.com.onwave.service;

import ar.com.onwave.repository.model.PlanModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PlanService {
    List<PlanModel> getPlans(String keyword, Boolean activo);
    void addPlan(PlanModel planModel);
    void removePlan(PlanModel planModel);
    PlanModel getPlan(PlanModel planModel);
    Page<PlanModel> findPage(int pageNumber);
}
