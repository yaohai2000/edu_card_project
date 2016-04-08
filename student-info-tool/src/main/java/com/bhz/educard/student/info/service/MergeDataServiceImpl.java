package com.bhz.educard.student.info.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bhz.educard.student.info.entity.StudentInfo;
//import com.bhz.utils.Utils;

public class MergeDataServiceImpl implements MergeDataService {

	private static Logger logger = LogManager.getLogger(MergeDataServiceImpl.class);

	public void merge(String pendingDataDir, String processedDataDir) throws Exception {
		File f = new File(pendingDataDir);
		File pd = new File(processedDataDir);
		List<StudentInfo> allStudents = new ArrayList<StudentInfo>();
		Map<String, StudentInfo> studentDB = new HashMap<String, StudentInfo>();
		StudentInfoService sis = new StudentInfoServiceImpl();
		if (!f.exists() || !f.isDirectory()) {
			logger.error("指定的待处理文件夹不存在:" + pendingDataDir);
			throw new Exception("指定的待处理文件夹不存在");
		}
		if (!pd.exists() || !pd.isDirectory()) {
			logger.error("指定的已处理文件夹不存在:" + processedDataDir);
			throw new Exception("指定的已处理文件夹不存在");
		}
		// 处理第一级目录：待处理文件目录
		File[] dir1 = f.listFiles();
		for (File f1 : dir1) {
			if (f1.isFile()) {
				logger.info("非文件夹：" + f1.getName());
				continue;
			}
			// 创建已处理目录，此级目录和待处理相同
//			File destDir1 = new File(pd.getAbsolutePath() + File.separator + f1.getName());
//			if (!destDir1.exists()) {
//				destDir1.mkdirs();
//			}
			// 处理第二级目录：年级目录
			File[] dir2 = f1.listFiles();
			//先找到csv文件，此文件作为学生信息数据库。
			for (File f2 : dir2) {
				if (f2.isFile()) {
					// 如果是文件，则判断是否为csv文件
					if (f2.getName().toLowerCase().endsWith(".csv")) {
						// 处理CSV文件，将此文件中的内容装入一个HashMap中，HashMap的键为身份证号，值为从csv文件中获取的学生信息
						List<StudentInfo> students = sis.parseStudentInfoFromCSV(f2.getAbsolutePath());
						allStudents.addAll(students);
						for (StudentInfo si : students) {
							studentDB.put(si.getId().trim().toLowerCase(), si);
						}
					}
					// 拷贝csv文件到处理完目录
//					Utils.copyFile(f2, new File(destDir1.getAbsolutePath() + File.separator + f2.getName()));
//					csvFilePath = destDir1.getAbsolutePath() + File.separator + f2.getName();
				}
			}
			//处理照片
			for (File f2 : dir2) {
				if (f2.isDirectory()) {// 第三级目录，照片父目录
					// 如果文件是目录，则此目录为照片目录，需要进入此目录中将各班目录下的学生照片进行处理。
					// 第四级目录，其中保持照片文件
					File[] dir3 = f2.listFiles();
					List<File> imageFiles = new ArrayList<File>();
					// 循环处理照片目录
					for (File f3 : dir3) {
						dealImageFiles(f3, imageFiles);
					}
					for (File img : imageFiles) {
						// 只处理jpg文件，其它文件不要处理
						if (img.getName().toLowerCase().endsWith(".jpg")) {
							String fileName = img.getName().trim();
							String fileId = fileName.substring(0, fileName.lastIndexOf("."));
							// 如果在学生信息csv文件中找到该学生的身份证号和文件名相同，则将此文件移动到已处理目录中
							if (studentDB.get(fileId.toLowerCase()) != null) {
//								img.renameTo(new File(destDir1.getAbsolutePath() + File.separator + img.getName()));
								img.renameTo(new File(pd.getAbsolutePath() + File.separator + img.getName()));
								studentDB.remove(fileId.toLowerCase());
							} else {
								logger.error("此文件无法找到对应信息：" + img.getAbsolutePath());
							}
						}
					}
				}
			}
		}
		for (Map.Entry<String, StudentInfo> entry : studentDB.entrySet()) {
			logger.warn("学生信息在 " + entry.getValue().getSourceFile() + " 文件中，但无法找到对应照片 [ 学生姓名：" + entry.getValue().getName() + "，学籍号："
					+ entry.getValue().getStudentCode() + "，身份证号：" + entry.getValue().getId() + "] ");
		}
		logger.info("开始合并学生信息...");
		sis.exportStudentInfoToCSV(allStudents,pd.getAbsolutePath() + File.separator + "学生信息整合（转换）.csv");
		logger.info("合并学生信息结束，整合后文件保存在：" + pd.getAbsolutePath() + File.separator + "学生信息整合（转换）.csv");
	}

	// 处理照片目录所有照片文件，照片文件可以在任意层次的目录。
	private List<File> dealImageFiles(File f, List<File> imageFile) {
		if (f != null) {
			if (f.isDirectory()) {
				File[] imgs = f.listFiles();
				if (imgs != null) {
					for (File img : imgs) {
						dealImageFiles(img, imageFile);
					}
				}
			}
			imageFile.add(f);
		}
		return imageFile;
	}
}
