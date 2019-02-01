package com.group4.barrageSearch.entity;


import java.io.Serializable;
import java.util.ArrayList;

public class VideoInfo implements Serializable {
    private Video video;
    private ArrayList<Barrage> barrages;
    private int btimes;
    private String bcontent;

    public String getBcontent() {
        return bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }

    public int getBtimes() {
        return btimes;
    }

    public void setBtimes(int btimes) {
        this.btimes = btimes;
    }

    public ArrayList<Barrage> getBarrages() {
        return barrages;
    }

    public void setBarrages(ArrayList<Barrage> barrages) {
        this.barrages = barrages;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
