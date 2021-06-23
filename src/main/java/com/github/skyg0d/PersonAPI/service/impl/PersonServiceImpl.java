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

    @Override
    public PersonDTO findByIdOrElseThrowPersonNotFoundException(Long id) throws PersonNotFoundException {
        Person person = personRepository
                .findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return personMapper.toDTO(person);
    }

    @Override
    public Person save(PersonDTO personDTO) {
        Person person = personMapper.toPerson(personDTO);
        return personRepository.save(person);
    }

    @Override
    public void delete(Long id) throws PersonNotFoundException {
        personRepository.deleteById(findByIdOrElseThrowPersonNotFoundException(id).getId());
    }

}
