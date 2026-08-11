package soo.demo.domain.observer;

import soo.demo.constant.RedisConst;
import soo.demo.domain.Story;
import soo.demo.util.CacheUtil;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;


public class StoryObserver {

    private final CacheUtil cacheUtil;

    public StoryObserver(CacheUtil cacheUtil) {
        this.cacheUtil = cacheUtil;
    }

    @PostPersist
    public void afterInsert(Story story) {
        refreshCache(story);
    }

    @PostUpdate
    public void afterUpdate(Story story) {
        refreshCache(story);
    }

    @PostRemove
    public void afterRemove(Story story) {
        refreshCache(story);
    }

    private void refreshCache(Story story) {
        // 메인페이지 캐시 삭제
        cacheUtil.deleteCache(RedisConst.WEB_MAIN, "");
        // 사연 메인 캐시 삭제
        cacheUtil.deleteCacheByPrefix(RedisConst.WEB_MAIN_COLLECTION);
        // 사연 상세 캐시 삭제
        cacheUtil.deleteCacheByPrefix(cacheUtil.getCacheKey(RedisConst.WEB_STORY_LIST,
                String.valueOf(story.getSeq())));
        // 사연 소식 캐시 삭제
        cacheUtil.deleteCacheByPrefix(cacheUtil.getCacheKey(RedisConst.WEB_STORY_LIST,
                String.valueOf(story.getSeq())));
    }
}
