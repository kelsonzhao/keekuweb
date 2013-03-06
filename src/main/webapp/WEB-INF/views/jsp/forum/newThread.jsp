<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<input id="forumId"  type="hidden"  value="<c:out value='${forumId}'/>"/>
<div class="title_area">
    <input id="txtTitle" name="tTitle" onclick="if (this.value == '请在这里输入标题，限30个字') {this.value = ''};" onblur="if (this.value == ''){ this.value = '请在这里输入标题，限30个字';}" type="text" class="txt_tit" value="请在这里输入标题，限30个字" autocomplete="off">
    <div  class="shadow1">
    </div>
    <div class="shadow2">
    </div>
</div>
<hr class="clear">
<div id="elm1" class="contentInput">
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
	});
	function submitNewthread(){
		var url = '<c:url value="/forumOpr"/>' +"/" + $("#forumId").val() + "/newthread"  ;
		//var threadContent  = $('#elm1').html();
		/* console.log(threadContent);
		return; */
		//$("#show").html(threadContent);
		$.ajax({
			url: url,
			type : "post",
			data : {
				title :  $('#txtTitle').val() ,
				content :  $('#elm1').html()
			},
			dataType : "json",
			success : function(data){
				alert(data.success);
			}
			
		});
		//$("#elm1").tinymce({height: "2000"});
	};
	function editorChange(){
		
	};
</script>
