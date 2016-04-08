package com.bhz.test;

import java.util.List;

import org.junit.Test;

import com.bhz.educard.student.info.entity.StudentInfo;
import com.bhz.educard.student.info.service.StudentInfoService;
import com.bhz.educard.student.info.service.StudentInfoServiceImpl;

public class StudentInfoServiceTest {
	
	@Test
	public void parseStudentInfoFromCSVTest(){
		StudentInfoService sis = new StudentInfoServiceImpl();
		int counter = 1;
		List<StudentInfo> stduList = sis.parseStudentInfoFromCSV("/Users/yaoh/workspace/edu_card/project/student-info-tool/template/student-org.csv");
//		List<StudentInfo> stduList = sis.parseStudentInfoFromCSV("/Users/yaoh/workspace/edu_card/project/student-info-tool/template/test.csv");
		for(StudentInfo info:stduList){
			System.out.println("[" + counter + "]" + info);
			counter++;
		}
	}
	
	@Test
	public void exportStudentInfoToCSV(){
		StudentInfoService sis = new StudentInfoServiceImpl();
		List<StudentInfo> stduList = sis.parseStudentInfoFromCSV("/Users/yaoh/workspace/edu_card/project/student-info-tool/template/student-org.csv");
		sis.exportStudentInfoToCSV(stduList,"/Users/yaoh/workspace/edu_card/project/student-info-tool/template/student-trans.csv");
	}
	
	@Test
	public void translateStudentInfoTest(){
		StudentInfoService sis = new StudentInfoServiceImpl();
		sis.translateStudentInfo("/Volumes/TOSHIBA/TestDataCS/测试数据原始数据-长沙/三年级/三年级学生信息.csv",
				"/Volumes/TOSHIBA/TestDataCS/三年级学生信息(转换).csv");
	}
}
