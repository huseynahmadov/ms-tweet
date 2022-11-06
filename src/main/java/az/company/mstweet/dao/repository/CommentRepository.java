package az.company.mstweet.dao.repository;

import az.company.mstweet.dao.entity.CommentEntity;
import az.company.mstweet.dao.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByPostId(Long postId);
}
