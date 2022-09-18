package ar.com.onwave.controller;

import ar.com.onwave.repository.model.EquipmentModel;
import ar.com.onwave.service.EquipmentService;
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
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @GetMapping("/listarEquipos")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }

    @GetMapping("/listarEquipos/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<EquipmentModel> page = equipmentService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<EquipmentModel> equipmentModels = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("equipmentModels", equipmentModels);

        return "equipos";
    }

    @GetMapping("/listarEquipos/page/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @PathVariable String field,
                                  @PathParam("sortDir") String sortDir){
        Page<EquipmentModel> page = equipmentService.findAllWithSort(field, sortDir, currentPage);
        List<EquipmentModel> equipmentModels = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("equipmentModels", equipmentModels);

        return "equipos";
    }

    @GetMapping("/agregarEquipos")
    public String agregar(EquipmentModel equipmentModel){
        return "modificarEquipos";
    }

    @PostMapping("/guardarEquipos")
    public String guardar(@Valid EquipmentModel equipmentModel, Errors errores){
        if(errores.hasErrors()){
            return "modificarEquipos";
        }
        equipmentService.addEquipment(equipmentModel);
        return "redirect:/listarEquipos";
    }

    @GetMapping("/editarEquipos/{id}")
    public String editar(EquipmentModel equipmentModel, Model model){
        equipmentModel = equipmentService.getEquipment(equipmentModel);
        model.addAttribute("equipmentModel", equipmentModel);
        return "modificarEquipos";
    }

    @GetMapping("/eliminarEquipos")
    public String eliminar(EquipmentModel equipmentModel){
        equipmentService.removeEquipment(equipmentModel);
        return "redirect:/listarEquipos";
    }

}
