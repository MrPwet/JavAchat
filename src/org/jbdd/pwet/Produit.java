package org.jbdd.pwet;

import java.math.BigDecimal;

public class Produit {
	int idProduit;
	String nomProduit;
	BigDecimal prixProduit;
	
	public Produit() {}
	
	public Produit(int idProduit, String nomProduit, BigDecimal prixProduit) {
		this.idProduit = idProduit;
		this.nomProduit = nomProduit;
		this.prixProduit = prixProduit;
	}
	
	public int getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}
	public String getNomProduit() {
		return nomProduit;
	}
	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
	public BigDecimal getPrixProduit() {
		return prixProduit;
	}
	public void setPrixProduit(BigDecimal prixProduit) {
		this.prixProduit = prixProduit;
	}
	
	
}
