package Management.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Management.Employee.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
