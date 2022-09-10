package ar.com.onwave.service.impl;

import ar.com.onwave.repository.LineDao;
import ar.com.onwave.repository.model.LineModel;
import ar.com.onwave.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LineServiceImpl implements LineService {

    @Autowired
    private LineDao lineDao;

    @Override
    @Transactional(readOnly = true)
    public List<LineModel> getLines(String keyword, Boolean activo) {
        if(activo && keyword != null){
            return lineDao.findByNumeroContainsAndActivo(keyword, activo);
        }else if (activo){
            return lineDao.findByActivo(true);
        }else if (activo == false){
            return lineDao.findAll();
        }else{
            return lineDao.findAll();
        }
    }

    @Override
    @Transactional
    public void addLine(LineModel lineModel) {
        lineDao.save(lineModel);
    }

    @Override
    @Transactional
    public void removeLine(LineModel lineModel) {
        lineDao.delete(lineModel);
    }

    @Override
    @Transactional(readOnly = true)
    public LineModel getLine(Long id) {
        return lineDao.findById(id).orElse(null);
    }
}
