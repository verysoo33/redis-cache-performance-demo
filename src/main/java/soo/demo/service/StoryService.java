package soo.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import soo.demo.config.cache.Cacheable;
import soo.demo.constant.RedisConst;
import soo.demo.dto.RestPage;
import soo.demo.dto.story.StoryDto;
import soo.demo.repository.StoryRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoryService {
    private final StoryRepository storyRepository;

    @Cacheable(key1 = RedisConst.WEB_STORY_LIST, ttl = 60*60*24)
    public RestPage<StoryDto> getStoryList(PageRequest pageable) {
        return storyRepository.search(pageable);
    }
}
