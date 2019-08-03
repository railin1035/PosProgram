package DB;

import java.sql.*;

public class cartDAO {
	private static int cId =1;
	public static boolean insertData(cartDTO dto) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		boolean flag = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버연결
			String url = "jdbc:mysql://localhost:3306/tinypos?serverTimezone=UTC"; // DB연결
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, "root", "1234");
			System.out.println("데이터베이스 연결 성공");
			
			
			int total = dto.getTotal();
			
			pstmt = con.prepareStatement("insert into Cart values(?,?)");
			pstmt.setInt(1, cId);
			pstmt.setInt(2, total);
			
			int i = pstmt.executeUpdate();
			
			
			if (i == 1) {
				System.out.println("레코드 추가 성공");
				cId++;
				flag = true;
			}
			else {
				System.out.println("레코드 추가 실패");
				flag = false;
			}
			

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
			flag = false;
			
		} catch (SQLException e) {
			System.out.println("연결에 실패하였습니다.");
			flag = false;
		}
		finally {
			if (stmt != null) { stmt.close(); }
            if (con != null) { con.close(); }
		}

		return flag;
	}
}