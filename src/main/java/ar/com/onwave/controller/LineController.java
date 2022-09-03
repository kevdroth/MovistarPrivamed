package ar.com.onwave.controller;

import ar.com.onwave.repository.model.LineModel;
import ar.com.onwave.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class LineController {

    @Autowired
    private LineService lineService;
    @Autowired
    private PlanService planService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BusinessService businessService;

    @GetMapping("/listarLineas")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        var lineModel = lineService.getLines();
        var planModel = planService.getPlans();
        var equipmentModel = equipmentService.getEquipments();
        var employeeModel = employeeService.getEmployees();
        var businessModel = businessService.getBusinesses();
        model.addAttribute("lineModel", lineModel);
        model.addAttribute("planModel", planModel);
        model.addAttribute("equipmentModel", equipmentModel);
        model.addAttribute("employeeModel", employeeModel);
        model.addAttribute("businessModel", businessModel);
        model.addAttribute("newLine", new LineModel());
        return "lineas";
    }

    @GetMapping("/agregarLineas")
    public String agregar(LineModel lineModel){
        return "modificarLineas";
    }

    @PostMapping("/guardarLineas")
    public String guardar(@ModelAttribute LineModel line, Errors errores){
        if(errores.hasErrors()){
            return "modificarLineas";
        }
        lineService.addLine(line);
        return "redirect:/listarLineas";
    }

    @GetMapping("/editarLineas/{id}")
    public String editar(@PathVariable("id") Long id, Model model){
        var lineModel = lineService.getLine(id);
        var planModel = planService.getPlans();
        var equipmentModel = equipmentService.getEquipments();
        var employeeModel = employeeService.getEmployees();
        var businessModel = businessService.getBusinesses();
        model.addAttribute("lineModel", lineModel);
        model.addAttribute("planModel", planModel);
        model.addAttribute("equipmentModel", equipmentModel);
        model.addAttribute("employeeModel", employeeModel);
        model.addAttribute("businessModel", businessModel);
        return "modificarLineas";
    }

    @GetMapping("/eliminarLineas/{id}")
    public String eliminar(@PathVariable("id") Long id){
        LineModel lineModel = lineService.getLine(id);
        lineService.removeLine(lineModel);
        return "redirect:/listarLineas";
    }

}
