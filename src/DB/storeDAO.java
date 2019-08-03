package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class storeDAO {

	public static boolean insertData(storeDTO dto) throws SQLException {
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
			
			String mId = dto.getmId();
			String cName = dto.getcName();
			String mName = dto.getmName();
			String mNum = dto.getmNum();
			String mMaster = dto.getmMaster();
			String mAddr = dto.getmAddr();
			String mCall = dto.getmCall();
			String mEtc = dto.getmEtc();
			
			pstmt = con.prepareStatement("INSERT INTO Market VALUES (?,?,?,?,?,?,?,?)");
			pstmt.setString(1, mId);
			pstmt.setString(2, cName);
			pstmt.setString(3, mName);
			pstmt.setString(4, mNum);
			pstmt.setString(5, mMaster);
			pstmt.setString(6, mAddr);
			pstmt.setString(7, mCall);
			pstmt.setString(8, mEtc);
			
			
			int i = pstmt.executeUpdate();
			
			if (i == 1) {
				System.out.println("레코드 추가 성공");
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
	
	public static boolean updateData(storeDTO dto) throws Exception {
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버연결
			String url = "jdbc:mysql://localhost:3306/tinypos?serverTimezone=Asia/Seoul"; // DB연결
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, "root", "1234");
			System.out.println("데이터베이스 연결 성공");
			
			stmt = con.createStatement();
			String s = "delete from Market";
			int i = stmt.executeUpdate(s);
			
			if (i == 1) {
				System.out.println("레코드 삭제 성공");
				flag = true;
			}
			else {
				System.out.println("레코드 삭제 실패");
				flag = false;
			}
			
			String mId = dto.getmId();
			String cName = dto.getcName();
			String mName = dto.getmName();
			String mNum = dto.getmNum();
			String mMaster = dto.getmMaster();
			String mAddr = dto.getmAddr();
			String mCall = dto.getmCall();
			String mEtc = dto.getmEtc();
			
			pstmt = con.prepareStatement("INSERT INTO Market VALUES (?,?,?,?,?,?,?,?)");
			pstmt.setString(1, mId);
			pstmt.setString(2, cName);
			pstmt.setString(3, mName);
			pstmt.setString(4, mNum);
			pstmt.setString(5, mMaster);
			pstmt.setString(6, mAddr);
			pstmt.setString(7, mCall);
			pstmt.setString(8, mEtc);
			
			
			i = pstmt.executeUpdate();
			
			if (i == 1) {
				System.out.println("레코드 추가 성공");
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
			if (pstmt != null) { pstmt.close(); }
            if (con != null) { con.close(); }
		}

		return flag;
	}
	
	public static boolean loadData(storeDTO dto) throws Exception {
		Connection con = null;
		Statement stmt = null;
		boolean flag = false;
		ResultSet rs;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버연결
			String url = "jdbc:mysql://localhost:3306/tinypos?serverTimezone=Asia/Seoul"; // DB연결
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, "root", "1234");
			System.out.println("데이터베이스 연결 성공");
			
			stmt = con.createStatement();
			String s = "SELECT * FROM Market";
			rs = stmt.executeQuery(s);
			
			while(rs.next()) {
				
					dto.setcName(rs.getString("cName"));
					dto.setmName(rs.getString("mName"));
					dto.setmNum(rs.getString("mNum"));
					dto.setmMaster(rs.getString("mMaster"));
					dto.setmAddr(rs.getString("mAddr"));
					dto.setmCall(rs.getString("mCall"));
					dto.setmEtc(rs.getString("mEtc"));
			}
			flag = true;
			
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
