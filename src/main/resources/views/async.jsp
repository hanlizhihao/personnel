<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'async.jsp' starting page</title> 
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

  </head>
  
  <body>
    <script type="text/javascript" src="assets/jquery.js"></script>
    <script type="text/javascript">
    	deferred();
    	function deferred(){
    		$.get('defer',function(data){
    			console.log(data);
    			deferred();
    		})
    	}
    </script>
  </body>
</html>
