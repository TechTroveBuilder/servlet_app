package websample11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Java入門 データベース接続クラス.
 */
public class DbConnect {

	public static void main(String[] args) {

		Connection con = null;
		
		try {
			
			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");
	
			// データベースと接続
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", 
											  "root",
											  "1234");
			
			// 接続しているデータベース名を表示
			System.out.println(con.getCatalog());
			
		} catch(ClassNotFoundException ce) {
			
			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
			
		} catch (SQLException se) {
			
			// データベースとの接続に失敗した場合
			se.printStackTrace();
			
		} finally {
			
			try {
				
				// データベースとの接続を解除
				if(con != null) {
					con.close();
				}
				
			} catch (SQLException se) {
				
				// データベースとの接続解除に失敗した場合
				se.printStackTrace();
			}
		}
	}
}