package com.bhz.educard.student.info.entity;

public class StudentInfo implements java.io.Serializable{
	private static final long serialVersionUID = 6347361800434392156L;
	private String name;				//学生姓名
	private String studentCode;			//学籍号
	private String id;					//身份证件号
	private String sex;					//性别
	private String nativePlace;			//籍贯
	private String race;				//民族
	private String address;				//家庭地址
	private String contactPhone;		//联系电话
	private String schoolName;			//学校名称
	private String schoolId;			//学校标识码
	private String disabilityType;		//残疾类型
	private String birthday;			//出生日期
	private String birthPlace;			//出生地
	private String national;			//国籍地区
	private String idType;				//身份证件类型
	private String grade;				//年级
	private String gradeClass;			//班级
	private String overseas;			//港澳台侨外
	private String politics;			//政治面貌
	private String health;				//健康状况
	private String registeredPlace;		//户口所在地
	private String registeredCharacter; //户口性质
	private String enterSchoolDate;		//入学年月
	private String enterSchoolMethod;	//入学方式
	private String studyMethod;			//就读方式
	private String presentAddr;			//现住址
	private String contactAddr;			//通讯地址
	private String zipCode;				//邮政编码
	private String homeAddr;			//家庭住址
	private String onlyChild;			//是否独生子女
	private String enterCityChild;		//是否进城务工人员子女
	private String leftChild;			//是否留守儿童
	private String preSchool;			//是否受过学前教育
	private String martyrChild;			//是否烈士优抚子女
	private String orphan;				//是否孤儿
	private String yiBu;				//是否享受一补
	private String needFund;			//是否需要申请资助
	private String assistCode;			//学籍辅号
	private String codeInClass;			//班内学号
	private String source;				//学生来源
	private String followClass;			//随班就读
	private String familyMember;		//家庭成员姓名
	private String familyRelation;		//关系
	private String familyWorkplace;		//工作单位
	private String familyPresentAddr;	//现住址
	private String familyRegisteredPlace;//户口所在地
	private String familyContactPhone;	//联系电话
	private String sourceFile;//此信息的文件来源
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getDisabilityType() {
		return disabilityType;
	}
	public void setDisabilityType(String disabilityType) {
		this.disabilityType = disabilityType;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public String getNational() {
		return national;
	}
	public void setNational(String national) {
		this.national = national;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getGradeClass() {
		return gradeClass;
	}
	public void setGradeClass(String gradeClass) {
		this.gradeClass = gradeClass;
	}
	public String getOverseas() {
		return overseas;
	}
	public void setOverseas(String overseas) {
		this.overseas = overseas;
	}
	public String getPolitics() {
		return politics;
	}
	public void setPolitics(String politics) {
		this.politics = politics;
	}
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	public String getRegisteredPlace() {
		return registeredPlace;
	}
	public void setRegisteredPlace(String registeredPlace) {
		this.registeredPlace = registeredPlace;
	}
	public String getRegisteredCharacter() {
		return registeredCharacter;
	}
	public void setRegisteredCharacter(String registeredCharacter) {
		this.registeredCharacter = registeredCharacter;
	}
	public String getEnterSchoolDate() {
		return enterSchoolDate;
	}
	public void setEnterSchoolDate(String enterSchoolDate) {
		this.enterSchoolDate = enterSchoolDate;
	}
	public String getEnterSchoolMethod() {
		return enterSchoolMethod;
	}
	public void setEnterSchoolMethod(String enterSchoolMethod) {
		this.enterSchoolMethod = enterSchoolMethod;
	}
	public String getStudyMethod() {
		return studyMethod;
	}
	public void setStudyMethod(String studyMethod) {
		this.studyMethod = studyMethod;
	}
	public String getPresentAddr() {
		return presentAddr;
	}
	public void setPresentAddr(String presentAddr) {
		this.presentAddr = presentAddr;
	}
	public String getContactAddr() {
		return contactAddr;
	}
	public void setContactAddr(String contactAddr) {
		this.contactAddr = contactAddr;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getHomeAddr() {
		return homeAddr;
	}
	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}
	public String getOnlyChild() {
		return onlyChild;
	}
	public void setOnlyChild(String onlyChild) {
		this.onlyChild = onlyChild;
	}
	public String getEnterCityChild() {
		return enterCityChild;
	}
	public void setEnterCityChild(String enterCityChild) {
		this.enterCityChild = enterCityChild;
	}
	public String getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(String leftChild) {
		this.leftChild = leftChild;
	}
	public String getPreSchool() {
		return preSchool;
	}
	public void setPreSchool(String preSchool) {
		this.preSchool = preSchool;
	}
	public String getMartyrChild() {
		return martyrChild;
	}
	public void setMartyrChild(String martyrChild) {
		this.martyrChild = martyrChild;
	}
	public String getOrphan() {
		return orphan;
	}
	public void setOrphan(String orphan) {
		this.orphan = orphan;
	}
	public String getYiBu() {
		return yiBu;
	}
	public void setYiBu(String yiBu) {
		this.yiBu = yiBu;
	}
	public String getNeedFund() {
		return needFund;
	}
	public void setNeedFund(String needFund) {
		this.needFund = needFund;
	}
	public String getAssistCode() {
		return assistCode;
	}
	public void setAssistCode(String assistCode) {
		this.assistCode = assistCode;
	}
	public String getCodeInClass() {
		return codeInClass;
	}
	public void setCodeInClass(String codeInClass) {
		this.codeInClass = codeInClass;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getFollowClass() {
		return followClass;
	}
	public void setFollowClass(String followClass) {
		this.followClass = followClass;
	}
	public String getFamilyMember() {
		return familyMember;
	}
	public void setFamilyMember(String familyMember) {
		this.familyMember = familyMember;
	}
	public String getFamilyRelation() {
		return familyRelation;
	}
	public void setFamilyRelation(String familyRelation) {
		this.familyRelation = familyRelation;
	}
	public String getFamilyWorkplace() {
		return familyWorkplace;
	}
	public void setFamilyWorkplace(String familyWorkplace) {
		this.familyWorkplace = familyWorkplace;
	}
	public String getFamilyPresentAddr() {
		return familyPresentAddr;
	}
	public void setFamilyPresentAddr(String familyPresentAddr) {
		this.familyPresentAddr = familyPresentAddr;
	}
	public String getFamilyRegisteredPlace() {
		return familyRegisteredPlace;
	}
	public void setFamilyRegisteredPlace(String familyRegisteredPlace) {
		this.familyRegisteredPlace = familyRegisteredPlace;
	}
	public String getFamilyContactPhone() {
		return familyContactPhone;
	}
	public void setFamilyContactPhone(String familyContactPhone) {
		this.familyContactPhone = familyContactPhone;
	}
	public String getSourceFile() {
		return sourceFile;
	}
	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	@Override
	public String toString() {
		return "学生姓名： " + this.name + "\t学籍号：" + this.studentCode + "\t身份证件号: " + this.id;
	}
}
