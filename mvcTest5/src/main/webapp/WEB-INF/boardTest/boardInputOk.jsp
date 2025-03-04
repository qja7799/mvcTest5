<%@page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- boardInputOk.jsp -->
<%
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/springproject";
	String user = "atom";
	String password = "1234";
	
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url, user, password);

	String name = request.getParameter("name")==null ? "" : request.getParameter("name");
	String title = request.getParameter("title")==null ? "" : request.getParameter("title");
	String content = request.getParameter("content")==null ? "" : request.getParameter("content");
	String hostIp = request.getParameter("hostIp");
	
	String sql = "insert into boardTest values (default,?,?,?,?,default,default)";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, name);
	pstmt.setString(2, title);
	pstmt.setString(3, content);
	pstmt.setString(4, hostIp);
	pstmt.executeUpdate();

	pstmt.close();
	conn.close();
%>
<script>
  alert("게시글이 입력되었습니다.");
  location.href = "boardList.jsp";
</script>