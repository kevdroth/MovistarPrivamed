package ar.com.onwave.service;

import ar.com.onwave.repository.model.LineModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LineService {
    List<LineModel> getLines(String keyword, Boolean activo);
    void addLine(LineModel lineModel);
    void removeLine(LineModel lineModel);
    LineModel getLine(Long id);
    Page<LineModel> findPage(int pageNumber);
    Page<LineModel> findAllWithSort(String field, String direction, int pageNumber);
}