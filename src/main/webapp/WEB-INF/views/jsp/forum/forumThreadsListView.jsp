<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<input type="hidden"  id="forumId" value='<c:out value="${forumId }"/>'/>
<div class="threadList" >
	<ul id="threadListUl">
		<li class="thread" data-template><a href="#" class="user"><img alt="p"  src='<c:url value='/resources/style1/images/person-default.gif'/>'  align="middle"> </a>
			<div class="threadBody">
				<h2>
					<a href="#" data-href="../thread/{{threadId}}/viewpost/0/10">{{subject}}</a>
				</h2>
				<div class="Tags"></div>
				<div class="Date">
					<a href="#">{{authorUserName}}</a>发布于{{createdDate | date 'YYYY-MM-DD HH:mm:ss'}} , <a href="#">{{lastUpdatedBy}}</a>最后回复于{{lastUpdatedDate | date 'YYYY-MM-DD HH:mm:ss'}}.
				</div>
			</div>
			<div class="stat">
				<ul>
					<li class="reply"><em>{{replies}}</em>回复</li>
					<li class="view"><em>{{views}}</em>浏览</li>
				</ul>
			</div>
			<hr class="clear"></li>
	</ul>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var threadListUI = Tempo.prepare('threadListUl');
	$.getJSON('<c:url value="/forum"/>'+"/"+$("#forumId").val()+"/thread/list",function(data){
		if(data == null || data.data == null || data.data.content == null ||data.data.content < 1) {
			return ;
		}
		threadListUI.render(data.data.content);
		//alert(">>:" + data.data.numberOfElements);
/* 		var threadList = $("#threadListUl");
		$.each(data.data.content,function(key,val){
			var li = $("<li class='thread'></li>");
			var icon = $('<a href="#" class="user"> </a>');
			var img = $('<img alt="p"  align="middle">');
			img.attr("src","<c:url value='/resources/style1/images/person-default.gif'/>");
			icon.append(img);
			var postBody = $('<div class="threadBody"></div>');
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
			threadList.append(li);
		}); */
	});
});
</script>
