package Model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.client;



public class DataSQL_Model {
	Connect cn = new Connect();
	
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement ps;
	
	public DataSQL_Model() {
		conn = new Connect().connect();
	}
	public boolean Login(String Username, String Password) throws SQLException, UnknownHostException, IOException {
		rs=cn.listAll("Select * from account where Username = '"+Username+"' and Password = '"+Password+"'");
		if(rs.next()) {
			return true;
		}else {
//			JOptionPane.showMessageDialog(null, "Username or Password incorrect");
			return false;
		}
	}
	
	public boolean Insert(String Username, String Password, String FullName, int Age) {
		String sql1 = "INSERT INTO account VALUES (?,?)";
		try {
			ps = conn.prepareStatement(sql1);
			ps.setString(1,Username);
			ps.setString(2,Password);			
			int record = ps.executeUpdate();
			if(record == 0)
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
		String sql2 = "INSERT INTO information (Username, FullName, Age, Score) VALUES (?,?,?,0)";
		try {
			ps = conn.prepareStatement(sql2);
			ps.setString(1,Username);
			ps.setString(2,FullName);	
			ps.setInt(3,Age);
			
			int record = ps.executeUpdate();
			return record > 0;
		}catch (Exception e1){
			e1.printStackTrace();
			return false;
		}
	}
	public ArrayList<User> getListUser(){
		ArrayList<User> ListUser = new ArrayList<User>();
		
		String sql = "Select * from information order by Score DESC";
		rs = cn.listAll(sql);
		try {
			while(rs.next()) {
				User user = new User();
				user.setUsername(rs.getString(1));
				user.setFullname(rs.getString(2));
				user.setAge(rs.getInt(3));
				user.setScore(rs.getInt(4));
				ListUser.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ListUser;
	}
	
	public void increasePoint(String Username) {
		String sql = "UPDATE information SET Score = Score + 1 WHERE Username = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Username);			
			int record = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	public int getScore(String userName) {
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
		}
		return 0;
	}
	
	public static void main(String[] args) throws UnknownHostException, SQLException, IOException {
		DataSQL_Model dataSQL_Model = new DataSQL_Model();
		ArrayList<User> a = dataSQL_Model.getListUser();
		for (User u : a)
			System.out.println(u);
//		System.out.println(dataSQL_Model.Login("b", "c"));
//		dataSQL_Model.increasePoint("HaiZuka");
//		System.out.println(dataSQL_Model.getScore(""));;
		
	}

}
