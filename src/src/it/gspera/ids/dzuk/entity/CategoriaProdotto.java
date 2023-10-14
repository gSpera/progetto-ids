package it.gspera.ids.dzuk.entity;

public class CategoriaProdotto {
	public enum PossibiliCategorie {
		Pesci(1),
		Crostacei(2),
		Molluschi(3),
		;
		
		public final int id;
		private PossibiliCategorie(int id) {
			this.id = id;
		}
	};
	public enum TipoCategoriaProdotto {
		Verace(1),
		Allevamento(2),
		Congelato(3),
		;
		
		public final int id;
		private TipoCategoriaProdotto(int id) {
			this.id = id;
		}
	};
	
	private int codice;
	private String descrizione;
	private PossibiliCategorie categoria;
	private TipoCategoriaProdotto tipo;
	private float prezzoAlKg;
	
	public CategoriaProdotto(int codice, String descrizione, PossibiliCategorie categoria, TipoCategoriaProdotto tipo, float prezzoAlKg) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.tipo = tipo;
		this.prezzoAlKg = prezzoAlKg;
	}
	
	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public PossibiliCategorie getCategoria() {
		return categoria;
	}

	public void setCategoria(PossibiliCategorie categoria) {
		this.categoria = categoria;
	}

	public TipoCategoriaProdotto getTipo() {
		return tipo;
	}

	public void setTipo(TipoCategoriaProdotto tipo) {
		this.tipo = tipo;
	}

	public float getPrezzoAlKg() {
		return prezzoAlKg;
	}

	public void setPrezzoAlKg(float prezzoAlKg) {
		this.prezzoAlKg = prezzoAlKg;
	}
}
