package com.group4.barrageSearch.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Video implements Serializable {
    private int id;
    private String videoUrl;
    private String website;
    private int barrageCount;
    private String coverUrl;
    private Time timeLength;
    private Timestamp releaseTime;
    private int heat;
    private int videoId;
    private int views;
    private int likes;
    private String title;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "video_url")
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Basic
    @Column(name = "website")
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Basic
    @Column(name = "barrage_count")
    public int getBarrageCount() {
        return barrageCount;
    }

    public void setBarrageCount(int barrageCount) {
        this.barrageCount = barrageCount;
    }

    @Basic
    @Column(name = "cover_url")
    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Basic
    @Column(name = "time_length")
    public Time getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(Time timeLength) {
        this.timeLength = timeLength;
    }

    @Basic
    @Column(name = "release_time")
    public Timestamp getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Timestamp releaseTime) {
        this.releaseTime = releaseTime;
    }

    @Basic
    @Column(name = "heat")
    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    @Basic
    @Column(name = "video_id")
    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    @Basic
    @Column(name = "views")
    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Basic
    @Column(name = "likes")
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return id == video.id &&
                heat == video.heat &&
                videoId == video.videoId &&
                views == video.views &&
                likes == video.likes &&
                Objects.equals(videoUrl, video.videoUrl) &&
                Objects.equals(website, video.website) &&
                Objects.equals(barrageCount, video.barrageCount) &&
                Objects.equals(coverUrl, video.coverUrl) &&
                Objects.equals(timeLength, video.timeLength) &&
                Objects.equals(releaseTime, video.releaseTime) &&
                Objects.equals(title, video.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, videoUrl, website, barrageCount, coverUrl, timeLength, releaseTime, heat, videoId, views, likes, title);
    }
}
