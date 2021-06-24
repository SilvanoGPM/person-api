package com.github.skyg0d.PersonAPI.service.impl;

import com.github.skyg0d.PersonAPI.dto.request.PersonDTO;
import com.github.skyg0d.PersonAPI.entity.Person;
import com.github.skyg0d.PersonAPI.exception.PersonNotFoundException;
import com.github.skyg0d.PersonAPI.mapper.PersonMapper;
import com.github.skyg0d.PersonAPI.repository.PersonRepository;
import com.github.skyg0d.PersonAPI.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person findByIdOrElseThrowPersonNotFoundException(Long id) throws PersonNotFoundException {
        return personRepository
                .findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public Person save(PersonDTO personDTO) {
        Person person = personMapper.toPerson(personDTO);
        return personRepository.save(person);
    }

    @Override
    public void replace(PersonDTO personDTO) {
        Person person = findByIdOrElseThrowPersonNotFoundException(personDTO.getId());
        Person savedPerson = personRepository.save(personMapper.toPerson(personDTO));
        savedPerson.setId(person.getId());
        personRepository.save(savedPerson);
    }

    @Override
    public void delete(Long id) throws PersonNotFoundException {
        personRepository.delete(findByIdOrElseThrowPersonNotFoundException(id));
    }

}
