package com.github.skyg0d.PersonAPI.util;

import com.github.skyg0d.PersonAPI.dto.request.PhoneDTO;
import com.github.skyg0d.PersonAPI.entity.Phone;
import com.github.skyg0d.PersonAPI.enums.PhoneType;

public class PhoneCreator {

    public static Phone createValidPhone() {
        return Phone
                .builder()
                .id(1L)
                .number("87 912345678")
                .type(PhoneType.MOBILE)
                .build();
    }

    public static PhoneDTO createValidPhoneDTO() {
        return PhoneDTO
                .builder()
                .id(1L)
                .number("87 912345678")
                .type(PhoneType.MOBILE)
                .build();
    }

}
