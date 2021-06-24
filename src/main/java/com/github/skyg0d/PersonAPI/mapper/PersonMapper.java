package com.github.skyg0d.PersonAPI.mapper;

import com.github.skyg0d.PersonAPI.dto.request.PersonDTO;
import com.github.skyg0d.PersonAPI.dto.request.PhoneDTO;
import com.github.skyg0d.PersonAPI.entity.Person;
import com.github.skyg0d.PersonAPI.entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public abstract class PersonMapper {

    public static final PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    public abstract Person toPerson(PersonDTO personDTO);

    public abstract PersonDTO toDTO(Person person);

    List<Phone> mapPhones(List<PhoneDTO> source) {
        return source.stream().map(PhoneMapper.INSTANCE::toPhone).collect(Collectors.toList());
    }

}
