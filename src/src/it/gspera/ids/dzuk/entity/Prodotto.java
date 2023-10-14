package it.gspera.ids.dzuk.entity;

/***
 * Prodotto contiene tutte le informazioni di un prodotto.
 * <p>
 * Poichè non è effetivamente usato, si è scelto per fini pratici e prestazionali
 * di non includere un riferimento alla <i>Fattura</i> relativa al <i>Prodotto</i>,
 * nel caso questo attributo sia necessario si può passare per il <i>ProdottoDAO</i>,
 * questo indica, anche, al programmatore, che ottenere la fattura potrebbe essere
 * un operazione costosa, a titolo di esempio una connessione al database.
 * </p>
 * 
 * @see it.gspera.ids.dzuk.entity.Fattura
 * @see it.gspera.ids.dzuk.entity.ProdottoDAO
 */
public class Prodotto {
	public enum Stato {
		Creato(1),
		Venduto(2),
		InPreparazione(3),
		Preparato(4),
		InSpedizione(5),
		Reportato(6),
		Fatturato(7),
		ReportatoEFatturato(8),
		;
		
		public final int id;
		private Stato(int id) {
			this.id = id;
		}
	};
	
	private int codice;
	private String descrizione;
	private CategoriaProdotto categoria;
	private float pesoInKg; // Non è necessario creare un tipo specifico per il peso, anche se sarebbe una buona idea
	private String pescatore; // Chi ha venduto il Prodotto alla cooperativa
	private Stato stato;
	
	public Prodotto(int codice, CategoriaProdotto categoria, String descrizione, float pesoInKg, String pescatore) {
		this.codice = codice;
		this.categoria = categoria;
		this.descrizione = descrizione;
		this.pesoInKg = pesoInKg;
		this.pescatore = pescatore;
		this.stato = Stato.Creato;
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

	public float getPesoInKg() {
		return pesoInKg;
	}
	
	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

}
