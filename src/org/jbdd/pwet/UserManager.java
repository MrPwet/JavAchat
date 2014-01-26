package org.jbdd.pwet;

import java.sql.Connection;
import java.sql.Statement;

public class UserManager {
	
	//Method to create table User
	public int createTable(Connection conn) {
		int n = -1;
		Statement stm = null;
		
		try {
			String sql = "create table if not exists User ("
					+ "id_user int not null auto_increment,"
					+ "username varchar(30) not null,"
					+ "password varchar(30) not null,"
					+ "primary key(id_user));";
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
	
}
