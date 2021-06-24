package com.github.skyg0d.PersonAPI.controller;

import com.github.skyg0d.PersonAPI.dto.request.PersonDTO;
import com.github.skyg0d.PersonAPI.entity.Person;
import com.github.skyg0d.PersonAPI.exception.PersonNotFoundException;
import com.github.skyg0d.PersonAPI.service.PersonService;
import com.github.skyg0d.PersonAPI.util.PersonCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Person Controller")
public class PersonControllerTest {

    private PersonController personController;
    private PersonService personServiceMock;

    @BeforeEach
    void setup() {
        this.personServiceMock = BDDMockito.mock(PersonService.class);
        this.personController = new PersonController(this.personServiceMock);

        List<Person> peopleList = List.of(PersonCreator.createValidPerson());

        BDDMockito.when(personServiceMock.getAll())
                .thenReturn(peopleList);

        BDDMockito.when(personServiceMock.findByIdOrElseThrowPersonNotFoundException(ArgumentMatchers.anyLong()))
                .thenReturn(PersonCreator.createValidPerson());

        BDDMockito.when(personServiceMock.save(ArgumentMatchers.any(PersonDTO.class)))
                .thenReturn(PersonCreator.createValidPerson());

        BDDMockito.doNothing()
                .when(personServiceMock)
                .replace(ArgumentMatchers.any(PersonDTO.class));

        BDDMockito.doNothing()
                .when(personServiceMock)
                .delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("getAll returns list of people when successful")
    void getAll_ReturnsListOfPeople_WhenSuccessful() {
        Person validPerson = PersonCreator.createValidPerson();

        List<Person> peopleList = personController.getAll().getBody();

        assertThat(peopleList)
                .isNotNull()
                .hasSize(1)
                .contains(validPerson);
    }

    @Test
    @DisplayName("findById returns a person when successful")
    void findById_ReturnsPerson_WhenSuccessful() {
        Person validPerson = PersonCreator.createValidPerson();

        Person foundPerson = personController.findById(PersonCreator.ID).getBody();

        assertThat(foundPerson)
                .isNotNull()
                .isEqualTo(validPerson);
    }

    @Test
    @DisplayName("save returns person when successful")
    void save_ReturnsPerson_WhenSuccessful() {
        Person validPersonDTO = PersonCreator.createValidPerson();

        Person savedPerson = personController.save(PersonCreator.createPersonDTOToSave()).getBody();

        assertThat(savedPerson)
                .isNotNull()
                .isEqualTo(validPersonDTO);
    }

    @Test
    @DisplayName("replace updates person when successful")
    void replace_UpdatesPerson_WhenSuccessful() {
        ResponseEntity<Void> entity = personController.replace(PersonCreator.createPersonDTOToUpdate());

        assertThat(entity).isNotNull();

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete removes person when successful")
    void delete_RemovesPerson_WhenSuccessful() {
        ResponseEntity<Void> entity = personController.delete(PersonCreator.ID);

        assertThat(entity).isNotNull();

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("findById throws PersonNotFoundException when person doest not exists")
    void findByIdOrElseThrowPersonNotFoundException_ThrowsPersonNotFoundException_WhenPersonDoesNotExists() {
        BDDMockito
                .when(personServiceMock.findByIdOrElseThrowPersonNotFoundException(ArgumentMatchers.anyLong()))
                .thenThrow(PersonNotFoundException.class);

        assertThatThrownBy(() -> personController.findById(PersonCreator.ID))
                .isInstanceOf(PersonNotFoundException.class);
    }

}
