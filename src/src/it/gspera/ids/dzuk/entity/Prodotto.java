package it.gspera.ids.dzuk.entity;

/***
 * Prodotto contiene tutte le informazioni di un prodotto
 */
public class Prodotto {
	private int codice;
	private CategoriaProdotto categoria;
	private String descrizione;
	private float prezzoAlKg; // Per i prezzi non sarebbe l'ideale usare i float
	private float pesoInKg; // Non è necessario creare un tipo specifico per il peso, anche se sarebbe una buona idea
	
	private boolean venduto; // True se è stato venduto
	private boolean riportato; // True se è in un report
	private boolean fatturato; // True se è stato fatturato
	
	public Prodotto(int codice, CategoriaProdotto categoria, String descrizione, float prezzoAlKg, float pesoInKg, boolean venduto, boolean riportato, boolean fatturato) {
		this.codice = codice;
		this.categoria = categoria;
		this.descrizione = descrizione;
		this.prezzoAlKg = prezzoAlKg;
		this.pesoInKg = pesoInKg;
		this.venduto = venduto;
		this.riportato = riportato;
		this.fatturato = fatturato;
	}

	public int getCodice() {
		return codice;
	}

	public CategoriaProdotto getCategoria() {
		return categoria;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public float getPrezzoAlKg() {
		return prezzoAlKg;
	}

	public float getPesoInKg() {
		return pesoInKg;
	}
}
