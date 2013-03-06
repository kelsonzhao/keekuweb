<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<div class="forumList" >
	<ul id="forumListUl">
		<c:forEach items="${forums}" var="forum">
			<li class="forum" ><a href=' <c:url value="/forum"/>/<c:out value="${forum.forumId }"/>/viewThreadsList' class="user"><c:out value="${forum.name }"/></a></li>
		</c:forEach>
	</ul>
</div>
<script type="text/javascript">
$(document).ready(function(){

});
</script>
