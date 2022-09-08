package ar.com.onwave.controller;

import ar.com.onwave.repository.model.EmployeeModel;
import ar.com.onwave.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/listarEmpleados")
    public String inicio(Model model, @AuthenticationPrincipal User user, @Param("keyword") String keyword){
        var employeeModel = employeeService.getEmployees(keyword);
        log.info("usuario que hizo login:" + user);
        model.addAttribute("employeeModel", employeeModel);
        model.addAttribute("keyword", keyword);
        return "empleados";
    }

    @GetMapping("/agregarEmpleado")
    public String agregar(EmployeeModel employeeModel){
        return "modificarEmpleados";
    }

    @PostMapping("/guardarEmpleados")
    public String guardar(@Valid EmployeeModel employeeModel, Errors errores){
        if(errores.hasErrors()){
            return "modificarEmpleados";
        }
        employeeService.addEmployee(employeeModel);
        return "redirect:/listarEmpleados";
    }

    @GetMapping("/editarEmpleados/{id}")
    public String editar(EmployeeModel employeeModel, Model model){
        employeeModel = employeeService.getEmployee(employeeModel);
        model.addAttribute("employeeModel", employeeModel);
        return "modificarEmpleados";
    }

    @GetMapping("/eliminarEmpleados")
    public String eliminar(EmployeeModel employeeModel){
        employeeService.removeEmployee(employeeModel);
        return "redirect:/listarEmpleados";
    }

}

