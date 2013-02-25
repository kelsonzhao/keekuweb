<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<input type="hidden"  id="threadId"  value="<c:out value='${threadId}' />"  />
<input type="hidden"  id="pageNum"  value="<c:out value='${pageNum}' />"  />
<input type="hidden"  id="pageSize" value="<c:out value='${pageSize}' />"  />
<div class="topic_detail" id="topic_detail">
	<div class="topic_main" data-template data-if-floor='1'>
			<div class="floor_left">
				<ul>
					<li><a href="#">{{authorUserName}}</a></li>
					<li>XXX会员</li>
				</ul>
				<ul>
					<li><a href="#" class="user"><img alt="p"  src='<c:url value='/resources/style1/images/person-default.gif'/>'  align="middle"> </a></li>
					<li>注册时间</li>
				</ul>
			</div>
			<div class="floor_right">
			{{message}}
			</div>
			<hr class="clear">
	</div>
	<div class="topic_reply" data-template>
			<div class="floor_left">
				<ul>
					<li><a href="#">{{authorUserName}}</a></li>
					<li>XXX会员</li>
				</ul>
				<ul>
					<li><a href="#" class="user"><img alt="p"  src='<c:url value='/resources/style1/images/person-default.gif'/>'  align="middle"> </a></li>
					<li>注册时间</li>
				</ul>
			</div>
			<div class="floor_right">
			{{message}}
			</div>
			<hr class="clear">
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var topicDetail = Tempo.prepare('topic_detail');
	var url = '<c:url value="/forum/thread/"/>';
	url = url + $("#threadId").val() +"/post/list/"+$("#pageNum").val()+"/"+$("#pageSize").val() ;
	$.getJSON(url,function(data){
		if(data == null || data.data == null || data.data.content == null ||data.data.content < 1) {
			return ;
		}
		topicDetail.render(data.data.content);
	});
});
</script>
