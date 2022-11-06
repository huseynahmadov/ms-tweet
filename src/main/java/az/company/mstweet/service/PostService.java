package az.company.mstweet.service;

import az.company.mstweet.dao.entity.PostEntity;
import az.company.mstweet.dao.repository.PostRepository;
import az.company.mstweet.exception.NotFoundException;
import az.company.mstweet.mapper.PostMapper;
import az.company.mstweet.mapper.factory.CommentFactory;
import az.company.mstweet.mapper.factory.PostDetailFactory;
import az.company.mstweet.mapper.factory.PostFactory;
import az.company.mstweet.model.dto.PostDto;
import az.company.mstweet.model.dto.SaveCommentDto;
import az.company.mstweet.model.dto.SavePostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.company.mstweet.model.constants.ExceptionConstants.POST_NOT_FOUND_CODE;
import static az.company.mstweet.model.constants.ExceptionConstants.POST_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final TagService tagService;

    public void savePost(SavePostDto request) {
      var post = PostFactory.buildPostEntity(request);
      var postDetail = PostDetailFactory.buildPostDetailEntity(post, request.getCreatedBy());

      if (!request.getTagIds().isEmpty()) {
          var tags = tagService.getTagsByIds(request.getTagIds());
          post.setTags(tags);
      }

      post.setDetail(postDetail);
      postRepository.save(post);
    }

    public void addCommentToPost(Long id, SaveCommentDto dto) {
        var post = fetchPostIfExist(id);
        var comments = post.getComments();

        comments.add(CommentFactory.buildCommentEntity(post, dto.getContent()));
        post.setComments(comments);

        postRepository.save(post);
    }

    public PostDto getPost(Long id) {
        var post = fetchPostIfExist(id);

        return PostMapper.mapEntityToDto(post);
    }

    private PostEntity fetchPostIfExist(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format(POST_NOT_FOUND_MESSAGE, id),
                        POST_NOT_FOUND_CODE));
    }
}
