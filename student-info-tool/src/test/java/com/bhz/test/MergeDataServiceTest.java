package com.bhz.test;

import java.io.IOException;

import org.junit.Test;

import com.bhz.educard.student.info.service.MergeDataService;
import com.bhz.educard.student.info.service.MergeDataServiceImpl;

public class MergeDataServiceTest {
	@Test
	public void merge(){
		MergeDataService mds = new MergeDataServiceImpl();
		try {
			mds.merge("/Volumes/TOSHIBA/TestDataCS/测试数据原始数据-长沙", "/Volumes/TOSHIBA/TestDataCS/Processed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
