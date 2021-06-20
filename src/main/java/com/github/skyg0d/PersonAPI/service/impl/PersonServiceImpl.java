package com.github.skyg0d.PersonAPI.service.impl;

import com.github.skyg0d.PersonAPI.entity.Person;
import com.github.skyg0d.PersonAPI.repository.PersonRepository;
import com.github.skyg0d.PersonAPI.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

}
