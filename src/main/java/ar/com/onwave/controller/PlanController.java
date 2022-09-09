package ar.com.onwave.controller;

import ar.com.onwave.repository.model.PlanModel;
import ar.com.onwave.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@Slf4j
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping("/listarPlanes")
    public String inicio(Model model, @Param("keyword") String keyword, @RequestParam(defaultValue="true") boolean isChecked){
        var planModel = planService.getPlans(keyword);
        var activePlanModel = planService.getActivePlans(isChecked);
        model.addAttribute("activePlanModel", activePlanModel);
        model.addAttribute("planModel", planModel);
        model.addAttribute("keyword", keyword);
        return "planes";
    }
    @GetMapping("/agregar")
    public String agregar(PlanModel planModel){
        return "modificarPlanes";
    }

    @PostMapping("/guardarPlan")
    public String guardar(@Valid PlanModel planModel, Errors errores){
        if(errores.hasErrors()){
            return "modificarPlanes";
        }
        planService.addPlan(planModel);
        return "redirect:/listarPlanes";
    }

    @GetMapping("/editarPlanes/{id}")
    public String editar(PlanModel planModel, Model model){
        planModel = planService.getPlan(planModel);
        model.addAttribute("planModel", planModel);
        return "modificarPlanes";
    }

    @GetMapping("/eliminarPlanes")
    public String eliminar(PlanModel planModel){
        planService.removePlan(planModel);
        return "redirect:/listarPlanes";
    }
}
