package ar.com.onwave.repository;

import ar.com.onwave.repository.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDao extends JpaRepository <EmployeeModel, Long> {
    List<EmployeeModel> findByNombreContainsAndActivo(String keyword, Boolean activo);
    List<EmployeeModel> findByActivo(Boolean active);

}
