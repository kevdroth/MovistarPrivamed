package ar.com.onwave.service;

import ar.com.onwave.repository.model.EmployeeModel;

import java.util.List;

public interface EmployeeService {

    List<EmployeeModel> getEmployees();

    void addEmployee (EmployeeModel employeeModel);

    void removeEmployee (EmployeeModel employeeModel);

    EmployeeModel getEmployee (EmployeeModel employeeModel);
}