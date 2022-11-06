package az.company.mstweet.controller;

import az.company.mstweet.model.dto.SaveTagDto;
import az.company.mstweet.model.dto.TagDto;
import az.company.mstweet.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveTag(@RequestBody SaveTagDto dto) {
        tagService.saveTag(dto);
    }

    @GetMapping
    public List<TagDto> getTags() {
        return tagService.getTags();
    }

}
