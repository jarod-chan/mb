package cn.fyg.mb.interfaces.file;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("file")
public class FileCtl {
	
	private static final String path="file/";
	
	private interface Page{
		String UPLOAD=path+"upload";
	}
	
	@RequestMapping(value="upload",method=RequestMethod.GET)
	public String toUpload(Map<String,Object> map){
		map.put("message","上传文件");
		return Page.UPLOAD;
	}
	
	@RequestMapping(value="upload",method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file,@RequestParam("name")String name ){
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		return "redirect:./upload";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(@RequestParam("name")String name){
		System.out.println(name);
		return "redirect:./upload";
	}

}
