package Management.Employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Management.Employee.model.Employee;
import Management.Employee.model.Person;
import Management.Employee.repository.EmployeeRepository;
import Management.Employee.repository.PersonRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonRepository personRepository;

   
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

   
    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

   
    public Employee createEmployee(Employee employee) {
        if (employee.getPersonId() != null) {
            Person person = personRepository.findById(employee.getPersonId())
                .orElseThrow(() -> new RuntimeException("Person with id " + employee.getPersonId() + " not found"));
        } else {
            throw new RuntimeException("Person ID must be provided");
        }
        return employeeRepository.save(employee);
    }

  
    public Employee updateEmployee(Long employeeId, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new RuntimeException("Employee not found"));

        existingEmployee.setRole(updatedEmployee.getRole());
        existingEmployee.setSalary(updatedEmployee.getSalary());
        existingEmployee.setStartDate(updatedEmployee.getStartDate());
        existingEmployee.setManagerId(updatedEmployee.getManagerId());
        existingEmployee.setPersonId(updatedEmployee.getPersonId()); 

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(employeeId);
    }
}
