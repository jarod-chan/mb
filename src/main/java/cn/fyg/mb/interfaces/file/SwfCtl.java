package cn.fyg.mb.interfaces.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 采用swfupload 上传文件
 * 添加进度条功能
 * @author jhon.chen@gmail.com
 *
 */
@Controller
@RequestMapping("swf")
public class SwfCtl {
	
	public static final Logger logger=LoggerFactory.getLogger(SwfCtl.class);
	
	private static final String path="swf/";
	
	private interface Page{
		String UPLOAD=path+"upload";
	}
	
	@RequestMapping(value="upload",method=RequestMethod.GET)
	public String toUpload(){
		return Page.UPLOAD;
	}
	
	@RequestMapping(value="upload",method=RequestMethod.POST)
	@ResponseBody
	public void upload(@RequestParam("Filedata") MultipartFile file,@RequestParam("name")String name ){
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
	}
	
	
    @RequestMapping(value = "/doupload", method = RequestMethod.POST, produces = "text/json")
    public void doupload(@RequestParam("Filedata") MultipartFile file,
                           @RequestParam("requestid") String requestid,
                           @RequestParam("fieldName") String fieldName)
    {
        System.out.println("--\r\n");
        System.out
                .println("requestid=" + requestid + ";fieldName=" + fieldName);
        System.out.println("--\r\n");

        String filename = file.getOriginalFilename();
        OutputStream output = null;
        File outfile = null;
        try
        {
            filename = new String(filename.getBytes("iso-8859-1"), "UTF-8");
            String folderpath = "D:\\file-upload\\";
            String filePath = folderpath + filename;

            outfile = new File(filePath);
            output = new FileOutputStream(outfile);
            IOUtils.copy(file.getInputStream(),
                         output);

            System.out.println(filename);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (output != null)
            {
                try
                {
                    output.close();
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        }
    }
    
    public static final int MB = 1024 * 1024;

    @RequestMapping(value = "/doupload2", method = RequestMethod.POST)
    protected void doupload2(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory(10 * MB, new File("D://file-upload//temp//"));
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
        System.out.println("write file to '"+basePath + item.getName()+"'");
    }

}
