package models;

import java.io.Serializable;

public class User implements Serializable{
	String Username;
	String Fullname;
	int Age;
	int Score;
	public User() {
		
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getFullname() {
		return Fullname;
	}
	public void setFullname(String fullname) {
		Fullname = fullname;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	@Override
	public String toString() {
		return "Username=" + Username + ", Fullname=" + Fullname + ", Age=" + Age + ", Score=" + Score + " ";
	}
	
	
}
