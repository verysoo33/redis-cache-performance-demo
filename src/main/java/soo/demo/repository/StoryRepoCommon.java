package soo.demo.repository;

import soo.demo.domain.Story;
import soo.demo.dto.RestPage;
import soo.demo.dto.story.StoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepoCommon {

    RestPage<StoryDto> search(PageRequest pageable);

    Page<StoryDto> findListBySort(PageRequest pageable, Integer orderType, Integer typeCode, Integer fundTypeCode,
                               Integer categorySeq, Integer agencySeq, Integer storyTypeCode, String keyword,
                               Integer mainSectionYn, Integer useYn, String benefitTargetCode, Integer[] storyTypeCodeArr,
                                  String state, Integer cmsYn, String groupId);

    long count(String typeCode, String search);

    List<Story> findListBySegs(Integer[] segs, Integer useYn);

    List<StoryDto> listStory();
}
