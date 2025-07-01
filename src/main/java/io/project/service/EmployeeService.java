package io.project.service;

import io.project.DTO.emp.EmployeeCreateDTO;
import io.project.DTO.emp.EmployeeUpdateDTO;
import io.project.DTO.emp.EmployeeDTO;
import io.project.mapper.EmployeeMapper;
import io.project.model.Employee;
import io.project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<EmployeeDTO> getAll() {
        var list = employeeRepository.findAll().stream()
                .map(s -> employeeMapper.map(s))
                .toList();
        return list;
    }


    public Employee addEmployee(EmployeeCreateDTO employeeCreateDTO) {
        var emp = employeeMapper.map(employeeCreateDTO);
        return employeeRepository.save(emp);
    }

    public void removeEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDTO getEmployeeById(Long id) {
        var emp = employeeRepository.findById(id).orElse(null);
        return employeeMapper.map(emp);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeUpdateDTO updateDTO){
        var emp = employeeRepository.findById(id).orElse(null);
        employeeMapper.update(updateDTO, emp);
        employeeRepository.save(emp);
        return employeeMapper.map(emp);
    }
}
