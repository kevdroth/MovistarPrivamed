package ar.com.onwave.controller;

import ar.com.onwave.repository.model.LineModel;
import ar.com.onwave.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public String inicio(Model model, @Param("keyword") String keyword,@RequestParam(defaultValue="true") boolean isChecked){
        var lineModel = lineService.getLines(keyword);
        var planModel = planService.getPlans(keyword);
        var equipmentModel = equipmentService.getEquipments(keyword);
        var employeeModel = employeeService.getEmployees(keyword);
        var businessModel = businessService.getBusinesses(keyword);
        var activeLineModel = lineService.getActiveLines(isChecked);
        model.addAttribute("activeLineModel", activeLineModel);
        model.addAttribute("lineModel", lineModel);
        model.addAttribute("planModel", planModel);
        model.addAttribute("equipmentModel", equipmentModel);
        model.addAttribute("employeeModel", employeeModel);
        model.addAttribute("businessModel", businessModel);
        model.addAttribute("keyword", keyword);
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
    public String editar(@PathVariable("id") Long id, Model model, @Param("keyword") String keyword, @RequestParam(defaultValue="true") boolean isChecked){
        var lineModel = lineService.getLine(id);
        var planModel = planService.getPlans(keyword);
        var equipmentModel = equipmentService.getEquipments(keyword);
        var employeeModel = employeeService.getEmployees(keyword);
        var businessModel = businessService.getBusinesses(keyword);
        var activeLineModel = lineService.getActiveLines(isChecked);
        model.addAttribute("activeLineModel", activeLineModel);
        model.addAttribute("lineModel", lineModel);
        model.addAttribute("planModel", planModel);
        model.addAttribute("equipmentModel", equipmentModel);
        model.addAttribute("employeeModel", employeeModel);
        model.addAttribute("businessModel", businessModel);
        model.addAttribute("keyword", keyword);
        return "modificarLineas";
    }

    @GetMapping("/eliminarLineas/{id}")
    public String eliminar(@PathVariable("id") Long id){
        LineModel lineModel = lineService.getLine(id);
        lineService.removeLine(lineModel);
        return "redirect:/listarLineas";
    }

}
