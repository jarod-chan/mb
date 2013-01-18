package cn.fyg.mb.interfaces.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("demo")
public class DemoCtl {
	
	public static final Logger logger=LoggerFactory.getLogger(DemoCtl.class);

	public static final int MB = 1024 * 1024;
	
	@RequestMapping(value="upload",method=RequestMethod.GET)
	public String toUpload(){
		return "demo/demo";
	}

	@RequestMapping(value = "/doupload", method = RequestMethod.POST)
	protected void doupload(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, IOException {

		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory(10 * MB,
				new File("D://file-upload//temp//"));
		ServletFileUpload uploader = new ServletFileUpload(fileItemFactory);
		uploader.setSizeMax(1200 * MB);
		List fields = null;
		try {
			fields = uploader.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
			return;
		}
		Iterator iter = fields.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();

			if (!item.isFormField()) {
				try {
					processUploadedFile(item);
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		}

		response.getOutputStream().println("200 OK");

	}

	private void processUploadedFile(FileItem item) throws Exception {
		String basePath = "D://file-upload//";
		item.write(new File(basePath + item.getName()));
		System.out.println("write file to '" + basePath + item.getName() + "'");
	}
	
	
	
	@RequestMapping(value = "/doupload3", method = RequestMethod.POST)
	public void upload(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		try{
			String uploadDir = "D://file-upload//";
			File dirPath = new File(uploadDir);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("uploadfile");//这里是表单的名字，在swfupload.js中this.ensureDefault("file_post_name", "filedata");
			
			InputStream stream = file.getInputStream();
			String fileName = file.getOriginalFilename();
			fileName = new String(fileName.getBytes(),"utf-8");
			String fileNameFull = uploadDir  + fileName;
			OutputStream bos = new FileOutputStream(fileNameFull);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			bos.close();
			// close the stream
			stream.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		//return new ModelAndView("redirect:/mb/demo/upload");//这里要制定返回页，如果返回Null,上传没问题，但是上传完毕后，页面会弹出404错误
		response.getOutputStream().println("200 OK");
	}
	
	
	@RequestMapping(value="doupload4",method=RequestMethod.POST)
	@ResponseBody
	public String upload4(@RequestParam("uploadfile") MultipartFile file ){
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		String fileDirectory="D:\\file-upload\\";
		File newFile=new File(fileDirectory+file.getOriginalFilename());
		try {
			file.transferTo(newFile);
		} catch (IllegalStateException e) {
			logger.error("保存文件出错", e);
		} catch (IOException e) {
			logger.error("保存文件出错", e);
		}
		return "200 OK";
	}

}
