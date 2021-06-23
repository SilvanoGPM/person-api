package com.github.skyg0d.PersonAPI.service;

import com.github.skyg0d.PersonAPI.dto.request.PersonDTO;
import com.github.skyg0d.PersonAPI.entity.Person;

import java.util.List;

public interface PersonService {

    List<PersonDTO> getAll();

    Person save(PersonDTO personDTO);

}
