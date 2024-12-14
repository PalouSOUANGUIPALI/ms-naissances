package com.asp_dev.naissances.profiles.mappingDtoToObject;

import com.asp_dev.naissances.profiles.dto.ProfilesDTO;
import com.asp_dev.naissances.profiles.entities.Profiles;
import org.springframework.stereotype.Component;

@Component
public class ProfilesMapper {

    public Profiles dtoToEntity(ProfilesDTO dto) {
        Profiles entity = new Profiles();
        entity.setEmail(dto.email());
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setPassword(dto.password());
        entity.setPhone(dto.phone());
        entity.setCivility(dto.civility());
        //entity.setAddress(dto.address());
        return entity;
    }

    public ProfilesDTO entityToDto(Profiles entity) {
        return new ProfilesDTO(
                entity.getCivility(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhone(),
                null
        );
    }
}
