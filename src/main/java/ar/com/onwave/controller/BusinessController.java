package ar.com.onwave.controller;

import ar.com.onwave.repository.model.BusinessModel;
import ar.com.onwave.service.BusinessService;
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
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @GetMapping("/listarEmpresas")
        public String getAllPages(Model model,
                                  @Param("keyword") String keyword,
                                  @RequestParam(defaultValue="true") boolean isChecked){
        var businessModel = businessService.getBusinesses(keyword, isChecked);
        model.addAttribute("businessModel", businessModel);
            return getOnePage(model, 1);
        }

    @GetMapping("/listarEmpresas/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<BusinessModel> page = businessService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<BusinessModel> businessModels = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("businessModels", businessModels);

        return "empresas";
    }

    @GetMapping("/listarEmpresas/page/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @PathVariable String field,
                                  @PathParam ("sortDir") String sortDir){
        Page<BusinessModel> page = businessService.findAllWithSort(field, sortDir, currentPage);
        List<BusinessModel> businessModels = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("businessModels", businessModels);

        return "empresas";
    }

    @GetMapping("/agregarEmpresa")
    public String agregar(BusinessModel businessModel){
        return "modificarEmpresas";
    }

    @PostMapping("/guardarEmpresas")
    public String guardar(@Valid BusinessModel businessModel, Errors errores){
        if(errores.hasErrors()){
            return "modificarEmpresas";
        }
        businessService.addBusiness(businessModel);
        return "redirect:/listarEmpresas";
    }

    @GetMapping("/editarEmpresas/{id}")
    public String editar(BusinessModel businessModel, Model model){
        businessModel = businessService.getBusiness(businessModel);
        model.addAttribute("businessModel", businessModel);
        return "modificarEmpresas";
    }

    @GetMapping("/eliminarEmpresas")
    public String eliminar(BusinessModel businessModel){
        businessService.removeBusiness(businessModel);
        return "redirect:/listarEmpresas";
    }

}
