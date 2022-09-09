package ar.com.onwave.controller;

import ar.com.onwave.repository.model.BusinessModel;
import ar.com.onwave.service.BusinessService;
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
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @GetMapping("/listarEmpresas")
    public String inicio(Model model, @Param("keyword") String keyword, @RequestParam(defaultValue="true") boolean isChecked){
        var businessModel = businessService.getBusinesses(keyword);
        var activeBusinessModel = businessService.getActiveBusinesses(isChecked);
        model.addAttribute("activeBusinessModel", activeBusinessModel);
        model.addAttribute("businessModel", businessModel);
        model.addAttribute("keyword", keyword);
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
