<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>My JSP 'sse.jsp' starting page</title>
  </head>
  
  <body>
  	<div id="msgFrompPush">  	</div>
  	<script type="text/javascript" src="<c:url value="assets/jquery.js"/>"></script>
  	<script type="text/javascript">
  		if(!!window.EventSource){
  			var source=new EventSource("push");
  			s='';
  			source.addEventListener('message',function(e){
  				s+=e.data+"<br/>"
  				$("#msgFrompPush").html(s);
  		});
  		source.addEventListener('open',function (e){
  			if(e.readyState==EventSource.CLOSED){
  				console.log("close")
  			}else{
  				console.log(e.readyState);
  			}
  		},false);
  		}else{
  			console.log("noSurpport");
  		}
  	</script>
  </body>
</html>
