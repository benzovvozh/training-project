package io.project.controller;

import io.project.DTO.emp.EmployeeCreateDTO;
import io.project.DTO.emp.EmployeeUpdateDTO;
import io.project.DTO.emp.EmployeeDTO;
import io.project.model.Employee;
import io.project.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EmployeeDTO>> showAll() {
        var list = employeeService.getAll();
        return ResponseEntity.ok()
                .body(list);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO show(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@Valid @RequestBody EmployeeCreateDTO employeeCreateDTO) {
        return employeeService.addEmployee(employeeCreateDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        employeeService.removeEmployee(id);
    }
    @PutMapping(path = "/{id}")
    public EmployeeDTO update(@PathVariable("id") Long id, @Valid @RequestBody EmployeeUpdateDTO updateDTO){
        return employeeService.updateEmployee(id, updateDTO);
    }

}
