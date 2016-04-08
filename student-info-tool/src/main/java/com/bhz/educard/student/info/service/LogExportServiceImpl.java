package com.bhz.educard.student.info.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.bhz.educard.ResultMessage;

public class LogExportServiceImpl implements LogExportService {
	public int export(String content,File savePath){
		if(savePath.exists()){
			savePath.delete();
		}
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(savePath));
			bw.write(content);
			bw.flush();
		} catch (IOException e) {
			return ResultMessage.OP_ERROR;
		}finally{
			if(bw!=null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ResultMessage.OP_SUCCESS;
	}
}
