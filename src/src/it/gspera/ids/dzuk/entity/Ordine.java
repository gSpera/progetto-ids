package it.gspera.ids.dzuk.entity;

import java.util.ArrayList;
import java.util.List;

public class Ordine {
	private int codice;
	private List<Prodotto> prodotti;
	private Fattorino fattorino;
	
	public Ordine(int codice, Fattorino fattorino) {
		this.codice = codice;
		this.fattorino = fattorino;
		this.prodotti = new ArrayList<Prodotto>();
	}
	
	public void aggiungiProdotto(Prodotto p) {
		this.prodotti.add(p);
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public Fattorino getFattorino() {
		return fattorino;
	}

	public void setFattorino(Fattorino fattorino) {
		this.fattorino = fattorino;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}
}
