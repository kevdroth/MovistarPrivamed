package ar.com.onwave.service;

import ar.com.onwave.repository.model.InvoiceModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InvoiceService {
    List<InvoiceModel> getInvoices(String keyword, Boolean activo);
    void addInvoice(InvoiceModel invoiceModel);
    void removeInvoice(InvoiceModel invoiceModel);
    InvoiceModel getInvoice(InvoiceModel invoiceModel);
    Page<InvoiceModel> findPage(int pageNumber);
    Page<InvoiceModel> findAllWithSort(String field, String direction, int pageNumber);
}
