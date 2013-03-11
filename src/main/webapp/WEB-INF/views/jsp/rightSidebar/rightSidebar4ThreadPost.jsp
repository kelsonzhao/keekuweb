<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<li>
		<a href="<c:url value='/forumOpr/'/><c:out value= '${threadId}' />/createPostView"   class="button white" title="回帖">回贴</a>
		<a href="#" onclick="gotoNewThreadView();" class="button white" title="发新帖">发新帖</a>
</li>