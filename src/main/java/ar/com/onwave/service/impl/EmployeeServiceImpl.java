package ar.com.onwave.service.impl;

import ar.com.onwave.repository.EmployeeDao;
import ar.com.onwave.repository.model.EmployeeModel;
import ar.com.onwave.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeModel> getEmployees() {
        return employeeDao.findAll();
    }

    @Override
    @Transactional
    public void addEmployee(EmployeeModel employeeModel) {
        employeeDao.save(employeeModel);
    }

    @Override
    @Transactional
    public void removeEmployee(EmployeeModel employeeModel) {
        employeeDao.delete(employeeModel);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeModel getEmployee(EmployeeModel employeeModel) {
        return employeeDao.findById(employeeModel.getId()).orElse(null);
    }
}
