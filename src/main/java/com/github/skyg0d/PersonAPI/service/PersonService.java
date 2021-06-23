package com.github.skyg0d.PersonAPI.service;

import com.github.skyg0d.PersonAPI.dto.request.PersonDTO;
import com.github.skyg0d.PersonAPI.exception.PersonNotFoundException;

import java.util.List;

public interface PersonService {

    List<PersonDTO> getAll();

    PersonDTO findById(Long id) throws PersonNotFoundException;

    PersonDTO save(PersonDTO personDTO);

    void delete(Long id) throws PersonNotFoundException;

    void replace(PersonDTO personDTO);

}
