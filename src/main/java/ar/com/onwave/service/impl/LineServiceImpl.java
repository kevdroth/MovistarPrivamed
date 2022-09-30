package ar.com.onwave.service.impl;

import ar.com.onwave.repository.LineRepository;
import ar.com.onwave.repository.model.LineModel;
import ar.com.onwave.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LineServiceImpl implements LineService {

    @Autowired
    private LineRepository lineRepository;

    @Override
    @Transactional(readOnly = true)
    public List<LineModel> getLines(String keyword, Boolean activo) {
        if(activo && keyword != null){
            return lineRepository.findByNumeroContainsAndActivo(keyword, activo);
        }else if (activo){
            return lineRepository.findByActivo(true);
        }else if (activo == false){
            return lineRepository.findAll();
        }else{
            return lineRepository.findAll();
        }
    }

    @Override
    @Transactional
    public void addLine(LineModel lineModel) {
        lineRepository.save(lineModel);
    }

    @Override
    @Transactional
    public void removeLine(LineModel lineModel) {
        lineRepository.delete(lineModel);
    }

    @Override
    @Transactional(readOnly = true)
    public LineModel getLine(Long id) {
        return lineRepository.findById(id).orElse(null);
    }

    @Override
    public Page<LineModel> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 11);
        return lineRepository.findLineModelsByActivo(true, pageable);
    }

    @Override
    public Page<LineModel> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber -1, 11, sort);
        return lineRepository.findLineModelsByActivo(true, pageable);
    }
}
