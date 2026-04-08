package Management.Employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Management.Employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByRoleContainingIgnoreCase(String role);
    List<Employee> findByManagerId(Long managerId);
}
