package com.baobao.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.servlet.http.HttpServletRequest;

public class calFileSize {
	public static Float getFileSize(HttpServletRequest request,String path){
		float  size = 0.0f;
		 FileChannel fc= null;  
		    try {  
		        File f= new File(GetImageUrl.getImageUrl(request)+"/imageUpload"+System.getProperty("file.separator", "\\")+path);  
		        if (f.exists() && f.isFile()){  
		            FileInputStream fis= new FileInputStream(f);  
		            fc= fis.getChannel();
		            size = Math.round(fc.size()/((float)1024));
		        }  
		    } catch (FileNotFoundException e) {  
		       
		    } catch (IOException e) {  
		         
		    } finally {  
		        if (null!=fc){  
		            try{  
		                fc.close();  
		            }catch(IOException e){  
		                
		            }  
		        }   
		    }
			return size;  
		
	}
}	
