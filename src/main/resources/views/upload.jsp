<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" 
contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  	<title>upload page</title>
  </head>
  
  <body>
    This is my JSP page. <br>
    <div class="upload">
    	<form action="upload" enctype="multipart/form-data" method="post">
    		<input type="file" name="file"/><br/>
    		<input type="submit" value="上传"> 
    	</form>
    </div>
  </body>
</html>
