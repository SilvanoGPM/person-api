package com.github.skyg0d.PersonAPI.controller;

import com.github.skyg0d.PersonAPI.entity.Person;
import com.github.skyg0d.PersonAPI.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/people")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        Person savedPerson = personService.save(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

}
