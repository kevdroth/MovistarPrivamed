package ar.com.onwave.service.impl;

import ar.com.onwave.repository.EmployeeDao;
import ar.com.onwave.repository.model.EmployeeModel;
import ar.com.onwave.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeModel> getEmployees(String keyword, Boolean activo) {
        if(activo && keyword != null){
            return employeeDao.findByNombreContainsAndActivo(keyword, activo);
        }else if (activo){
            return employeeDao.findByActivo(true);
        }else if (activo == false){
            return employeeDao.findAll();
        }else{
            return employeeDao.findAll();
        }
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

    @Override
    public Page<EmployeeModel> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 10);
        return employeeDao.findEmployeeModelsByActivo(true, pageable);
    }

    @Override
    public Page<EmployeeModel> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber -1, 10, sort);
        return employeeDao.findEmployeeModelsByActivo(true, pageable);
    }
}
