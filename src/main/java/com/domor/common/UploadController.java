package com.domor.common;

import com.domor.utils.Uploader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class UploadController {

	@RequestMapping("/uploadUEImage")
	public String uploadUEImage(MultipartFile upfile,HttpServletRequest request) throws Exception{
		Uploader up = new Uploader(request);
		up.setSavePath("/upload");
		String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
		up.setAllowFiles(fileType);
		up.setMaxSize(10000); //单位KB
		up.upload(upfile);

		String callback = request.getParameter("callback");
		String result = "{\"name\":\""+ up.getFileName() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize()
				+", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ up.getUrl() +"\"}";

		result = result.replaceAll( "\\\\", "\\\\" );
		if(callback == null ){
			return result ;
		}else{
			return "<script>"+ callback +"(" + result + ")</script>";
		}
	}

	@RequestMapping("/uploadImages")
	public Object uploadImages(HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String yearMonth = sdf.format(new Date());
		String separator = System.getProperty("file.separator") ;
		String relativePath = "uploads" + separator + yearMonth + separator;
		String ctxPath = request.getSession().getServletContext().getRealPath("/") + relativePath;
		File file = new File(ctxPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();
			String fileName = mf.getOriginalFilename();
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
			File uploadFile = new File(ctxPath + newFileName);// 文件名的格式：uploads/yyyyMM/yyyyMMddHHmmss_三位随机数.扩展名
			try {
				FileCopyUtils.copy(mf.getBytes(), uploadFile); // 产生图片到本地
				String normalPath = relativePath.replace("\\", "/");//windows中\是转义字符，故有2个，需转化为非转义字符/（LINUX默认就是/）
				result.put("images", normalPath + newFileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@RequestMapping("/uploadFiles")
	public Object uploadFiles(HttpServletRequest request) {
		Map<String, String> pathMap = new HashMap<String, String>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String yearMonth = sdf.format(new Date());
		String separator = System.getProperty("file.separator") ;
		String relativePath = "uploads" + separator + yearMonth + separator;
		String ctxPath = request.getSession().getServletContext().getRealPath("/") + relativePath;
		File file = new File(ctxPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();
			String fileName = mf.getOriginalFilename();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String newFileName = df.format(new Date()) + "_" + fileName;
			File uploadFile = new File(ctxPath + newFileName);// 文件名的格式：uploads/yyyyMM/yyyyMMddHHmmssSSS.扩展名
			try {
				FileCopyUtils.copy(mf.getBytes(), uploadFile); // 产生文件到本地
				String normalPath = relativePath.replace("\\", "/");//windows中\是转义字符，故有2个，需转化为非转义字符/（LINUX默认就是/）
				pathMap.put("url", normalPath + newFileName);
				pathMap.put("filename", fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pathMap;
	}
	
}
