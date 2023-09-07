package it.gspera.ids.dzuk.entity;

public class Cliente {
	public enum Tipo {
		Pescheria,
		Ristorante,
	}
	
	private Tipo tipo; // Non ha un setter
	private String nome;
	private String indirizzo;
	private String email;
	
	private String utente;  // Login, non ha un setter
	private String password;
	
	private String codice_univoco_fatturazione; // Fatturazione Elettronica

	public Tipo getTipo() {
		return tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUtente() {
		return utente;
	}
}
