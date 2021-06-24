package com.github.skyg0d.PersonAPI.mapper;

import com.github.skyg0d.PersonAPI.dto.request.PhoneDTO;
import com.github.skyg0d.PersonAPI.entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhoneMapper {

    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    Phone toPhone(PhoneDTO phoneDTO);

    PhoneDTO toDTO(Phone phone);

}
