package ar.com.onwave.controller;

import ar.com.onwave.repository.model.InvoiceModel;
import ar.com.onwave.service.InvoiceService;
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
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/listarFacturacion")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }

    @GetMapping("/listarFacturacion/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<InvoiceModel> page = invoiceService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<InvoiceModel> invoiceModels = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("invoiceModels", invoiceModels);

        return "facturacion";
    }

    @GetMapping("/listarFacturacion/page/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @PathVariable String field,
                                  @PathParam("sortDir") String sortDir){
        Page<InvoiceModel> page = invoiceService.findAllWithSort(field, sortDir, currentPage);
        List<InvoiceModel> invoiceModels = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("invoiceModels", invoiceModels);

        return "facturacion";
    }

    @GetMapping("/agregarFacturacion")
    public String agregar(InvoiceModel invoiceModel){
        return "modificarFacturacion";
    }

    @PostMapping("/guardarFacturacion")
    public String guardar(@Valid InvoiceModel invoiceModel, Errors errores){
        if(errores.hasErrors()){
            return "modificarFacturacion";
        }
        invoiceService.addInvoice(invoiceModel);
        return "redirect:/listarFacturacion";
    }

    @GetMapping("/editarFacturacion/{id}")
    public String editar(InvoiceModel invoiceModel, Model model){
        invoiceModel = invoiceService.getInvoice(invoiceModel);
        model.addAttribute("invoiceModel", invoiceModel);
        return "modificarFacturacion";
    }

    @GetMapping("/eliminarFacturacion")
    public String eliminar(InvoiceModel invoiceModel){
        invoiceService.removeInvoice(invoiceModel);
        return "redirect:/listarFacturacion";
    }
}
