package com.github.skyg0d.PersonAPI.service;

import com.github.skyg0d.PersonAPI.entity.Person;
import com.github.skyg0d.PersonAPI.exception.PersonNotFoundException;
import com.github.skyg0d.PersonAPI.repository.PersonRepository;
import com.github.skyg0d.PersonAPI.service.impl.PersonServiceImpl;
import com.github.skyg0d.PersonAPI.util.PersonCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Person Service")
public class PersonServiceTest {

    private PersonService personService;
    private PersonRepository personRepositoryMock;

    @BeforeEach
    void setup() {
        this.personRepositoryMock = BDDMockito.mock(PersonRepository.class);
        this.personService = new PersonServiceImpl(this.personRepositoryMock);

        List<Person> peopleList = List.of(PersonCreator.createValidPerson());

        BDDMockito.when(personRepositoryMock.findAll())
                .thenReturn(peopleList);

        BDDMockito.when(personRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(PersonCreator.createValidPerson()));

        BDDMockito.when(personRepositoryMock.save(ArgumentMatchers.any(Person.class)))
                .thenReturn(PersonCreator.createValidPerson());

        BDDMockito.doNothing()
                .when(personRepositoryMock)
                .delete(ArgumentMatchers.any(Person.class));
    }

    @Test
    @DisplayName("getAll returns list of people when successful")
    void getAll_ReturnsListOfPeople_WhenSuccessful() {
        Person validPerson = PersonCreator.createValidPerson();

        List<Person> peopleList = personService.getAll();

        assertThat(peopleList)
                .isNotNull()
                .hasSize(1)
                .contains(validPerson);
    }

    @Test
    @DisplayName("findById returns a person when successful")
    void findByIdOrElseThrowPersonNotFoundException_ReturnsPerson_WhenSuccessful() {
        Person validPerson = PersonCreator.createValidPerson();

        Person foundPerson = personService.findByIdOrElseThrowPersonNotFoundException(PersonCreator.ID);

        assertThat(foundPerson)
                .isNotNull()
                .isEqualTo(validPerson);
    }

    @Test
    @DisplayName("save returns person when successful")
    void save_ReturnsPerson_WhenSuccessful() {
        Person validPersonDTO = PersonCreator.createValidPerson();

        Person savedPerson = personService.save(PersonCreator.createPersonDTOToSave());

        assertThat(savedPerson)
                .isNotNull()
                .isEqualTo(validPersonDTO);
    }

    @Test
    @DisplayName("replace updates person when successful")
    void replace_UpdatesPerson_WhenSuccessful() {
        BDDMockito.when(personRepositoryMock.save(ArgumentMatchers.any(Person.class)))
                .thenReturn(PersonCreator.createValidUpdatedPerson());

        assertThatCode(() -> personService.replace(PersonCreator.createPersonDTOToUpdate()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete removes person when successful")
    void delete_RemovesPerson_WhenSuccessful() {
        assertThatCode(() -> personService.delete(PersonCreator.ID))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("findById throws PersonNotFoundException when person doest not exists")
    void findByIdOrElseThrowPersonNotFoundException_ThrowsPersonNotFoundException_WhenPersonDoesNotExists() {
        BDDMockito
                .when(personRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> personService.findByIdOrElseThrowPersonNotFoundException(PersonCreator.ID))
                .isInstanceOf(PersonNotFoundException.class);
    }

}
