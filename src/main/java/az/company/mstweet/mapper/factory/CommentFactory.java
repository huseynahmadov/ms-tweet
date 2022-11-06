package az.company.mstweet.mapper.factory;

import az.company.mstweet.dao.entity.CommentEntity;
import az.company.mstweet.dao.entity.PostEntity;

public class CommentFactory {

    public static CommentEntity buildCommentEntity(PostEntity post, String content) {
        return CommentEntity.builder()
                .post(post)
                .content(content)
                .build();
    }
}
