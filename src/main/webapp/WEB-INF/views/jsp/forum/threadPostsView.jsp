<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<input type="hidden"  id="threadId"  value="<c:out value='${threadId}' />"  />
<input type="hidden"  id="pageNum"  value="<c:out value='${pageNum}' />"  />
<input type="hidden"  id="pageSize" value="<c:out value='${pageSize}' />"  />
<table class="topic_detail" id="topic_detail" border="0" cellspacing="0" cellpadding="0"  >
	<tbody >
		<tr data-template data-if-floor='1' >
			<td class="topic_main floor_left">
				<ul>
					<li><a href="<c:url value='/'/>__.authorUserId/home" >{{author.userName}}</a></li>
				</ul>
				<ul>
					<li><a href="#" class="user"><img alt="p"  src='<c:url value='/resources/style1/images/person-default.gif'/>'  align="middle"> </a></li>
					<li>帖子：{{author.postCount}}贴/{{author.replyCount}}回</li>
					<li>注册：{{author.regDate | date 'YYYY-MM-DD'}}</li>
				</ul>
			</td>
			<td class="topic_main floor_right"  >
				<div class="floor_right_top">
					<table style="width: 100%;">
						<tr>
							<td width="250px">发表于：{{createdDate | date 'YYYY-MM-DD HH:mm:ss'}} </td>
							<td width="250px">最后修改于：{{lastUpdatedDate | date 'YYYY-MM-DD HH:mm:ss'}} </td>
							<td  style="text-align: right;" >修改 | 回复 | {{floor | append '楼'}}</td>
						</tr>
					</table>
				</div>
				<div class="floor_right_title">
					{{subject}}
				</div>
				<div class="floor_right_main">{{message}}</div>
				<div class="floor_right_bottom">
					{{author.signature}}
				</div>					
			</td>
		</tr>
		<tr data-template >
			<td class="topic_reply floor_left">
				<ul>
					<li><a href="<c:url value='/'/>__.authorUserId/home" >{{author.userName}}</a></li>
				</ul>
				<ul>
					<li><a href="#" class="user"><img alt="p"  src='<c:url value='/resources/style1/images/person-default.gif'/>'  align="middle"> </a></li>
					<li>帖子：{{author.postCount}}贴/{{author.replyCount}}回</li>
					<li>注册：{{author.regDate | date 'YYYY-MM-DD'}}</li>
				</ul>					
			</td>
			<td class="topic_reply floor_right">
				<div class="floor_right_top">
					<table style="width: 100%;">
						<tr>
							<td width="250px">发表于：{{createdDate | date 'YYYY-MM-DD HH:mm:ss'}} </td>
							<td width="250px">最后修改于：{{lastUpdatedDate | date 'YYYY-MM-DD HH:mm:ss'}} </td>
							<td style="text-align: right;">{{floor | append '楼'}}</td>
						</tr>
					</table>				
				</div>
				<div class="floor_right_main">{{message}}</div>
				<div class="floor_right_bottom">bottom</div>					
			</td>
		</tr>
	</tbody>
</table>
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
