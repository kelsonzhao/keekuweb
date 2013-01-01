<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@page import="javax.naming.Context"  %> <%@page import="javax.naming.InitialContext"%> <%@page import="javax.sql.DataSource"%> <%@page import="java.sql.Connection"%> <%@page import="java.sql.ResultSet"%> <%@page import="java.sql.Statement"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=f-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
ResultSet rs = null;
Statement stmt = null;
Connection con = null;
try{
	
	 String strJNDIName = "jdbc/mySql";
	 Context ctx = null;
	 ctx = new InitialContext();
	 DataSource dataSource = (DataSource) ctx.lookup(strJNDIName);
	 con = dataSource.getConnection();
	 stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	 rs = stmt.executeQuery("select * from user_t");
	 while(rs.next()){
		 System.out.println(rs.getString("id"));
	 }
	
}catch(Exception e){
	
}finally{
	if(rs != null) {
		rs.close();
		rs = null;
	}
	if(stmt != null) {
		stmt.close();
		stmt = null;
	}
	if(con != null) {
		con.close();
		con = null;
	}
}

 -->

</body>
</html>