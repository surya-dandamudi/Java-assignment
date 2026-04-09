package Management.Employee.service;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Management.Employee.model.Person;
import Management.Employee.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

   
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

  
    public Person getPersonById(Long id) {
        return personRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Person not found"));
    }

    public Person createPerson(Person person) {
        
        return personRepository.save(person);
    }

  
    public Person updatePerson(Long id, Person temp) {
        Person cur = personRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        cur.setName(temp.getName());
        cur.setEmail(temp.getEmail());
        cur.setPhoneNumber(temp.getPhoneNumber());

        return personRepository.save(cur);
    }

    public void deletePerson(Long id) {
        if (!personRepository.existsById(id)) {
            throw new EntityNotFoundException("Person not found");
        }
        personRepository.deleteById(id);
    }
}
