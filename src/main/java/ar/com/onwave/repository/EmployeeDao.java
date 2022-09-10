package ar.com.onwave.repository;

import ar.com.onwave.repository.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeDao extends JpaRepository <EmployeeModel, Long> {

    @Query("SELECT e FROM EmployeeModel e WHERE e.nombre LIKE %?1% and e.activo=true ")
    List<EmployeeModel> findAll(String keyword);

    @Query("SELECT e FROM EmployeeModel e WHERE e.activo=true ")
    List<EmployeeModel> findAll(boolean isChecked);

    List<EmployeeModel> findByNombreContainsAndActivo(String keyword, Boolean activo);
    List<EmployeeModel> findByActivo(Boolean active);

}
