package ar.com.onwave.service;

import ar.com.onwave.repository.model.EmployeeModel;
import ar.com.onwave.repository.model.EquipmentModel;
import ar.com.onwave.repository.model.LineModel;
import ar.com.onwave.repository.model.PlanModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LineService {
    List<LineModel> getLines(String keyword);
    void addLine(LineModel lineModel);
    void removeLine(LineModel lineModel);
    LineModel getLine(Long id);
}
