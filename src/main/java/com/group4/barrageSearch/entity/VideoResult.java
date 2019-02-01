package com.group4.barrageSearch.entity;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

public interface VideoResult extends Serializable {
    public int getId();
    public String getVideoUrl();
    public String getWebsite();
    public int getBarrageCount();
    public String getCoverUrl();
    public Time getTimeLength();
    public Timestamp getReleaseTime();
    public int getHeat();
    public int getVideoId();
    public int getViews();
    public int getLikes();
    public String getTitle();
    public int getBtimes();
    public String getBcontent();
}
