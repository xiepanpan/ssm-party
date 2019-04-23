package com.baobao.util;

import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.write.Alignment;
import jxl.write.Label;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baobao.common.mapping.BranchInfoModelMapper;
import com.baobao.common.mapping.MemberInfoModelMapper;
import com.baobao.common.model.MemberInfoModel;

public class ExportExcel {
	@Autowired
	private BranchInfoModelMapper branchInfoMapper;
	public static XSSFWorkbook buildXSLXExcel(List<MemberInfoModel> list){
		
		XSSFWorkbook workBook = null;
		String[] cellTitle = {"用户ID","用户姓名","性别","入党时间","转正时间","手机号","职位","所在支部","党员类型","出生日期","学历"};
		workBook = new XSSFWorkbook();//创建工作薄
		XSSFSheet sheet = workBook.createSheet();
		workBook.setSheetName(0, "党员信息");
		createSheetForMem(workBook, sheet, cellTitle, list);
		return workBook;
		
	}
	//创建保存党员的信息
	public static void createSheetForMem(XSSFWorkbook workBook,XSSFSheet sheet,String[] cellTitle,List<MemberInfoModel> list){
		
		   //创建第一行标题 
		   XSSFRow titleRow = sheet.createRow((short) 0);//第一行标题
		   XSSFFont font = workBook.createFont();  
		   font.setColor(XSSFFont.COLOR_NORMAL);
		   font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		   XSSFCellStyle cellStyle = workBook.createCellStyle();//创建格式
		   cellStyle.setFont(font);
		   cellStyle.setAlignment(cellStyle.ALIGN_CENTER);
		   cellStyle.setVerticalAlignment(cellStyle.VERTICAL_CENTER);
		   for(int i = 0,size = cellTitle.length; i < size; i++){//创建第1行标题单元格    
			   XSSFCell cell = titleRow.createCell(i,0);        
			    cell.setCellStyle(cellStyle);
			    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			    cell.setCellValue(cellTitle[i]);
		   }
		   sheet.setColumnWidth(0, 4000);
		   sheet.setColumnWidth(1, 4000);
		   sheet.setColumnWidth(2, 4000);
		   sheet.setColumnWidth(3, 6000);
		   sheet.setColumnWidth(4, 6000);
		   sheet.setColumnWidth(5, 6000);
		   sheet.setColumnWidth(6, 6000);
		   sheet.setColumnWidth(7, 12000);
		   sheet.setColumnWidth(8, 6000);
		   sheet.setColumnWidth(9, 6000);
		   sheet.setColumnWidth(10, 6000);
		   if(list!=null && !list.isEmpty()){
			  for(int i=0,size=list.size();i<size;i++){
			     MemberInfoModel entity = list.get(i);
			     XSSFRow row = sheet.createRow((short) i+1);
			    
			     for (int j = 0,length=cellTitle.length; j < length; j++) {
			         XSSFCell cell = row.createCell(j, 0);// 在上面行索引0的位置创建单元格
			         cell.setCellType(XSSFCell.CELL_TYPE_STRING);// 定义单元格为字符串类型
			         
			       switch(j){// 在单元格中输入一些内容
				     case 0://成员ID
				    	cell.setCellValue(entity.getMemberId());
				     break;
			         case 1://成员姓名
			        	 cell.setCellValue(entity.getMemberName());
			         break;
			         case 2://性别
			        	 cell.setCellValue(entity.getMemberSex());
			        	
			         break;
			         case 3://入党时间
			        	 if(entity.getMemberRddate()!=null){
			        	 cell.setCellValue(entity.getMemberRddate());
			        	  XSSFCellStyle ctyle = workBook.createCellStyle();

			              XSSFDataFormat format= workBook.createDataFormat();

			              ctyle.setDataFormat(format.getFormat("yyyy年m月d日"));

			              cell.setCellStyle(ctyle);
			        	 }
			        	
			          break;
			         case 4://转正时间
			        	  if(entity.getMemberZzdate()!=null){
				          cell.setCellValue(entity.getMemberZzdate());
				          XSSFCellStyle ctyle2 = workBook.createCellStyle();

			              XSSFDataFormat format2= workBook.createDataFormat();

			              ctyle2.setDataFormat(format2.getFormat("yyyy年m月d日"));

			              cell.setCellStyle(ctyle2);
			       			}
				          break;
			         case 5://手机号
				          cell.setCellValue(entity.getMemberTel());
				          break;
			         case 6://职位
				          cell.setCellValue(entity.getMemberJob());
				          
				          break;
			         case 7://所在支部
			        	 
			        	  cell.setCellValue(entity.getBranchName());
			        	
				          break;
			         case 8://党员类型
			        	  cell.setCellValue(entity.getMemberType());
				        
			        	 break;
			         case 9://出生日期
			        	  if(entity.getMemberBirth()!=null){
				          cell.setCellValue(entity.getMemberBirth());
				          XSSFCellStyle ctyle3 = workBook.createCellStyle();

			              XSSFDataFormat format3= workBook.createDataFormat();

			              ctyle3.setDataFormat(format3.getFormat("yyyy年m月d日"));

			              cell.setCellStyle(ctyle3);
			        	  }
				          break;
			         case 10://学历
			        	
				          cell.setCellValue(entity.getMemberEdu());
				         
				          break;
			         }
			     	}
			    }
			  }
	
		}
	
}
