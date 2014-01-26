package org.jbdd.pwet;

import java.sql.Connection;

public class Test {
	
	public static void main(String[] args) {
		Connection conn = null;
		UserManager userManager = null;
	
		try {
			conn = Singleton.DS.getConnection();
			userManager = new UserManager();
			
			userManager.createTable(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { conn.close(); } catch (Exception ignore) {}
		}
	}
}

