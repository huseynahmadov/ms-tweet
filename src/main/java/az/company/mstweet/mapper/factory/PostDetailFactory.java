package az.company.mstweet.mapper.factory;

import az.company.mstweet.dao.entity.PostDetailEntity;
import az.company.mstweet.dao.entity.PostEntity;

public class PostDetailFactory {

    public static PostDetailEntity buildPostDetailEntity(PostEntity post, String createdBy) {
        return PostDetailEntity.builder()
                .post(post)
                .createdBy(createdBy)
                .build();
    }
}
