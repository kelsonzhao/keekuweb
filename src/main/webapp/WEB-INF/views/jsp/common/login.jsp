<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/style1/style.css'/>">
<script type="text/javascript" charset="utf-8" src="<c:url value='/scripts/jQuery/jquery-1.8.3.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/scripts/common.js'/>"></script>

<div style="text-align: center;">
			用户名：<input type="text" name="username"  id="username" value=""><br>
			密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password"  id="password" value="">
			<ul class="button-list">
					<li>
			    	   <a href="#"  onclick="loginSubmit();" class="button white" >登录</a>
			    	 </li>
			</ul>
</div>
<script type="text/javascript">
		function loginSubmit() {
			var url = '<c:url value="/login"/>';
			$.ajax({
				url: url,
				type : "POST",
				data : {
					username : $("#username").val() ,
					password : $("#password").val(),
					ajaxLogin : "1",
					needRedirect : "1"
				},
				dataType : "json",
				success : function(data) {
					console.log(data);
					if(data.success=='1') {
						if(data.needRedirect == '1'){
							var backUrl = Keeku.Constant.host + data.backUrl;
							window.location.href = backUrl;
						}else {
							window.location.reload();
						}
					}else {
						alert(data.message);
					}
				},
				error : function(error){
				}
			});
		}
	
	</script>