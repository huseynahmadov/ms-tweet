package az.company.mstweet.controller;

import az.company.mstweet.model.dto.CommentDto;
import az.company.mstweet.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@WebMvcTest(controllers = CommentController.class)
class CommentControllerTest {

    private static final Long COMMENT_ID = 1L;
    private static final Long POST_ID = 1L;
    private static final String COMMENT_CONTENT = "Test Comment";
    private static final String COMMENT_CONTROLLER_PATH = "/v1/comments";

    private static List<CommentDto> commentDtos;
    private static CommentDto commentDto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentService commentService;

    @BeforeAll
    static void setUp() {
        commentDto = new CommentDto();
        commentDto.setId(1L);

        commentDtos = new ArrayList<>();
        commentDtos.add(0, commentDto);
    }

    @Test
    void changeCommentViewStatus_Success() throws Exception {
        //when
        doNothing().when(commentService).changeCommentViewStatus(COMMENT_ID);

        //then
        mockMvc.perform(patch(COMMENT_CONTROLLER_PATH + "/{id}/view-status", COMMENT_ID))
                .andExpect(status().isNoContent());

        verify(commentService, times(1)).changeCommentViewStatus(POST_ID);
    }

    @Test
    void getCommentByPost_Success() throws Exception {
        //when
        when(commentService.getCommentsByPost(POST_ID)).thenReturn(commentDtos);

        //then
        mockMvc.perform(get(COMMENT_CONTROLLER_PATH + "/posts/{postId}", POST_ID))
                .andExpect(jsonPath("$[0].content", is(commentDto.getContent())))
                .andExpect(status().isOk());

        verify(commentService, times(1)).getCommentsByPost(POST_ID);
    }

    @Test
    void changeCommentContent_Success() throws Exception {
        //when
        doNothing().when(commentService).changeCommentContent(COMMENT_ID, COMMENT_CONTENT);

        //then
        mockMvc.perform(patch(COMMENT_CONTROLLER_PATH + "/{id}/content", COMMENT_ID)
                        .param("value", COMMENT_CONTENT))
                .andExpect(status().isNoContent());

        verify(commentService, times(1))
                .changeCommentContent(COMMENT_ID, COMMENT_CONTENT);
    }
}
