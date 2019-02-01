package com.group4.barrageSearch.dao;

import com.group4.barrageSearch.entity.Barrage;
import com.group4.barrageSearch.entity.BarrageInfo;
import com.group4.barrageSearch.entity.Video;
import com.group4.barrageSearch.entity.VideoResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface BarrageDao extends JpaRepository<Barrage,Integer> {
    @Query(value="select video_id from barrage where content like ?1 order by times desc",nativeQuery = true)
    List<Integer> findVideoIdByContentLikeOrderByTimesDesc(String content);
    @Query(value="select video_id from barrage where content like ?1",nativeQuery = true)
    List<Integer> findVideoIdByContentLike(String content);

//    @Query(value="select distinct video.id as id,video.video_url as videoUrl,video.title as title,video.website as website,video.barrage_count as barrageCount," +
//            "video.cover_url as coverUrl,video.time_length as timeLength,video.release_time as releaseTime,video.heat as heat,video.video_id as videoId,video.views as views," +
//            "video.likes as likes,barrage.times as btimes ,barrage.content as bcontent from barrage left join video on video.video_id=barrage.video_id where barrage.content like ?1 order by barrage.times desc",nativeQuery = true)
//    List<VideoResult> findByContentLike(String content);

    @Query(value="select distinct video.id as id,video.video_url as videoUrl,video.title as title,video.website as website,video.barrage_count as barrageCount," +
            "video.cover_url as coverUrl,video.time_length as timeLength,video.release_time as releaseTime,video.heat as heat,video.video_id as videoId,video.views as views," +
            "video.likes as likes,barrage.times as btimes ,barrage.content as bcontent from barrage,video where barrage.content like ?1  and barrage.video_id=video.video_id order by barrage.times desc",nativeQuery = true)
    List<VideoResult> findByContentLike(String content);

    ArrayList<Barrage> findByVideoId(int videoId);
    List<Barrage> findTop5ByContentLikeOrderByTimesDesc(String partBarrage);

    @Query("select distinct b.content from Barrage b where b.content like ?1")
    List<String> findDistinctContentByContentLike(String partBarrage);

    @Query(value="select content,max(times) as times from barrage group by content order by times desc limit 0,20",nativeQuery = true)
    List<BarrageInfo> findDistinctTop20OrderByTimesDesc();
}
