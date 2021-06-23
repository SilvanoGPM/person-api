package com.github.skyg0d.PersonAPI.service;

import com.github.skyg0d.PersonAPI.dto.request.PersonDTO;
import com.github.skyg0d.PersonAPI.entity.Person;
import com.github.skyg0d.PersonAPI.exception.PersonNotFoundException;

import java.util.List;

public interface PersonService {

    List<PersonDTO> getAll();

    PersonDTO findByIdOrElseThrowPersonNotFoundException(Long id) throws PersonNotFoundException;

    Person save(PersonDTO personDTO);

    void delete(Long id) throws PersonNotFoundException;

}
