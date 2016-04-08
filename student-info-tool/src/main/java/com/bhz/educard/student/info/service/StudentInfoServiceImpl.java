package com.bhz.educard.student.info.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bhz.educard.student.info.entity.StudentInfo;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class StudentInfoServiceImpl implements StudentInfoService {
	private static Logger logger = LogManager.getLogger(StudentInfoService.class);
	String[] studentInfoHeader = new String[]{
		"学生姓名","学籍号","身份证件号","性别","籍贯","民族","家庭地址","联系电话","学校名称","学校标识码",
		"残疾类型","出生日期","出生地","国籍地区","身份证件类型","年级","班级","港澳台侨外","政治面貌","健康状况",
		"户口所在地","户口性质","入学年月","入学方式","就读方式","现住址","通讯地址","邮政编码","家庭住址","是否独生子女",
		"是否进城务工人员子女","是否留守儿童","是否受过学前教育","是否烈士优抚子女","是否孤儿","是否享受一补","是否需要申请资助","学籍辅号","班内学号","学生来源",
		"随班就读","家庭成员姓名","关系","工作单位","家庭现住址","家庭户口所在地","家庭联系电话","证件有效期"
	};
	@Override
	public List<StudentInfo> parseStudentInfoFromCSV(String csvFileUrl) {
		HeaderColumnNameTranslateMappingStrategy<StudentInfo> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<StudentInfo>();
		beanStrategy.setType(StudentInfo.class);
		Map<String, String> columnMapping = new HashMap<String, String>();
		columnMapping.put("学生姓名", "name");
	    columnMapping.put("学籍号", "studentCode");
	    columnMapping.put("身份证件号", "id");
	    columnMapping.put("性别", "sex");
	    columnMapping.put("籍贯", "nativePlace");
	    columnMapping.put("民族", "race");
	    columnMapping.put("家庭地址", "address");
	    columnMapping.put("联系电话", "contactPhone");
	    columnMapping.put("学校名称", "schoolName");
	    columnMapping.put("学校标识码", "schoolId");
	    columnMapping.put("残疾类型", "disabilityType");
	    columnMapping.put("出生日期", "birthday");
	    columnMapping.put("出生地", "birthPlace");
	    columnMapping.put("国籍地区", "national");
	    columnMapping.put("身份证件类型", "idType");
	    columnMapping.put("年级", "grade");
	    columnMapping.put("班级", "gradeClass");
	    columnMapping.put("港澳台侨外", "overseas");
	    columnMapping.put("政治面貌", "politics");
	    columnMapping.put("健康状况", "health");
	    columnMapping.put("户口所在地", "registeredPlace");
	    columnMapping.put("户口性质", "registeredCharacter");
	    columnMapping.put("入学年月", "enterSchoolDate");
	    columnMapping.put("入学方式", "enterSchoolMethod");
	    columnMapping.put("就读方式", "studyMethod");
	    columnMapping.put("现住址", "presentAddr");
	    columnMapping.put("通讯地址", "contactAddr");
	    columnMapping.put("邮政编码", "zipCode");
	    columnMapping.put("家庭住址", "homeAddr");
	    columnMapping.put("是否独生子女", "onlyChild");
	    columnMapping.put("是否进城务工人员子女", "enterCityChild");
	    columnMapping.put("是否留守儿童", "leftChild");
	    columnMapping.put("是否受过学前教育", "preSchool");
	    columnMapping.put("是否烈士优抚子女", "martyrChild");
	    columnMapping.put("是否孤儿", "orphan");
	    columnMapping.put("是否享受一补", "yiBu");
	    columnMapping.put("是否需要申请资助", "needFund");
	    columnMapping.put("学籍辅号", "assistCode");
	    columnMapping.put("班内学号", "codeInClass");
	    columnMapping.put("学生来源", "source");
	    columnMapping.put("随班就读", "followClass");
	    columnMapping.put("家庭成员姓名", "familyMember");
	    columnMapping.put("关系", "familyRelation");
	    columnMapping.put("工作单位", "familyWorkplace");
	    columnMapping.put("现住址", "familyPresentAddr");
	    columnMapping.put("户口所在地", "familyRegisteredPlace");
	    columnMapping.put("联系电话", "familyContactPhone");
	    
	    beanStrategy.setColumnMapping(columnMapping);
	    CsvToBean<StudentInfo> csvToBean = new CsvToBean<StudentInfo>();
	    CSVReader reader = null;
		try {
			reader = new CSVReader(new InputStreamReader(new FileInputStream(csvFileUrl), "GBK"), ',');
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(reader != null){
			List<StudentInfo> students = csvToBean.parse(beanStrategy, reader);
			for(StudentInfo stu:students){
				stu.setSourceFile(csvFileUrl);
			}
			return students;
		}else{
			return null;
		}
	}
	
	public void exportStudentInfoToCSV(List<StudentInfo> studentList,String outputFileUrl){
		try {
			File f = new File(outputFileUrl);
			if(f.exists()){
				f.delete();
			}
			BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileUrl),"GBK"));
			CSVWriter csvWriter = new CSVWriter(bfw,',');
		    List<String[]> data  = toStringArray(studentList);
		    csvWriter.writeAll(data);
		    try {
				csvWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void translateStudentInfo(String inputFileUrl,String outputFileUrl){
		List<StudentInfo> stduList = parseStudentInfoFromCSV(inputFileUrl);
		exportStudentInfoToCSV(stduList, outputFileUrl);
	}
	
	private List<String[]> toStringArray(List<StudentInfo> students) {
	    List<String[]> records = new ArrayList<String[]>();
	    //add header record
	    records.add(studentInfoHeader);
	    String OVERSEAS_SHOW = "其他";
	    String ENTERSCHOOL_SHOW = "普通入学";
	    String SOURCE = "正常入学";
	    String OLD_AGRICULTER="农业户口";
	    String OLD_NON_AGRICULTER="非农业户口";
	    String AGRICULTER="农业家庭户口";
	    String NON_AGRICULTER="非农业家庭户口";
	    String CREDENTIAL_PERIOD_PLACEHOLDER="";
	    Iterator<StudentInfo> it = students.iterator();
	    int lineNumber = 2;
	    while(it.hasNext()){
	    	StudentInfo student = it.next();
	    	String registeredCharacter = student.getRegisteredCharacter();
	    	if(registeredCharacter.equals(OLD_AGRICULTER)){
	    		registeredCharacter = AGRICULTER;
	    	}else if(registeredCharacter.equals(OLD_NON_AGRICULTER)){
	    		registeredCharacter = NON_AGRICULTER;
	    	}else{
	    		logger.info("文件第 " + lineNumber + " 行，"+ "学生 [" + student.getName() + "] 学籍号 [" + student.getStudentCode() + "]：户口性质属性可能存在问题！目前户口性质为：" + registeredCharacter);
	    	}
	    	String studentSource = (student.getSource()==null || student.getSource().trim().equals(""))?SOURCE:student.getSource();
	    	
	    	records.add(new String[]{student.getName(),student.getStudentCode(),student.getId(),student.getSex(),student.getNativePlace(),
	    			student.getRace(),student.getAddress(),student.getContactPhone(),student.getSchoolName(),student.getSchoolId(),
	    			student.getDisabilityType(),student.getBirthday(),student.getBirthPlace(),student.getNational(),student.getIdType(),
	    			student.getGrade(),student.getGradeClass(),/*student.getOverseas()*/OVERSEAS_SHOW,student.getPolitics(),
	    			student.getHealth(),student.getRegisteredPlace(),
	    			/*student.getRegisteredCharacter()*/registeredCharacter,student.getEnterSchoolDate(),
	    			/*student.getEnterSchoolMethod()*/ENTERSCHOOL_SHOW,student.getStudyMethod(),student.getPresentAddr(),
	    			student.getContactAddr(),student.getZipCode(),student.getHomeAddr(),student.getOnlyChild(),student.getEnterCityChild(),student.getLeftChild(),
	    			student.getPreSchool(),student.getMartyrChild(),student.getOrphan(),student.getYiBu(),student.getNeedFund(),student.getAssistCode(),
	    			student.getCodeInClass(),/*student.getSource()*/studentSource,student.getFollowClass(),student.getFamilyMember(),student.getFamilyRelation(),
	    			student.getFamilyWorkplace(),student.getFamilyPresentAddr(),student.getFamilyRegisteredPlace(),student.getFamilyContactPhone(),
	    			CREDENTIAL_PERIOD_PLACEHOLDER});
	    	lineNumber ++;
	    }
	    return records;
	  }
}
