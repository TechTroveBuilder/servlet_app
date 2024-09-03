package websample11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Java入門 データベース接続クラス（DAO）.
 */
public class SampleDao {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet  rs  = null;
		
		try {
			
			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");
	
			// データベースと接続
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", 
											  "root",
											  "1234");

			ps = con.prepareStatement("select * from user");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString("id"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("age"));
				System.out.println(rs.getString("pass"));
				System.out.println("------");
			}
			
			
		} catch(ClassNotFoundException ce) {
			
			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
			
		} catch (SQLException se) {
			
			// データベースとの接続に失敗した場合
			se.printStackTrace();
			
		} finally {
			
			try {
				
				// データベースとの接続を解除する
				if(con != null) {
					con.close();
				}
				if(ps != null) {
					ps.close();
				}
				if(rs != null) {
					rs.close();
				}
				
			} catch (SQLException se) {
				
				// データベースとの接続解除に失敗した場合
				se.printStackTrace();
			}
		}
	}
}