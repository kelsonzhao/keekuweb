Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	};
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};
function toDate(longDate){
	var d =new Date(Number(longDate));
	return d.format('yyyy-MM-dd hh:mm:ss');
};

var Keeku = window.Keeku || {};
Keeku.Constant = Keeku.Constant || {};
Keeku.Constant.host = (function(){
	var strFullPath=window.document.location.href;
	var strPath=window.document.location.pathname;
	var pos=strFullPath.indexOf(strPath);
	var prePath=strFullPath.substring(0,pos);
	return(prePath);
})();
Keeku.Constant.root = (function(){
	var strFullPath=window.document.location.href;
	var strPath=window.document.location.pathname;
	var pos=strFullPath.indexOf(strPath);
	var prePath=strFullPath.substring(0,pos);
	var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
	return(prePath+postPath);
})();
/**
 * 分页
 */
(function($){
	$.fn.extend({
		myPage : function (settings) {//page start from zero.
			var opts = $.extend({},$.fn.myPage.defauls,settings);
			console.log("totalPage:" + opts.totalPage + "<<<>>> current page:" + opts.currentPage);
			if(opts.totalPage == null || Number(opts.totalPage) < 2 || opts.currentPage == null || Number(opts.currentPage)<0) {
				console.log("condition result false.");
				return ;
			}
			return this.each(function () {
				 var $this = $(this);
				 $this.addClass(opts.pageClassName);
				 if( opts.currentPage > 0 ) {//last page
					 var a = $("<a></a>");
					 a.text("上一页");
					 a.attr("page",opts.currentPage);
					 a.bind("click",opts.onClick);
					 $this.append(a);
				 }
				 var omitFlag = false;
				 for(var i = 0 ; i < opts.totalPage; i++ ) {//总页数大于或等于15页的情况下，a)第1页、最后一页总是显示 ，b)当前页的前后3页总是显示 ，c)其余隐藏
					 if(opts.totalPage > 10) {
						 if( i == 0 || i == 1 || (opts.totalPage - i) < 2 || ((opts.currentPage -3 <i) && ( i < opts.currentPage +3))){//显示 
							 omitFlag = false;
						 }else {
							 
							 if(!omitFlag) {
								 $this.append("...");
								omitFlag = true;	 
							 }
							 continue;
						 }
					 }
					 var a = $("<a></a>");
					 a.text(i+1);
					 a.attr("page",i+1);
					 if(i == opts.currentPage){
						 a.addClass('selected');
					 }else {
						 //console.log("onClick");
						 a.bind("click",opts.onClick);
					 }
					 $this.append(a);
				 }
				 if(opts.currentPage < opts.totalPage -1 ){//next page
					 var a = $("<a></a>");
					 a.text("下一页");
					 a.attr("page",opts.currentPage+2);
					 a.bind("click",opts.onClick);
					 $this.append(a);					 
				 }
			});
		}
	});
	$.fn.myPage.defauls = {
		totalPage : 0,
		currentPage : 0,
		pageClassName : "page",
		onClick : function(page) {
			
		}
	};
})(jQuery);