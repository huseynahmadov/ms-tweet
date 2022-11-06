package az.company.mstweet.mapper.factory;

import az.company.mstweet.dao.entity.PostEntity;
import az.company.mstweet.model.dto.SavePostDto;

public class PostFactory {

    public static PostEntity buildPostEntity(SavePostDto dto) {
        return PostEntity.builder()
                .title(dto.getTitle())
                .build();
    }
}
