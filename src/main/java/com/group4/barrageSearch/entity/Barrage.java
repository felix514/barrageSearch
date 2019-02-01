package com.group4.barrageSearch.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Barrage implements Serializable {
    private int id;
    private String content;
    private short times;
    private int videoId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "times")
    public short getTimes() {
        return times;
    }

    public void setTimes(short times) {
        this.times = times;
    }

    @Basic
    @Column(name = "video_id")
    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barrage barrage = (Barrage) o;
        return id == barrage.id &&
                times == barrage.times &&
                videoId == barrage.videoId &&
                Objects.equals(content, barrage.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, times, videoId);
    }
}
