package com.bhz.educard.student.info.service;

import java.util.List;

import com.bhz.educard.student.info.entity.StudentInfo;

public interface StudentInfoService {
	public List<StudentInfo> parseStudentInfoFromCSV(String csvFileUrl);
	public void exportStudentInfoToCSV(List<StudentInfo> studentList,String outputFileUrl);
	public void translateStudentInfo(String inputFileUrl,String outputFileUrl);
}
