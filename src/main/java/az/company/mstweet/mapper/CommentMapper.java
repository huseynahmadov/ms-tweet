package az.company.mstweet.mapper;

import az.company.mstweet.dao.entity.CommentEntity;
import az.company.mstweet.model.dto.CommentDto;

public class CommentMapper {

    public static CommentDto mapEntityToDto(CommentEntity entity) {
        return CommentDto.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .viewed(entity.isViewed())
                .build();
    }
}
