<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<div class="right-sidebar-top">
<div class="tile_item tile_item_1" >
   <div style="padding: 0px 10px;">
	    <c:choose>
	    	<c:when test="${username == null }">
	    	用户名：<input type="text" name="username"  id="username" value=""><br>
			密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password"  id="password" value="">
	    	</c:when>
	    	<c:otherwise>
	    		您好，<a href="#"><c:out value="${username}"/></a>
	    	</c:otherwise>
	    </c:choose>
	<hr class="clear">
		<ul class="button-list">
			    <c:choose>
			    	<c:when test="${username == null }">			
			    	  <li>
			    	   <a href="#"  onclick="loginSubmit();" class="button white" >登录</a>
			    	  </li>
			    	  <li>
				        <a href="#"   class="button white" title="帮助">帮助</a>
				      </li>	  
			    	</c:when>
			    	<c:otherwise>
    				  <li>
				        <a href="#"   class="button white" title="设置">设置</a>
				      </li>
					   <li>
				        <a href="#"   class="button white" title="帮助">帮助</a>
				      </li>	    
					  <li>
    					<a href="#"  onclick="logout();" class="button white" title="退出">退出</a>
    				   </li>
			    	</c:otherwise>  
			    </c:choose>
		</ul>
		</div>
	</div>
   <div class="tile_item tile_item_2" >
	 <div style="padding: 0px 10px;">
		<ul class="button-list">
			  <tiles:insertAttribute name="operations" />
		</ul>
	</div>
   </div>
</div>

<div class="right-sidebar-other">
	<div style="height: 220px;"></div>
	<div class="tile_item tile_item_3" >
	 <div style="padding: 0px 10px;">
		最新话题...<br>
		1....<br>
		2....
	</div>
   </div>
   	<div class="tile_item tile_item_3">
	 <div style="padding: 0px 10px;">
		最热话题...<br>
		1....<br>
		2....<br>
	</div>
   </div>
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
					ajaxLogin : "1"
				},
				dataType : "json",
				success : function(data) {
					if(data.success=='1') {
						window.location.reload();
					}else {
						alert(data.message);
					}
				},
				error : function(error){
				}
			});
		}
		function logout() {
			var url = '<c:url value="/logout"/>';
			$.ajax({
				url: url,
				type : "POST",
				dataType : "json",
				success : function() {
					window.location.reload();
				},
				error : function(error) {
				}
			});
		}
	
	</script>