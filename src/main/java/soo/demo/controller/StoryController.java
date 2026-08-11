package soo.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import soo.demo.dto.RestPage;
import soo.demo.dto.story.StoryDto;
import soo.demo.service.StoryService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/story")
public class StoryController {

    private final StoryService storyService;

    @PostMapping(value = "/list")
    public ResponseEntity<RestPage<StoryDto>> getStories(
            @RequestParam(value = "limit", required = false, defaultValue = "20") int limit,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page
    ) {
        RestPage<StoryDto> storyDtoList = storyService.getStoryList(PageRequest.of(page, limit));

        return ResponseEntity.ok(storyDtoList);
    }
}
