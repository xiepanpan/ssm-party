package com.baobao.common.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baobao.common.model.MemberInfoModel;
import com.baobao.util.ReadExcel;

@Service
public class DataManService {
	public  void exportMemberInfo(){
		
	}
	public int importMemInfo(MultipartFile file){
		ReadExcel readExcel = new ReadExcel();
		try {
			List<List<Object>> lists = readExcel.read2007Excel(file, 0);
			List<MemberInfoModel> ListMemInfo = new ArrayList<MemberInfoModel>();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
}
