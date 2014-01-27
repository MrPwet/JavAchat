package org.jbdd.pwet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProduitManager {
	
	//Method to create table Produit
	public int createTable(Connection conn) {
		int n = -1;
		Statement stm = null;

		try {
			String sql = "create table if not exists Produit ("
					+ "idProduit int not null auto_increment,"
					+ "nomProduit varchar(30) not null,"
					+ "prixProduit float not null,"
					+ "primary key(idProduit));";
			stm = conn.createStatement();
			n = stm.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {stm.close();}catch (Exception ignore) {}
		}

		return n;
	}

	//Method to add Produit
	public int create(Connection conn, Produit produit) {
		int n = -1;
		PreparedStatement pstm = null;

		try {
			String sql = "insert into Produit values(null,?,?);";
			pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setString(i++, produit.getNomProduit());
			pstm.setBigDecimal(i++, produit.getPrixProduit());
			n = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}

		return n;
	}

	//Method to delete user
	public int delete(Connection conn, int idProduit) {
		int n = -1;
		PreparedStatement pstm = null;

		try {
			String sql = "delete from Produit where idProduit=?;";
			pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setInt(i,idProduit);
			n = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}

		return n;
	}

	//Method to read 1 user
	public Produit read(Connection conn, int idProduit) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		Produit produit = null;

		try {
			String sql = "select idProduit, nomProduit, prixProduit from Produit where idProduit = ?;";
			pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setInt(i++, idProduit);
			rset = pstm.executeQuery();

			if(rset.next()) {
				produit = new Produit(rset.getInt(1), rset.getString(2), rset.getBigDecimal(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}

		return produit;
	}

	//Method to read many users
	List<Produit> readAll(Connection conn) {
		Statement stm = null;
		ResultSet rset = null;
		List<Produit> lstProduit = new ArrayList<Produit>();

		try {
			String sql = "select idProduit, nomProduit, prixProduit from Produit;";
			stm = conn.createStatement();
			rset = stm.executeQuery(sql);

			while(rset.next()) {
				lstProduit.add(new Produit(rset.getInt(1),rset.getString(2),rset.getBigDecimal(3)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { stm.close(); } catch (Exception ignore) {}
		}

		return lstProduit;
	}

	//Method to update password user
	int update(Connection conn, int idProduit, Produit produit) {
		int n = -1;
		PreparedStatement pstm = null;

		try {
			String sql = "update Produit set nomProduit=?, prixProduit=? where idProduit=?;";
			pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setString(i++, produit.getNomProduit());
			pstm.setBigDecimal(i++, produit.getPrixProduit());
			pstm.setInt(i++, idProduit);
			n = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}

		return n;
	}

}
