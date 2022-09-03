package ar.com.onwave.controller;

import ar.com.onwave.repository.model.EquipmentModel;
import ar.com.onwave.service.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @GetMapping("/listarEquipos")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        var equipmentModel = equipmentService.getEquipments();
        log.info("usuario que hizo login:" + user);
        model.addAttribute("equipmentModel", equipmentModel);
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
