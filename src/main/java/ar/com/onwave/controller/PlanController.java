package ar.com.onwave.controller;

import ar.com.onwave.repository.model.LineModel;
import ar.com.onwave.repository.model.PlanModel;
import ar.com.onwave.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@Slf4j
public class PlanController {

    @Autowired
    private PlanService planService;

    /*@GetMapping("/listarPlanes")
    public String inicio(Model model, @Param("keyword") String keyword, @RequestParam(defaultValue="true") boolean isChecked){
        var planModel = planService.getPlans(keyword, isChecked);
        model.addAttribute("planModel", planModel);
        model.addAttribute("keyword", keyword);
        return "planes";
    }*/

    @GetMapping("/listarPlanes")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }

    @GetMapping("/listarPlanes/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<PlanModel> page = planService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<PlanModel> planModels = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("planModels", planModels);
        return "planes";
    }

    @GetMapping("/listarPlanes/page/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @PathVariable String field,
                                  @PathParam("sortDir") String sortDir){
        Page<PlanModel> page = planService.findAllWithSort(field, sortDir, currentPage);
        List<PlanModel> planModels = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("planModels", planModels);

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
