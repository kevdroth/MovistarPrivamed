package ar.com.onwave.service.impl;

import ar.com.onwave.repository.EmployeeRepository;
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
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeModel> getEmployees(String keyword, Boolean activo) {
        if(activo && keyword != null){
            return employeeRepository.findByNombreContainsAndActivo(keyword, activo);
        }else if (activo){
            return employeeRepository.findByActivo(true);
        }else if (activo == false){
            return employeeRepository.findAll();
        }else{
            return employeeRepository.findAll();
        }
    }

    @Override
    @Transactional
    public void addEmployee(EmployeeModel employeeModel) {
        employeeRepository.save(employeeModel);
    }

    @Override
    @Transactional
    public void removeEmployee(EmployeeModel employeeModel) {
        employeeRepository.delete(employeeModel);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeModel getEmployee(EmployeeModel employeeModel) {
        return employeeRepository.findById(employeeModel.getId()).orElse(null);
    }

    @Override
    public Page<EmployeeModel> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 11);
        return employeeRepository.findEmployeeModelsByActivo(true, pageable);
    }

    @Override
    public Page<EmployeeModel> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber -1, 11, sort);
        return employeeRepository.findEmployeeModelsByActivo(true, pageable);
    }

}
