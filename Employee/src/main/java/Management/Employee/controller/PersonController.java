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
import org.springframework.web.bind.annotation.RestController;

import Management.Employee.model.Person;
import Management.Employee.service.PersonService;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

  
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") Long id) {
        try {
            Person person = personService.getPersonById(id);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        try {
            if (person == null || person.getName() == null || person.getName().trim().isEmpty()
                    || person.getEmail() == null || person.getEmail().trim().isEmpty()
                    || person.getPhoneNumber() == null || person.getPhoneNumber().trim().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Person createdPerson = personService.createPerson(person);
            return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") Long id, @RequestBody Person person) {
        try {
            if (person == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Person existingPerson = personService.getPersonById(id);

            if (person.getName() != null && !person.getName().trim().isEmpty()) {
                existingPerson.setName(person.getName());
            }
            if (person.getEmail() != null && !person.getEmail().trim().isEmpty()) {
                existingPerson.setEmail(person.getEmail());
            }
            if (person.getPhoneNumber() != null && !person.getPhoneNumber().trim().isEmpty()) {
                existingPerson.setPhoneNumber(person.getPhoneNumber());
            }
    
         
            Person updatedPerson = personService.updatePerson(id, existingPerson);
            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Long id) {
        try {
            personService.deletePerson(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
