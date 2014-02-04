package org.jbdd.pwet;

import java.sql.Connection;

public class Test {
	
	public static void main(String[] args) {
		Connection conn = null;
		
		try {
			conn = Singleton.DS.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { conn.close(); } catch (Exception ignore) {}
		}
	}
}

