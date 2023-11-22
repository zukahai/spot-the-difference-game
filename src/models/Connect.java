package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Connect {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement ps;
	String dbName = "data.db";

	public Connection connect() {
        try {
        	Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + dbName;
            Connection connection = DriverManager.getConnection(url);
            System.out.println("Kết nối đến SQLite đã được thiết lập.");
//            createTable(connection);
            return connection;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
	
	public void createTable(Connection connection) {
	    try (Statement statement = connection.createStatement()) {
	        // Câu lệnh SQL để tạo bảng `account`
	        String createAccountTableSQL = "CREATE TABLE IF NOT EXISTS account ("
	                + "Username TEXT NOT NULL,"
	                + "Password TEXT NOT NULL"
	                + ");";
	        statement.execute(createAccountTableSQL);

	        // Câu lệnh SQL để tạo bảng `information`
	        String createInformationTableSQL = "CREATE TABLE IF NOT EXISTS information ("
	                + "Username TEXT NOT NULL,"
	                + "FullName TEXT NOT NULL,"
	                + "Age INTEGER NOT NULL,"
	                + "Score INTEGER NOT NULL"
	                + ");";
	        statement.execute(createInformationTableSQL);

	        System.out.println("Bảng đã được tạo nếu nó chưa tồn tại.");
	    } catch (SQLException e) {
	        System.err.println(e.getMessage());
	    }
	}


	
	public int executeDB(String sql) { //insert, update, delete
		int record=0;
		try {
			conn = connect();
			stmt = conn.createStatement();
			record = stmt.executeUpdate(sql); //so luong hang thay doi sau khi thuc hien 1 trong 3 cau lenh tren
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
				stmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			} finally {
	            try {
	                if (conn != null) conn.close();
	                if (ps != null) ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
		}
		return record;
	}
	public ResultSet listAll(String sql) {
		try {
			conn = connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null) conn.close();
				if (stmt != null) stmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return rs;
	
	}
	public void deleteAllData() {
        try {
            conn = connect();

            // Xoá hết dữ liệu từ bảng `account`
            String deleteAccountDataSQL = "DELETE FROM account;";
            ps = conn.prepareStatement(deleteAccountDataSQL);
            ps.executeUpdate();

            // Xoá hết dữ liệu từ bảng `information`
            String deleteInformationDataSQL = "DELETE FROM information;";
            ps = conn.prepareStatement(deleteInformationDataSQL);
            ps.executeUpdate();

            System.out.println("Đã xoá hết dữ liệu từ cả hai bảng.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Connect connector = new Connect();

        // Gọi hàm để xoá hết dữ liệu trong cả hai bảng
        connector.deleteAllData();
    }



}
