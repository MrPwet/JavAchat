package org.jbdd.pwet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticleManager {
	
	//Method to create table Article
	public int createTable(Connection conn) {
		int n = -1;
		Statement stm = null;

		try {
			String sql = "create table if not exists Article ("
					+ "idArticle int not null auto_increment,"
					+ "nomArticle varchar(30) not null,"
					+ "prixArticle float not null,"
					+ "disponibiliteArticle int not null,"
					+ "idCategorie int not null,"
					+ "foreign key (idCategorie) references Categorie(idCategorie),"
					+ "primary key(idArticle));";
			stm = conn.createStatement();
			n = stm.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {stm.close();}catch (Exception ignore) {}
		}

		return n;
	}

	//Method to add Article
	public int create(Connection conn, Article article) {
		int n = -1;
		PreparedStatement pstm = null;

		try {
			String sql = "insert into Article values(null,?,?,?,?);";
			pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setString(i++, article.getNomArticle());
			pstm.setBigDecimal(i++, article.getPrixArticle());
			pstm.setInt(i++, article.getDisponibiliteArticle());
			pstm.setInt(i++, article.getIdCategorie());
			n = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}

		return n;
	}

	//Method to delete Article
	public int delete(Connection conn, int idArticle) {
		int n = -1;
		PreparedStatement pstm = null;

		try {
			String sql = "delete from Article where idArticle=?;";
			pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setInt(i++, idArticle);
			n = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}

		return n;
	}

	//Method to read 1 Article
	public Article read(Connection conn, int idArticle) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		Article article = null;

		try {
			String sql = "select idArticle, nomArticle, prixArticle, disponibiliteArticle, idCategorie from Produit where idArticle = ?;";
			pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setInt(i++, idArticle);
			rset = pstm.executeQuery();

			if(rset.next()) {
				article = new Article(rset.getInt(1), rset.getString(2), rset.getBigDecimal(3), rset.getInt(4), rset.getInt(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}

		return article;
	}

	//Method to read many users
	List<Article> readAll(Connection conn) {
		Statement stm = null;
		ResultSet rset = null;
		List<Article> lstArticle = new ArrayList<Article>();

		try {
			String sql = "select idArticle, nomArticle, prixArticle, disponibiliteArticle from Article;";
			stm = conn.createStatement();
			rset = stm.executeQuery(sql);

			while(rset.next()) {
				lstArticle.add(new Article(rset.getInt(1),rset.getString(2),rset.getBigDecimal(3),rset.getInt(4),rset.getInt(5)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { stm.close(); } catch (Exception ignore) {}
		}

		return lstArticle;
	}

	//Method to update password user
	int update(Connection conn, int idArticle, Article article) {
		int n = -1;
		PreparedStatement pstm = null;

		try {
			String sql = "update Article set nomArticle=?, prixArticle=?, disponibiliteArticle=?, idCategorie=? where idArticle=?;";
			pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setString(i++, article.getNomArticle());
			pstm.setBigDecimal(i++, article.getPrixArticle());
			pstm.setInt(i++, article.getDisponibiliteArticle());
			pstm.setInt(i++, article.getIdCategorie());
			pstm.setInt(i++, idArticle);
			n = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstm.close(); } catch (Exception ignore) {}
		}

		return n;
	}

}
