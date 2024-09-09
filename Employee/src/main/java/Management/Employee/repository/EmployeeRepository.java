package Management.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Management.Employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
