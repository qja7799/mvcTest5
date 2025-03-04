package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//싱글톤으로 작업함
public class DbConnection {
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/springproject";
	private String user = "atom";
	private String password = "1234";
	
//	정리
//	private static DbConnection instance = new DbConnection();
//	=> static 키워드가 붙어 있으므로 프로그램 실행 중 DbConnection 클래스가 로딩(접근)될 때 단 한 번만 생성됨
// 	즉, getConn()을 호출하면 자동으로 DbConnection가 로딩(접근)되므로 이때 instance = DbConnection()가 생성되는것임
//	그렇게 getConn()을 호출해서 instance가 생성된 후 getConn()은 instance로 생성된 conn객체를 불러오게 되는거임
//	이후에 또 getConn()가 호출될시에는 이미 instance가 생성된 상태고 instance는 스태틱으로 선언되어있기때문에
//	더이상 instance가 생성되지않고 getConn()으로 instance가 생성한 conn을 호출만 하게되는것임
//	즉, 항상 같은 객체(싱글톤 인스턴스)가 반환됨.
	
	// 싱글톤으로 conn객체를 만들어서 사용하기
	private static Connection conn;
	private static DbConnection instance = new DbConnection();
	
	private DbConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 오류~" + e.getMessage());//라이브러리폴더에 mysql커넥터가 없거나 거기에 문제가 있을경우
		} catch (SQLException e) {
			System.out.println("데이터베이스 연동 실패~~" + e.getMessage());//db연결 실패
		}
	}
	
	public static Connection getConn() { //커스텀한 겟함수
	return conn;
}
}
