package com.group4.barrageSearch.service.impl;

import com.group4.barrageSearch.dao.BarrageDao;
import com.group4.barrageSearch.dao.VideoDao;
import com.group4.barrageSearch.entity.*;
import com.group4.barrageSearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private BarrageDao barrageDao;
    @Autowired
    private VideoDao videoDao;
    @Override
    @Cacheable(value = "videoInfos",key = "'barrage_'+#barrage+'_order_'+#order+'_duration_'+#duration")
    public List<VideoInfo> searchByBarrage(String barrage,String order,int duration) {
        //返回的结果
        ArrayList<VideoInfo> videoInfos=new ArrayList<VideoInfo>();
        //返回的500个结果
        List<VideoInfo> results=new ArrayList<VideoInfo>();
        //暂存的videoid，防止重复
        ArrayList<Integer> tempVideoId=new ArrayList<Integer>();
        List<Integer> videoId;

        List<VideoResult> tempVideo=barrageDao.findByContentLike("%"+barrage+"%");
        System.out.println("tempvideo size :"+tempVideo.size());
        List<Integer> v=new ArrayList<Integer>();
        for(VideoResult vr:tempVideo){
            if(v.contains(vr.getId())){
                continue;
            }
            v.add(vr.getId());
            Video video=new Video();
            video.setId(vr.getId());
            video.setVideoUrl(vr.getVideoUrl());
            video.setWebsite(vr.getWebsite());
            video.setBarrageCount(vr.getBarrageCount());
            video.setCoverUrl(vr.getCoverUrl());
            video.setTimeLength(vr.getTimeLength());
            video.setReleaseTime(vr.getReleaseTime());
            video.setHeat(vr.getHeat());
            video.setVideoId(vr.getVideoId());
            video.setViews(vr.getViews());
            video.setLikes(vr.getLikes());
            video.setTitle(vr.getTitle());
            if(duration==1){
                Time tempTime=new Time(00,10,00);
                System.out.println("视频时间 ："+video.getTimeLength().getTime()+" ---"+video.getTimeLength());
                if(video.getTimeLength().getTime()>tempTime.getTime()){
                    System.out.println("超过时间限制 ："+video.getTimeLength().getTime()+" ---"+video.getTimeLength());
                    continue;
                }
            }
            //duration=1表示视频时间限制在10分钟到30分钟内
            if(duration==2){
                Time startTime=new Time(00,10,00);
                Time endTime=new Time(00,30,00);
                System.out.println("视频时间 ："+video.getTimeLength().getTime()+" ---"+video.getTimeLength());
                if(video.getTimeLength().getTime()<startTime.getTime()||video.getTimeLength().getTime()>endTime.getTime()){
                    System.out.println("超过时间限制 ："+video.getTimeLength().getTime()+" ---"+video.getTimeLength());
                    continue;
                }
            }
            //duration=1表示视频时间限制在30分钟到1个小时内
            if(duration==3){
                Time startTime=new Time(00,30,00);
                Time endTime=new Time(01,00,00);
                System.out.println("视频时间 ："+video.getTimeLength().getTime()+" ---"+video.getTimeLength());
                if(video.getTimeLength().getTime()<startTime.getTime()||video.getTimeLength().getTime()>endTime.getTime()){
                    System.out.println("超过时间限制 ："+video.getTimeLength().getTime()+" ---"+video.getTimeLength());
                    continue;
                }
            }
            //duration=1表示视频时间限制在1个小时以上
            if(duration==4){
                Time tempTime=new Time(01,10,00);
                System.out.println("视频时间 ："+video.getTimeLength().getTime()+" ---"+video.getTimeLength());
                if(video.getTimeLength().getTime()<tempTime.getTime()){
                    System.out.println("超过时间限制 ："+video.getTimeLength().getTime()+" ---"+video.getTimeLength());
                    continue;
                }
            }
            ArrayList<Barrage> tempBar=barrageDao.findByVideoId(video.getVideoId());
            VideoInfo videoInfo=new VideoInfo();
            videoInfo.setVideo(video);
            videoInfo.setBarrages(tempBar);
            videoInfo.setBtimes(vr.getBtimes());
            videoInfo.setBcontent(vr.getBcontent());
            videoInfos.add(videoInfo);
        }
        /*
        //根据times取降序的弹幕列表
        if(order.equals("totalsort")){
            videoId=barrageDao.findVideoIdByContentLikeOrderByTimesDesc("%"+barrage+"%");
        }else{
            videoId=barrageDao.findVideoIdByContentLike("%"+barrage+"%");
        }
        System.out.println("barrage  size : "+videoId.size());
        for(Integer b:videoId){
//            System.out.println("barrage content: "+b.getContent());
            if(!tempVideoId.contains(b)){
                tempVideoId.add(b);
                Video video=videoDao.findByVideoId(b);
                //判断是否符合duration
                //duration=1表示视频时间限制在10分钟内
                if(duration==1){
                    Time tempTime=new Time(00,10,00);
                    System.out.println("视频时间 ："+video.getTimeLength().getTime()+" ---"+video.getTimeLength());
                    if(video.getTimeLength().getTime()>tempTime.getTime()){
                        System.out.println("超过时间限制 ："+video.getTimeLength().getTime()+" ---"+video.getTimeLength());
                        continue;
                    }
                }
                //duration=1表示视频时间限制在10分钟到30分钟内
                if(duration==2){
                    Time startTime=new Time(00,10,00);
                    Time endTime=new Time(00,30,00);
                    System.out.println("视频时间 ："+video.getTimeLength().getTime()+" ---"+video.getTimeLength());
                    if(video.getTimeLength().getTime()<startTime.getTime()||video.getTimeLength().getTime()>endTime.getTime()){
                        System.out.println("超过时间限制 ："+video.getTimeLength().getTime()+" ---"+video.getTimeLength());
                        continue;
                    }
                }
                //duration=1表示视频时间限制在30分钟到1个小时内
                if(duration==3){
                    Time startTime=new Time(00,30,00);
                    Time endTime=new Time(01,00,00);
                    System.out.println("视频时间 ："+video.getTimeLength().getTime()+" ---"+video.getTimeLength());
                    if(video.getTimeLength().getTime()<startTime.getTime()||video.getTimeLength().getTime()>endTime.getTime()){
                        System.out.println("超过时间限制 ："+video.getTimeLength().getTime()+" ---"+video.getTimeLength());
                        continue;
                    }
                }
                //duration=1表示视频时间限制在1个小时以上
                if(duration==4){
                    Time tempTime=new Time(01,10,00);
                    System.out.println("视频时间 ："+video.getTimeLength().getTime()+" ---"+video.getTimeLength());
                    if(video.getTimeLength().getTime()<tempTime.getTime()){
                        System.out.println("超过时间限制 ："+video.getTimeLength().getTime()+" ---"+video.getTimeLength());
                        continue;
                    }
                }
                ArrayList<Barrage> tempBar=barrageDao.findByVideoId(b);
                VideoInfo videoInfo=new VideoInfo();
                videoInfo.setVideo(video);
                videoInfo.setBarrages(tempBar);
                videoInfos.add(videoInfo);
            }else{
                continue;
            }
        }
        */

        //按热度排序
        if(order.equals("heat")){
            System.out.println("----------------- order type is heat------------------ ");
            videoInfos.sort( new Comparator<VideoInfo>() {
                public int compare(VideoInfo o1, VideoInfo o2) {
                    Video video1=o1.getVideo();
                    Video video2=o2.getVideo();
                    System.out.println(" o1 heat and o2 heat  "+video1.getHeat()+"  "+video2.getHeat());
                    if(video1.getHeat()<video2.getHeat()){
                        return 1;
                    }
                    if(video1.getHeat()==video2.getHeat()){
                        return 0;
                    }
                    return -1;
                }
            });
        }else if(order.equals("pubdate")){
            //按发布时间排序
            System.out.println("----------------- order type is pubdate------------------ ");
            videoInfos.sort(new Comparator<VideoInfo>() {
                @Override
                public int compare(VideoInfo o1, VideoInfo o2) {
                    Video video1=o1.getVideo();
                    Video video2=o2.getVideo();
                    if(video1.getReleaseTime().before(video2.getReleaseTime())){
                        return 1;
                    }
                    if(video1.getReleaseTime()==video2.getReleaseTime()){
                        return 0;
                    }
                    return -1;
                }
            });
        }else if(order.equals("dm")){
            //按总弹幕数排序
            System.out.println("----------------- order type is dm------------------ ");
            videoInfos.sort( new Comparator<VideoInfo>() {
                @Override
                public int compare(VideoInfo o1, VideoInfo o2) {
                    if(o1.getBtimes()<o2.getBtimes()){
                        return 1;
                    }
                    if(o1.getBtimes()==o2.getBtimes()){
                        return 0;
                    }
                    return -1;
                }
            });
        }
        /*
        //数量筛选，只要前500个
        if(videoInfos.size()>500){
            for(int i=0;i<500;i++){
                results.add(videoInfos.get(i));
            }
        }else{
            return videoInfos;
        }
        return results;
        */
        return videoInfos;
    }

    @Override
    @Cacheable(value = "results",key = "'partBarrage_'+#partBarrage")
    public List<String> searchByPartBarrage(String partBarrage) {
//        List<Barrage> tempBarrages=barrageDao.findTop5ByContentLikeOrderByTimesDesc("%"+partBarrage+"%");
//        List<String> result=new ArrayList<String>();
//        for(Barrage b:tempBarrages){
//            System.out.println("barrage : "+b.getContent());
//            System.out.println("barrage times : "+b.getTimes());
//            result.add(b.getContent());
//        }
        List<String> temp=barrageDao.findDistinctContentByContentLike("%"+partBarrage+"%");
        List<String> result;
        //返回6个查询结果，没有按times排序
        if(temp.size()<=6){
            result=temp;
        }else{
            result=new ArrayList<>(temp.subList(0,6));
        }
        return result;
    }

    @Override
    @Cacheable(value = "popularBarrageContent",key = "#root.methodName")
    public List<String> getPopularBarrageContent() {
        List<String> result=new ArrayList<>();
        List<BarrageInfo> temp=barrageDao.findDistinctTop20OrderByTimesDesc();
        for(BarrageInfo b:temp){
            result.add(b.getContent());
        }
        return result;
    }

    @Override
    @Cacheable(value = "popularBarrage",key = "#root.methodName")
    public List<BarrageInfoImpl> getPopularBarrage() {
        List<BarrageInfo> temp=barrageDao.findDistinctTop20OrderByTimesDesc();
        List<BarrageInfoImpl> result=new ArrayList<>();
        for(BarrageInfo barrageInfo:temp){
            BarrageInfoImpl barrageInfoImpl=new BarrageInfoImpl();
            barrageInfoImpl.setContent(barrageInfo.getContent());
            barrageInfoImpl.setTimes(barrageInfo.getTimes());
            result.add(barrageInfoImpl);
        }
        return result;
    }
}
