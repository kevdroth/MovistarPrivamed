package ar.com.onwave.repository;

import ar.com.onwave.repository.model.InvoiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceDao extends JpaRepository<InvoiceModel, Long> {
    List<InvoiceModel> findByNumeroContainsAndActivo(String keyword, Boolean activo);
    List<InvoiceModel> findByActivo(Boolean active);
    Page<InvoiceModel> findInvoiceModelsByActivo(Boolean active, Pageable pageable);
}
