package ar.com.onwave.service.impl;

import ar.com.onwave.repository.InvoiceDao;
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
    private InvoiceDao invoiceDao;

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceModel> getInvoices(String keyword, Boolean activo) {
        if(activo && keyword != null){
            return invoiceDao.findByNumeroContainsAndActivo(keyword, activo);
        }else if (activo){
            return invoiceDao.findByActivo(true);
        }else if (activo == false){
            return invoiceDao.findAll();
        }else{
            return invoiceDao.findAll();
        }
    }

    @Override
    @Transactional
    public void addInvoice(InvoiceModel invoiceModel) {
        invoiceDao.save(invoiceModel);
    }

    @Override
    @Transactional
    public void removeInvoice(InvoiceModel invoiceModel) {
        invoiceDao.delete(invoiceModel);
    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceModel getInvoice(InvoiceModel invoiceModel) {
        return invoiceDao.findById(invoiceModel.getId()).orElse(null);
    }

    @Override
    public Page<InvoiceModel> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 11);
        return invoiceDao.findInvoiceModelsByActivo(true, pageable);
    }

    @Override
    public Page<InvoiceModel> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber -1, 11, sort);
        return invoiceDao.findInvoiceModelsByActivo(true, pageable);
    }
}
