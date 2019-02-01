package com.group4.barrageSearch.controller;

import com.group4.barrageSearch.entity.VideoInfo;
import com.group4.barrageSearch.response.AssociativeSearchResponse;
import com.group4.barrageSearch.response.PopularBarrageResponse;
import com.group4.barrageSearch.response.VideoInfoResponse;
import com.group4.barrageSearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;
    @RequestMapping("/search")
    public VideoInfoResponse searchByBarrage(String barrage, int pageIndex, String order, int duration,int pageNum){
        VideoInfoResponse videoInfoResponse=new VideoInfoResponse();
        List<VideoInfo> totalVideoInfos;
//        ArrayList<VideoInfo> resultVideoInfos=new ArrayList<VideoInfo>();
        if(pageIndex==0){
            return videoInfoResponse;
        }
        try{
            //获取videoinfo的信息
            totalVideoInfos=searchService.searchByBarrage(barrage,order,duration);
            videoInfoResponse.setTotal(totalVideoInfos.size());
            if(totalVideoInfos.size()>=pageNum){
                //多于一页，返回pageNum的数量
                if(totalVideoInfos.size()-pageIndex*pageNum>=0){
                    videoInfoResponse.setVideoInfos(totalVideoInfos.subList((pageIndex-1)*pageNum,pageIndex*pageNum));
                }
                //不够一页，返回剩余的数量
                if((totalVideoInfos.size()-pageIndex*pageNum<0)&&(totalVideoInfos.size()-(pageIndex-1)*pageNum>0)){
                    videoInfoResponse.setVideoInfos(totalVideoInfos.subList((pageIndex-1)*pageNum,totalVideoInfos.size()));
                }
            }else{
                videoInfoResponse.setVideoInfos(totalVideoInfos);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return videoInfoResponse;
    }
    @RequestMapping("/associativeSearch")
    public AssociativeSearchResponse getAssociativeSearch(String partBarrage){
        AssociativeSearchResponse associativeSearchResponse=new AssociativeSearchResponse();
        try{
            associativeSearchResponse.setResults(searchService.searchByPartBarrage(partBarrage));
        }catch (Exception e){
            e.printStackTrace();
        }
        return associativeSearchResponse;
    }

    @RequestMapping("/popularBarrage")
    public PopularBarrageResponse getPopularBarrage(){
        PopularBarrageResponse popularBarrageResponse=new PopularBarrageResponse();
        try{
            popularBarrageResponse.setPopularBarrages(searchService.getPopularBarrage());
        }catch (Exception e){
            e.printStackTrace();
        }
        return popularBarrageResponse;
    }
}
