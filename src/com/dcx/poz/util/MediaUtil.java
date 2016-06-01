package com.dcx.poz.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

public class MediaUtil {

	public static File multipartFile2File(MultipartFile file){
		if(file==null){
			return null;
		}
		CommonsMultipartFile cf= (CommonsMultipartFile)file;
        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
        File f = fi.getStoreLocation();
        return f;
	}
	public static Map<String, String> getMediaPlayTime(File file) throws Exception{
		if(file==null){
			return null;
		}
		Map<String, String> ret = new HashMap<String, String>();
		Encoder encoder = new Encoder();
		MultimediaInfo m = encoder.getInfo(file);
        long ls = m.getDuration();
        ret.put("length", ls/1000+"");//秒
        return ret;
	}
}
