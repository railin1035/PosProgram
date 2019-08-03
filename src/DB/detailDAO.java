package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class detailDAO {
	private static int oId = 0;
	public static boolean insertData(ArrayList<detailDTO> list) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		boolean flag = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버연결
			String url = "jdbc:mysql://localhost:3306/tinypos?serverTimezone=UTC"; // DB연결
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, "root", "1234");
			System.out.println("데이터베이스 연결 성공");
			
			for(int i=0; i<list.size(); i++) {
				pstmt = con.prepareStatement("insert into Cart_Detail values(?,?,?,?,?)");
				pstmt.setInt(1, list.get(i).getoId());
				pstmt.setInt(2, list.get(i).getcId());
				pstmt.setInt(3, list.get(i).getpId());
				pstmt.setInt(4, list.get(i).getAmount());
				pstmt.setInt(5, list.get(i).getCost());
				pstmt.executeUpdate();
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
			if (pstmt != null) { pstmt.close(); }
            if (con != null) { con.close(); }
		}

		return flag;
	}
	
	public static boolean loadData(int cartId, detailDTO dto) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		ResultSet rs;
		int totalCost = 0;
		int amount = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버연결
			String url = "jdbc:mysql://localhost:3306/tinypos?serverTimezone=Asia/Seoul"; // DB연결
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, "root", "1234");
			System.out.println("데이터베이스 연결 성공");

			pstmt = con.prepareStatement("SELECT * FROM Cart_Detail");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt("cId")== cartId) {
					totalCost += rs.getInt("cost");
					amount += rs.getInt("amount");
				}
			}
			dto.setAmount(amount);
			dto.setCost(totalCost);
			
			//System.out.println(dto.getAmount()+"개");
			//System.out.println(dto.getCost()+"원");
			
			flag = true;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
			flag = false;
			
		} catch (SQLException e) {
			System.out.println("연결에 실패하였습니다.");
			flag = false;
		}
		finally {
			if (pstmt != null) { pstmt.close(); }
            if (con != null) { con.close(); }
		}

		return flag;
	}
}
