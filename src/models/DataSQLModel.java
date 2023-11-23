package models;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataSQLModel {
	Connect cn = new Connect();

	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement ps;

	public DataSQLModel() {
		conn = new Connect().connect();
	}

	public boolean Login(String Username, String Password) throws SQLException, UnknownHostException, IOException {
		rs = cn.listAll("Select * from account where Username = '" + Username + "' and Password = '" + Password + "'");
		return rs.next();
	}

	public boolean Insert(String Username, String Password, String FullName, int Age) {
		conn = new Connect().connect();
		String sql1 = "INSERT INTO account VALUES (?,?)";
		try {
			ps = conn.prepareStatement(sql1);
			ps.setString(1, Username);
			ps.setString(2, Password);
			int record = ps.executeUpdate();
			if (record == 0)
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		conn = new Connect().connect();
		String sql2 = "INSERT INTO information (Username, FullName, Age, Score) VALUES (?,?,?,0)";
		try {
			ps = conn.prepareStatement(sql2);
			ps.setString(1, Username);
			ps.setString(2, FullName);
			ps.setInt(3, Age);

			int record = ps.executeUpdate();
			return record > 0;
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<User> getListUser() {
		ArrayList<User> ListUser = new ArrayList<User>();
		String sql = "Select * from information order by Score DESC";
		conn = new Connect().connect();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString(1));
				user.setFullname(rs.getString(2));
				user.setAge(rs.getInt(3));
				user.setScore(rs.getInt(4));
				ListUser.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ListUser;
	}

	public void increasePoint(String Username) {
		System.out.println("Update Score @@@@@@@@");
		conn = new Connect().connect();
		String sql = "UPDATE information SET Score = Score + 1 WHERE Username = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Username);
			int record = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int getScore(String userName) {
		conn = new Connect().connect();
		String sql = "SELECT Score FROM information WHERE Username = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static void main(String[] args) throws UnknownHostException, SQLException, IOException {
		DataSQLModel dataSQL_Model = new DataSQLModel();
		ArrayList<User> a = dataSQL_Model.getListUser();
		for (User u : a)
			System.out.println(u);
//		System.out.println(dataSQL_Model.Login("b", "c"));
//		dataSQL_Model.increasePoint("HaiZuka");
//		System.out.println(dataSQL_Model.getScore(""));;

	}

}
