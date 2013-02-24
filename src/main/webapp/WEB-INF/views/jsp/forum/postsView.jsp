<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<input type="hidden"  id="threadId"  value="<c:out value='${threadId}' />"  />
<input type="hidden"  id="pageNum"  value="<c:out value='${pageNum}' />"  />
<input type="hidden"  id="pageSize" value="<c:out value='${pageSize}' />"  />
<div class="postList">
	<ul id="postListUl">
	</ul>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var url = '<c:url value="/forum/thread/"/>';
	url = url + $("#threadId").val() +"/post/list/"+$("#pageNum").val()+"/"+$("#pageSize").val() ;
	$.getJSON(url,function(data){
		console.log(data);
		
	});
  /* 	$.getJSON('<c:url value="/forum/thread/list"/>',function(data){
		//alert(">>:" + data.data.numberOfElements);
		var postList = $("#postLIstUl");
		$.each(data.data.content,function(key,val){
			var li = $("<li class='post'></li>");
			var icon = $('<a href="#" class="user"> </a>');
			var img = $('<img alt="p"  align="middle">');
			img.attr("src","<c:url value='/resources/style1/images/person-default.gif'/>");
			icon.append(img);
			var postBody = $('<div class="postBody"></div>');
			var pb_title = $('<h2><a href="#">'+val.subject+'</a></h2>');
			var tags = $('<div class="Tags"></div>');
			var updateDate = $('<div class="Date">'+'<a href="#">'+val.authorUserName+'</a>'+'发布于'+toDate(val.createdDate)+', <a href="#">'+val.lastUpdatedBy+'</a>'+'最后回复于 '+toDate(val.lastUpdatedDate)+'</div>');
			postBody.append(pb_title).append(tags).append(updateDate);
			var stat = $('<div class="stat"></div>');
			var stat_ul =$('<ul></ul>');
			var li_reply = $('<li class="reply"><em>'+val.replies+'</em>回复</li>');
			var li_view = $('<li class="view"><em>'+val.views+'</em>浏览</li>');
			stat_ul.append(li_reply).append(li_view);
			stat.append(stat_ul);
			var clear =$('<hr class="clear">');
			li.append(icon).append(postBody).append(stat).append(clear);
			postList.append(li);
		});
	}); */
});
</script>
