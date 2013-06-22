<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<input id="forumId"  type="hidden"  value="<c:out value='${forumId}'/>"/>
<input id="threadId"  type="hidden"  value="<c:out value='${threadId}'/>"/>
<input id="postId"  type="hidden"  value="<c:out value='${postId}'/>"/>
<input id="updateFlag"  type="hidden"  value="<c:out value='${updateFlag}'/>"/>
<input id="title"  type="hidden"  value="<c:out value='${title}'/>"/>
<div><a href="<c:url value='/forum/' /><c:out value="${forumId}"/>/'viewThreadsList"><c:out value="${forumName}"/></a></div>
<div class="title_area">
	<c:choose>
		<c:when test="${subject != null }">
			<a href="<c:url value='/forum/thread/'/><c:out value='${threadId}'/>/viewpost/0/10"><c:out value="${subject}"/></a>
		</c:when>
		<c:otherwise><input id="txtTitle" name="tTitle" onclick="if (this.value == '请在这里输入标题，限30个字') {this.value = ''};" onblur="if (this.value == ''){ this.value = '请在这里输入标题，限30个字';}" type="text" class="txt_tit" value="请在这里输入标题，限30个字" autocomplete="off"></c:otherwise>
	</c:choose>
    <div  class="shadow1">
    </div>
    <div class="shadow2">
    </div>
</div>
<hr class="clear">
<div id="elm1" class="contentInput">
	<c:out value="${content }" escapeXml="false"></c:out>
</div>
<!-- Load TinyMCE -->
<script type="text/javascript" charset="utf-8" src="<c:url value='/scripts/tiny_mce/jquery.tinymce.js'/>"></script>
<script type="text/javascript">
	$().ready(function() {
		$('#elm1').tinymce({
			// Location of TinyMCE script
			script_url : '<c:url value="/scripts/tiny_mce/tiny_mce.js"/>',
			// General options
			theme : "advanced",
			/* relative_urls : false, */
			convert_urls : false,
			/* height : "800", */
			plugins : "autolink,lists,pagebreak,style,layer,table,advimage,advlink,emotions,inlinepopups,noneditable,visualchars,nonbreaking,xhtmlxtras,advlist",

			// Theme options
			theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|formatselect,fontselect,fontsizeselect,outdent,indent,pastetext,bullist,numlist,|,link,unlink,anchor,image,cleanup,code,forecolor,backcolor,|,undo,redo,",
			theme_advanced_buttons2 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,emotions,styleprops",
			theme_advanced_buttons3 : "",
			theme_advanced_buttons4 : "",
			theme_advanced_toolbar_location : "top",
			theme_advanced_toolbar_align : "left",
			theme_advanced_statusbar_location : "",
			theme_advanced_resizing : true,
			onchange_callback : "editorChange",

			// Example content CSS (should be your site CSS)
			content_css : '<c:url value="/scripts/tiny_mce/css/content.css"/>'

			// Drop lists for link/image/media/template dialogs
			/* template_external_list_url : "lists/template_list.js",
			external_link_list_url : "lists/link_list.js",
			external_image_list_url : "lists/image_list.js",
			media_external_list_url : "lists/media_list.js" */
			

			// Replace values for the template plugin
			/* template_replace_values : {
				username : "Some User",
				staffid : "991234"
			} */
		});
		if($("#title").val() != null && $("#title").val() !="" ) {
			$("#txtTitle").val($("#title").val());
		}
	});
	function submitPost(){
		var url;
		if($("#updateFlag").val() == "1") {
			url  = '<c:url value="/forumOpr"/>' +"/" + $("#threadId").val() +"/" + $("#postId").val() + "/editThread"  ;
		}else if($("#updateFlag").val() == "2") {
			url  = '<c:url value="/forumOpr"/>' +"/" + $("#threadId").val() +"/" + $("#postId").val() + "/editPost"  ;
		}else if(($("#updateFlag").val() == "3")){
			url  = '<c:url value="/forumOpr"/>' +"/" + $("#threadId").val() + "/createPost"  ;
		}else {
			url  = '<c:url value="/forumOpr"/>' +"/" + $("#forumId").val() + "/newthread"  ;
		};
		console.log("url:" + url);
		$.ajax({
			url: url,
			type : "post",
			data : {
				title :  $('#txtTitle').val() ,
				content :  $('#elm1').html(),
				threadId :  $('#threadId').val(),
				postId :  $('#postId').val()
			},
			dataType : "json",
			success : function(data){
				if(data.success == 1) {
					var redirect2 =  url;
					if($("#updateFlag").val() == "1" || $("#updateFlag").val() == "2" || $("#updateFlag").val() == "3") {
						redirect2  = '<c:url value="/forum/thread"/>' +"/" + $("#threadId").val() +"/viewpost/lastpage"   ;
					}else {
						redirect2  = '<c:url value="/forum/thread"/>' +"/" +data.threadId +"/viewpost/lastpage"   ;
					};	
					window.location.href = redirect2;
				}else {
					//shwo unsuccessful message
				}
			}
			
		});
		//$("#elm1").tinymce({height: "2000"});
	};
</script>
