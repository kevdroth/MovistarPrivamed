package ar.com.onwave.service.impl;

import ar.com.onwave.repository.InvoiceRepository;
import ar.com.onwave.repository.model.InvoiceModel;
import ar.com.onwave.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceModel> getInvoices(String keyword, Boolean activo) {
        if (activo && keyword != null) {
            return invoiceRepository.findByNumeroAndActivo(keyword, activo);
        } else if (activo) {
            return invoiceRepository.findByActivo(true);
        } else if (activo == false) {
            return invoiceRepository.findAll();
        } else {
            return invoiceRepository.findAll();
        }
    }

        @Override
        @Transactional
        public void addInvoice (InvoiceModel invoiceModel){
            invoiceRepository.save(invoiceModel);
        }

        @Override
        @Transactional
        public void removeInvoice (InvoiceModel invoiceModel){
            invoiceRepository.delete(invoiceModel);
        }

        @Override
        @Transactional(readOnly = true)
        public InvoiceModel getInvoice (Long id){
            return invoiceRepository.findById(id).orElse(null);
        }

        @Override
        public Page<InvoiceModel> findPage ( int pageNumber){
            Pageable pageable = PageRequest.of(pageNumber - 1, 11);
            return invoiceRepository.findInvoiceModelsByActivo(true, pageable);
        }

        @Override
        public Page<InvoiceModel> findAllWithSort (String field, String direction,int pageNumber){
            Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(field).ascending() : Sort.by(field).descending();
            Pageable pageable = PageRequest.of(pageNumber - 1, 11, sort);
            return invoiceRepository.findInvoiceModelsByActivo(true, pageable);
        }
    }
