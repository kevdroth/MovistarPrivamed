package ar.com.onwave.service;

import ar.com.onwave.repository.model.EmployeeModel;

import java.util.List;

public interface EmployeeService {

    List<EmployeeModel> getEmployees(String keyword);

    List<EmployeeModel> getActiveEmployees(boolean isChecked);

    void addEmployee (EmployeeModel employeeModel);

    void removeEmployee (EmployeeModel employeeModel);

    EmployeeModel getEmployee (EmployeeModel employeeModel);
}
