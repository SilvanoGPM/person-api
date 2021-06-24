package com.github.skyg0d.PersonAPI.util;

import com.github.skyg0d.PersonAPI.dto.request.PersonDTO;
import com.github.skyg0d.PersonAPI.entity.Person;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class PersonCreator {

    public static final Long ID = 1L;
    public static final String FIRST_NAME = "Silvano";
    public static final String LAST_NAME = "Marques";
    public static final String LAST_NAME_UPDATED = "Pimentel";
    public static final String CPF = "701.975.780-47";

    public static Person createValidPerson() {
        return Person
                .builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(LocalDate.of(2004, Month.SEPTEMBER, 4))
                .phones(List.of(PhoneCreator.createValidPhone()))
                .build();
    }

    public static Person createValidUpdatedPerson() {
        return Person
                .builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME_UPDATED)
                .cpf(CPF)
                .birthDate(LocalDate.of(2004, Month.SEPTEMBER, 4))
                .phones(List.of(PhoneCreator.createValidPhone()))
                .build();
    }

    public static Person createPersonToSave() {
        return Person
                .builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(LocalDate.of(2004, Month.SEPTEMBER, 4))
                .phones(List.of(PhoneCreator.createValidPhone()))
                .build();
    }

    public static Person createPersonToUpdate() {
        return Person
                .builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME_UPDATED)
                .cpf(CPF)
                .birthDate(LocalDate.of(2004, Month.SEPTEMBER, 4))
                .phones(List.of(PhoneCreator.createValidPhone()))
                .build();
    }

    public static PersonDTO createPersonDTOToSave() {
        return PersonDTO
                .builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate("04-09-2004")
                .phones(List.of(PhoneCreator.createValidPhoneDTO()))
                .build();
    }

    public static PersonDTO createPersonDTOToUpdate() {
        return PersonDTO
                .builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME_UPDATED)
                .cpf(CPF)
                .birthDate("04-09-2004")
                .phones(List.of(PhoneCreator.createValidPhoneDTO()))
                .build();
    }

}
