package com.group4.barrageSearch.response;

import com.group4.barrageSearch.entity.VideoInfo;
import lombok.Data;

import java.util.List;


@Data
public class VideoInfoResponse extends JsonpResponse{
    private List<VideoInfo> videoInfos;
    private int total;
}
