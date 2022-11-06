package az.company.mstweet.mapper;

import az.company.mstweet.dao.entity.TagEntity;
import az.company.mstweet.model.dto.SaveTagDto;
import az.company.mstweet.model.dto.TagDto;

public class TagMapper {

    public static TagEntity mapSaveTagRequestToEntity(SaveTagDto dto) {
        return TagEntity.builder()
                .name(dto.getName())
                .build();
    }

    public static TagDto mapEntityToDto(TagEntity entity) {
        return TagDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
