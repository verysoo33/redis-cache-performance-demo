package soo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soo.demo.domain.Story;

public interface StoryRepository extends JpaRepository<Story, Integer>, StoryRepoCommon {

}
