package ar.com.onwave.repository;

import ar.com.onwave.repository.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository <EmployeeModel, Long> {
}
