package Management.Employee.controller;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Management.Employee.model.Employee;
import Management.Employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/search")
public ResponseEntity<List<Employee>> searchEmployeesByRole(@RequestParam(value = "role", required = false) String role) {
        List<Employee> employees = employeeService.searchEmployeesByRole(role);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/manager/{managerId}")
public ResponseEntity<List<Employee>> getEmployeesByManagerId(@PathVariable("managerId") Long managerId) {
        List<Employee> employees = employeeService.getEmployeesByManagerId(managerId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            if (employee == null || employee.getPersonId() == null
                    || employee.getRole() == null || employee.getRole().trim().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Employee createdEmployee = employeeService.createEmployee(employee);
            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        try {
            if (employee == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Employee existingEmployee = employeeService.getEmployeeById(id);
    
         
            if (employee.getPersonId() != null) {
                existingEmployee.setPersonId(employee.getPersonId());
            }
            if (employee.getRole() != null && !employee.getRole().trim().isEmpty()) {
                existingEmployee.setRole(employee.getRole());
            }
            if (employee.getSalary() != null) {
                existingEmployee.setSalary(employee.getSalary());
            }
            if (employee.getStartDate() != null) {
                existingEmployee.setStartDate(employee.getStartDate());
            }
    
           
            Employee updatedEmployee = employeeService.updateEmployee(id, existingEmployee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
