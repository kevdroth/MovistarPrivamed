package ar.com.onwave.repository;

import ar.com.onwave.repository.model.EmployeeModel;
import ar.com.onwave.repository.model.EquipmentModel;
import ar.com.onwave.repository.model.LineModel;
import ar.com.onwave.repository.model.PlanModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineDao extends JpaRepository<LineModel, Long> {
}
