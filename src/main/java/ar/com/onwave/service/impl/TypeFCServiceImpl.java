package ar.com.onwave.service.impl;

import ar.com.onwave.repository.TypeFCRepository;
import ar.com.onwave.repository.model.TypeFCModel;
import ar.com.onwave.service.TypeFCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeFCServiceImpl implements TypeFCService {

    @Autowired
    private TypeFCRepository typeFCRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TypeFCModel> getTypes() {
        return typeFCRepository.findAll();
    }
}
