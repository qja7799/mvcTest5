package dao;

import java.sql.Connection;

//싱글톤으로 작업함
public class DBConnection2 {
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/springproject";
	private String user = "atom";
	private String password = "1234";
	
	//싱글톤으로 con객체를 만들어서 사용하기
	private static Connection con;
	
}
