package org.jbdd.pwet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
	
	//Method to create table User
	public int createTable(Connection conn) {
		int n = -1;
		Statement stm = null;
		
		try {
			String sql = "create table if not exists User ("
					+ "username varchar(30) not null,"
					+ "password varchar(30) not null,"
					+ "primary key(username));";
			stm = conn.createStatement();
			n = stm.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {stm.close();}catch (Exception ignore) {}
		}
		
		return n;
	}
	
	//Method to add user
	public int create(Connection conn, User user) {
		int n = -1;
		PreparedStatement pstm = null;
		
		try {
			String sql = "insert into User values(?,?);";
			pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setString(i++, user.getUsername());
			pstm.setString(i++, user.getPassword());
			n = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}
		
		return n;
	}
	
	//Method to delete user
	public int delete(Connection conn, String username) {
		int n = -1;
		PreparedStatement pstm = null;
		
		try {
			String sql = "delete from User where username=?;";
			pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setString(i,username);
			n = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}
		
		return n;
	}
	
	//Method to read 1 user
	public User read(Connection conn, String username) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		User user = null;
		
		try {
			String sql = "select username, password from User where username = ?;";
			pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setString(i++, username);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				user = new User(rset.getString(1), rset.getString(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}
		
		return user;
	}
	
	//Method to read many users
	List<User> readAll(Connection conn) {
		Statement stm = null;
		ResultSet rset = null;
		List<User> lstUser = new ArrayList<User>();
		
		try {
			String sql = "select username, password from User;";
			stm = conn.createStatement();
			rset = stm.executeQuery(sql);
			
			while(rset.next()) {
				lstUser.add(new User(rset.getString(1),rset.getString(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { stm.close(); } catch (Exception ignore) {}
		}
		
		return lstUser;
	}
	
	//Method to update password user
	int update(Connection conn, String username, User user) {
		int n = -1;
		PreparedStatement pstm = null;
		
		try {
			String sql = "update User set username=?, password=? where username=?;";
			pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setString(i++, user.getUsername());
			pstm.setString(i++, user.getPassword());
			pstm.setString(i++, username);
			n = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}
		
		return n;
	}
}
