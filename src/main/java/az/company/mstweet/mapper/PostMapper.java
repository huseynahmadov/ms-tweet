package az.company.mstweet.mapper;

import az.company.mstweet.dao.entity.PostEntity;
import az.company.mstweet.model.dto.PostDto;

public class PostMapper {

    public static PostDto mapEntityToDto(PostEntity entity) {
        return new PostDto(entity.getId(), entity.getTitle());
    }
}
