package com.github.skyg0d.PersonAPI.service.impl;

import com.github.skyg0d.PersonAPI.dto.request.PersonDTO;
import com.github.skyg0d.PersonAPI.entity.Person;
import com.github.skyg0d.PersonAPI.exception.PersonNotFoundException;
import com.github.skyg0d.PersonAPI.mapper.PersonMapper;
import com.github.skyg0d.PersonAPI.repository.PersonRepository;
import com.github.skyg0d.PersonAPI.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Override
    public List<PersonDTO> getAll() {
        return personRepository
                .findAll()
                .stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        return personMapper.toDTO(findByIdOrElseThrowPersonNotFoundException(id));
    }

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        Person person = personMapper.toPerson(personDTO);
        return personMapper.toDTO(personRepository.save(person));
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
        personRepository.deleteById(findByIdOrElseThrowPersonNotFoundException(id).getId());
    }

    private Person findByIdOrElseThrowPersonNotFoundException(Long id) throws PersonNotFoundException {
        return personRepository
                .findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

}
