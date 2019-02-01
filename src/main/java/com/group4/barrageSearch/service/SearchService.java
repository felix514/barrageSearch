package com.group4.barrageSearch.service;

import com.group4.barrageSearch.entity.BarrageInfoImpl;
import com.group4.barrageSearch.entity.VideoInfo;

import java.util.List;

public interface SearchService {
    List<VideoInfo> searchByBarrage(String barrage,String order,int duration);
    List<String> searchByPartBarrage(String partBarrage);
    List<String> getPopularBarrageContent();
    List<BarrageInfoImpl> getPopularBarrage();
}
