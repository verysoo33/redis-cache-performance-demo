package soo.demo.repository;

import soo.demo.domain.Story;
import soo.demo.dto.StoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepoCommon {

    Page<StoryDto> search(PageRequest pageable, List<Integer> benefitTargetSeqs, List<String> states, List<Integer> fundTypeCodes, Integer orderType,
                          String fieldType, String keyword, Integer useYn, Integer[] storyTypeCodeArr, Integer cmsYn, List<String> tags);

    Page<StoryDto> findListBySort(PageRequest pageable, Integer orderType, Integer typeCode, Integer fundTypeCode,
                               Integer categorySeq, Integer agencySeq, Integer storyTypeCode, String keyword,
                               Integer mainSectionYn, Integer useYn, String benefitTargetCode, Integer[] storyTypeCodeArr,
                                  String state, Integer cmsYn, String groupId);

    long count(String typeCode, String search);

    List<Story> findListBySegs(Integer[] segs, Integer useYn);

    List<StoryDto> listStory(Integer mainSectionYn, Integer cmsYn);
}
