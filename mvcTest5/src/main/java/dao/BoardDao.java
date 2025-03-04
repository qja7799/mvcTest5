package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.BoardVo;

public class BoardDao {

	//싱글톤으로 만들어서 객체가 한번만 생성됨 그걸 호출하는것
	//생성이 아니라 호출해서 쓰는것
	private Connection conn = DbConnection.getConn();//DbConnection여기에서 getConn을 스태틱으로 선언해서 클래스명.함수명으로 호출가능
	private Connection conn2 = DbConnection.getConn();//싱글톤은 객체를 한번만 생성하기에 conn과 conn2는 같은 객체를 사용하게됨 (getConn을 호출해서 쓰기에 같은것)
	
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	String sql = "";
	BoardVo vo = null;
	
	//PreparedStatement닫는 메서드
	public void pstmtClose() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {}
		}
	}
	
	//ResultSet과 PreparedStatement 닫는 메서드
	public void rsClose() {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
			finally {
				pstmtClose();
			}
		}
	}
	
	//게시글 전체 리스트
	public List<BoardVo> getBoardList() {
		List<BoardVo> vos = new ArrayList<BoardVo>();
		if(conn.equals(conn2)) {
			System.out.println("conn과 conn2는 같은 객체 입니다");
		}else {
			System.out.println("conn과 conn2는 다른 객체 입니다");
		}
		
		try {
			sql = "select * from boardTest order by idx desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setReadNum(rs.getInt("readNum"));
				vo.setwDate(rs.getDate("wDate"));
			  	vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("sql오류(getBoardList) : " + e.getMessage());
		}finally {
			rsClose();
		}
		
		return vos;
	}
	
	
	//게시글 등록처리하기
	public int setBoardInput(BoardVo vo) {
		int res = 0;
		try {
			sql = "insert into boardTest values (default,?,?,?,?,default,default)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getHostIp());
			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("sql오류(setBoardInput) : " + e.getMessage());
		}finally {
			//rsClose(); -> rs는 셀렉트에서만 쓰이니까 셀렉트 썻을때만 닫아주는거
			pstmtClose();
		}
		System.out.println("setBoardInput_res=>"+res);
		return res;
	}
	
	
	// 글 조회수 증가하기
		public void setReadNumUpdate(int idx) {
			try {
				sql = "update boardTest set readNum = readNum + 1 where idx = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, idx);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("sql오류(setReadNumUpdate) : " + e.getMessage());
			} finally {
				pstmtClose();
			}
		}

		// 글내용 조회하기
		public BoardVo getBoardContent(int idx) {
			vo = new BoardVo();
			try {
				sql = "select * from boardTest where idx = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, idx);
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					//vo.setIdx(rs.getInt("idx"));
					vo.setIdx(idx);
					vo.setName(rs.getString("name"));
					vo.setwDate(rs.getDate("wDate"));
					vo.setReadNum(rs.getInt("readNum"));
					vo.setHostIp(rs.getString("hostIp"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
				}
			} catch (SQLException e) {
				System.out.println("sql오류(getBoardContent) : " + e.getMessage());
			} finally {
				rsClose();
			}
			return vo;
		}

		// 게시글 삭제처리
		public int setBoardDelete(int idx) {
			int res = 0;
			try {
				sql = "delete from boardTest where idx = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, idx);
				res = pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("sql오류(setBoardDelete) : " + e.getMessage());
			} finally {
				pstmtClose();
			}
			return res;
		}
		
	
}
