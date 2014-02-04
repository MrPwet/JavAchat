package org.jbdd.pwet;

import java.math.BigDecimal;

public class Article {
	int idArticle;
	String nomArticle;
	BigDecimal prixArticle;
	int disponibiliteArticle;
	int idCategorie;
	
	public Article() {}
	
	public Article(int idArticle, String nomArticle, BigDecimal prixArticle, int disponibiliteArticle, int idCategorie) {
		this.idArticle = idArticle;
		this.nomArticle = nomArticle;
		this.prixArticle = prixArticle;
		this.idCategorie = idCategorie;
	}
	
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomProduit(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public BigDecimal getPrixArticle() {
		return prixArticle;
	}
	public void setPrixProduit(BigDecimal prixArticle) {
		this.prixArticle = prixArticle;
	}

	public int getDisponibiliteArticle() {
		return disponibiliteArticle;
	}

	public void setDisponibiliteArticle(int disponibiliteArticle) {
		this.disponibiliteArticle = disponibiliteArticle;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	
	
}
