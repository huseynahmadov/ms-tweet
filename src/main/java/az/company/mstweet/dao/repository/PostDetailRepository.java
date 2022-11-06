package az.company.mstweet.dao.repository;

import az.company.mstweet.dao.entity.PostDetailEntity;
import az.company.mstweet.dao.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDetailRepository extends JpaRepository<PostDetailEntity, Long> {
}
