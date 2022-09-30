package ar.com.onwave.service.impl;

import ar.com.onwave.repository.LetterFCRepository;
import ar.com.onwave.repository.model.LetterFCModel;
import ar.com.onwave.service.LetterFCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LetterFCServiceImpl implements LetterFCService {

    @Autowired
    private LetterFCRepository letterFCRepository;
    @Override
    @Transactional(readOnly = true)
    public List<LetterFCModel> getLetters() {
        return letterFCRepository.findAll();
    }
}
