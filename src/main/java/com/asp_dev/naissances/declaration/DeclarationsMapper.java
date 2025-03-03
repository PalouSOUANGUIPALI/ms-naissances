package com.asp_dev.naissances.declaration;

import com.asp_dev.naissances.profiles.mappingDtoToObject.ProfilesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DeclarationsMapper {
    ProfilesMapper profilesMapper;

    public DeclarationDto entityToDTO(Declaration declaration) {
        return DeclarationDto.builder()
                .id(declaration.getId())
                .name(declaration.getName())
                .comment(declaration.getComment())
                .description(declaration.getDescription())
                .registered(declaration.getRegistered())
                .firstParent(profilesMapper.entityToDto(declaration.getFirstParent()))
                .secondParent(profilesMapper.entityToDto(declaration.getSecondParent()))
                .child(profilesMapper.entityToDto(declaration.getChild()))
                .statuses(declaration.getStatuses())
                .company(declaration.getCompany())
                .build();
    }
}
