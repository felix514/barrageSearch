package com.group4.barrageSearch.dao;

import com.group4.barrageSearch.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoDao extends JpaRepository<Video,Integer> {
    Video findById(int id);
    Video findByVideoId(int videoId);
}
