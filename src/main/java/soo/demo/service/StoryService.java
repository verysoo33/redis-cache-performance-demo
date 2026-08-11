package soo.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import soo.demo.config.cache.Cacheable;
import soo.demo.constant.RedisConst;
import soo.demo.dto.StoryDto;
import soo.demo.repository.StoryRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoryService {
    private final StoryRepository storyRepository;

    @Cacheable(key1 = RedisConst.WEB_STORY_LIST, ttl = 60*60*24)
    public List<StoryDto> getStoryList(Integer mainSectionYn, Integer cmsYn) {
        return storyRepository.listStory(mainSectionYn, cmsYn);
    }
}
