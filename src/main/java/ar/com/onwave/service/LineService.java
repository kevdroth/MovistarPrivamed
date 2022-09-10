package ar.com.onwave.service;

import ar.com.onwave.repository.model.LineModel;

import java.util.List;

public interface LineService {
    List<LineModel> getLines(String keyword, Boolean activo);
    void addLine(LineModel lineModel);
    void removeLine(LineModel lineModel);
    LineModel getLine(Long id);
}
