package ar.com.onwave.repository;

import ar.com.onwave.repository.model.InvoiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceModel, Long> {
    List<InvoiceModel> findByNumeroAndActivo(String keyword, Boolean activo);
    List<InvoiceModel> findByActivo(Boolean activo);
    Page<InvoiceModel> findInvoiceModelsByActivo(Boolean active, Pageable pageable);
}
