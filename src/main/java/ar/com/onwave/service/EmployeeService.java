package ar.com.onwave.service;

import ar.com.onwave.repository.model.EmployeeModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<EmployeeModel> getEmployees(String keyword, Boolean activo);
    void addEmployee (EmployeeModel employeeModel);
    void removeEmployee (EmployeeModel employeeModel);
    EmployeeModel getEmployee (EmployeeModel employeeModel);
    Page<EmployeeModel> findPage(int pageNumber);
    Page<EmployeeModel> findAllWithSort(String field, String direction, int pageNumber);
}
