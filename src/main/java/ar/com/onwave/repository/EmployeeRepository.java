package ar.com.onwave.repository;

import ar.com.onwave.repository.model.EmployeeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository <EmployeeModel, Long> {
    List<EmployeeModel> findByNombreContainsAndActivo(String keyword, Boolean activo);
    List<EmployeeModel> findByActivo(Boolean active);
    Page<EmployeeModel> findEmployeeModelsByActivo(Boolean active, Pageable pageable);
}
