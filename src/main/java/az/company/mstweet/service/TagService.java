package az.company.mstweet.service;

import az.company.mstweet.dao.entity.TagEntity;
import az.company.mstweet.dao.repository.TagRepository;
import az.company.mstweet.mapper.TagMapper;
import az.company.mstweet.model.dto.SaveTagDto;
import az.company.mstweet.model.dto.TagDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public void saveTag(SaveTagDto dto) {
        tagRepository.save(TagMapper.mapSaveTagRequestToEntity(dto));
    }

    public List<TagEntity> getTagsByIds(Set<Long> ids) {
        return tagRepository.findByIdIn(ids);
    }

    public List<TagDto> getTags() {
        return tagRepository.findAll()
                .stream()
                .map(TagMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
