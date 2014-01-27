package org.jbdd.pwet;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

public class Test {
	public static void testProduit() {
		Connection conn = null;
		ProduitManager pm = null;
		Produit p1 = new Produit();
		Produit p2 = new Produit();
		
		try {
			conn = Singleton.DS.getConnection();
			p1.setIdProduit(2);
			p1.setNomProduit("Biscuit");
			p1.setPrixProduit(new BigDecimal(5.21));
			
			
			pm = new ProduitManager();
			pm.createTable(conn);
			
			pm.create(conn, p1);
			p2 = pm.read(conn, 2);
			assert(p1.getNomProduit() == p2.getNomProduit());
			
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch( Exception ignore) {}
		}
		
	}
	
	public static void main(String[] args) {
		testProduit();
		Connection conn = null;
		UserManager userManager = null;
		User user1 = null;
		User user2 = null;
		User user3 = null;
		List<User> lst = null;
		Iterator<User> it = null;
		
		try {
			conn = Singleton.DS.getConnection();
			
			userManager = new UserManager();
			userManager.createTable(conn);
			
			user1 = new User("MrPwet", "randompw");
			userManager.create(conn,user1);
			
			user2 = new User("Truc", "Blabla");
			userManager.create(conn, user2);
			
			userManager.delete(conn, "MrPwet");
			
			user3 = userManager.read(conn, "Truc");
			System.out.println(user3);
			
			for(int i = 0 ; i < 10 ; i++) {
				userManager.create(conn, new User("user"+i,"mdp"));
			}
			
			lst = userManager.readAll(conn);
			it = lst.iterator();
			while(it.hasNext()) {
				System.out.println(it.next());
			}
			
			userManager.update(conn, "user5", new User("blblabla","passlollllol"));
			
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { conn.close(); } catch (Exception ignore) {}
		}
	}
}

