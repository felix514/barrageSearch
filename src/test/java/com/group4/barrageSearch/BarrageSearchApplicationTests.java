package com.group4.barrageSearch;

import com.group4.barrageSearch.dao.BarrageDao;
import com.group4.barrageSearch.dao.VideoDao;
import com.group4.barrageSearch.entity.BarrageInfoImpl;
import com.group4.barrageSearch.entity.Video;
import com.group4.barrageSearch.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BarrageSearchApplicationTests {
	@Autowired
	private BarrageDao barrageDao;
	@Autowired
	private VideoDao videoDao;
	@Autowired
	private SearchService searchService;
	@Test
	public void contextLoads() throws Exception{
//		Video video=videoDao.findById(548);
		System.out.println();
//		System.out.println(video.getTimeLength());
	}

}
