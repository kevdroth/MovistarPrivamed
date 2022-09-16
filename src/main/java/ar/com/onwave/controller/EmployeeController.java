package ar.com.onwave.controller;

import ar.com.onwave.repository.model.EmployeeModel;
import ar.com.onwave.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /*@GetMapping("/listarEmpleados")
    public String inicio(Model model, @Param("keyword") String keyword, @RequestParam(defaultValue="true") boolean isChecked){
        var employeeModel = employeeService.getEmployees(keyword, isChecked);
        model.addAttribute("employeeModel", employeeModel);
        model.addAttribute("keyword", keyword);
        return "empleados";
    }*/

    @GetMapping("/listarEmpleados")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }

    @GetMapping("/listarEmpleados/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<EmployeeModel> page = employeeService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<EmployeeModel> employeeModels = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("employeeModels", employeeModels);

        return "empleados";
    }

    @GetMapping("/listarEmpleados/page/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @PathVariable String field,
                                  @PathParam("sortDir") String sortDir){
        Page<EmployeeModel> page = employeeService.findAllWithSort(field, sortDir, currentPage);
        List<EmployeeModel> employeeModels = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("employeeModels", employeeModels);

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

