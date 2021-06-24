package com.github.skyg0d.PersonAPI.controller;

import com.github.skyg0d.PersonAPI.dto.request.PersonDTO;
import com.github.skyg0d.PersonAPI.entity.Person;
import com.github.skyg0d.PersonAPI.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/people")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        List<Person> allPeople = personService.getAll();
        return ResponseEntity.ok(allPeople);
    }

    @GetMapping("{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        Person personDTO = personService.findByIdOrElseThrowPersonNotFoundException(id);
        return ResponseEntity.ok(personDTO);
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody @Valid PersonDTO personDTO) {
        Person savedPerson = personService.save(personDTO);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid PersonDTO personDTO) {
        personService.replace(personDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
