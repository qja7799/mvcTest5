<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- boardDelete.jsp -->
<%
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/springproject";
	String user = "atom";
	String password = "1234";
	
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url, user, password);
	
	int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
	
	String sql = "delete from boardTest where idx = ?";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1, idx);
	pstmt.executeUpdate();
	
	pstmt.close();
	conn.close();	
%>
<script>
  alert("게시글이 삭제되었습니다.");
  location.href = "boardList.jsp";
</script>