package com.asp_dev.naissances.profiles.mappingDtoToObject;

import com.asp_dev.naissances.profiles.dto.ProfilesDTO;
import com.asp_dev.naissances.profiles.entities.Profiles;
import org.springframework.stereotype.Component;

@Component
public class ProfilesMapper {

    public Profiles dtoToEntity(ProfilesDTO dto) {
        Profiles entity = new Profiles();
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPassword(dto.getPassword());
        entity.setPhone(dto.getPhone());
        //entity.setCivility(dto.getCivility());
        //entity.setAddress(dto.address());
        return entity;
    }

    public ProfilesDTO entityToDto(Profiles entity) {
        return ProfilesDTO
                .builder()
                .civility(entity.getCivility())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .phone(entity.getPhone())
                //.password(entity.getPassword())
                .birthDate(entity.getBirthDate())
                .role(entity.getRoles().getName())
                .build();
    }
}
