package soo.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import soo.demo.dto.StoryDto;
import soo.demo.repository.StoryRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoryService {
    private final StoryRepository storyRepository;

    public List<StoryDto> getStoryList(Integer mainSectionYn, Integer cmsYn) {
        return storyRepository.listStory(mainSectionYn, cmsYn);
    }
}
