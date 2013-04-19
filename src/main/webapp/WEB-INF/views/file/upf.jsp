<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />

<link rel="stylesheet" type="text/css" href="/mb/resources/plugin/uploadify/uploadify.css">


<script type="text/javascript" src="/mb/resources/js/jquery.js"></script>
<script type="text/javascript" src="/mb/resources/plugin/uploadify/jquery.uploadify.js"></script>

<style type="text/css">
 .uploadify{
 	float: left;
 }
</style>


<script type="text/javascript">
	$(function() {
	    $("#file_upload").uploadify({
	    	buttonText    : '上传文件',
	        swf           : '/mb/resources/plugin/uploadify/uploadify.swf',
	        uploader      : '/mb/upf/upload',
	        queueID : 'some_file_queue',
	        height        : 20,
	        width         : 80,
	        //返回一个错误，选择文件的时候触发
	        onSelectError:function(file, errorCode, errorMsg){
	            switch(errorCode) {
	                case -100:
	                    alert("上传的文件数量已经超出系统限制的"+$('#file_upload').uploadify('settings','queueSizeLimit')+"个文件！");
	                    break;
	                case -110:
	                    alert("文件 ["+file.name+"] 大小超出系统限制的"+$('#file_upload').uploadify('settings','fileSizeLimit')+"大小！");
	                    break;
	                case -120:
	                    alert("文件 ["+file.name+"] 大小异常！");
	                    break;
	                case -130:
	                    alert("文件 ["+file.name+"] 类型不正确！");
	                    break;
	            }
	        },
	        //检测FLASH失败调用
	        onFallback:function(){
	            alert("您未安装FLASH控件，无法上传文件！请安装FLASH控件后再试。");
	        },
	        //上传到服务器，服务器返回相应信息到data里
	        onUploadSuccess:function(file, data, response){
	          //  alert(data);
	        }
	    });
	});
 
 	 
</script>
</head>

<body>


<input id="file_upload" type="button" ><span>ssdd</span>
<div id="some_file_queue"></div>



</body>


</html>


