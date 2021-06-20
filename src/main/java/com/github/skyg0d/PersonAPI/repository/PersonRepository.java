package com.github.skyg0d.PersonAPI.repository;

import com.github.skyg0d.PersonAPI.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
