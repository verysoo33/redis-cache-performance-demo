package soo.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "/search")
    public ResponseEntity<RestPage<StoryDto>> getSearch(
            @RequestParam(value = "limit", required = false, defaultValue = "20") int limit,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page
    ) {
        RestPage<StoryDto> storyDtoList = storyService.getStoryList(PageRequest.of(page, limit));

        return ResponseEntity.ok(storyDtoList);
    }
}
