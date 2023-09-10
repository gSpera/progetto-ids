package it.gspera.ids.dzuk.entity;

public class Impiegato extends Utente{
	// I campi non hanno un setter in quanto non necessario
	private String nome;
	
	private boolean puoRegistrareClienti;
	private boolean puoRegistrareImpiegati;
	
	
	public Impiegato(String username, String password, boolean puoRegistrareClienti, boolean puoRegistrareImpiegato) {
		this.username = username;
		this.password = password;
		this.puoRegistrareClienti = puoRegistrareClienti;
		this.puoRegistrareImpiegati = puoRegistrareImpiegato;
	}
	
	public String getNome() {
		return nome;
	}
	
	public boolean puoRegistrareClienti() {
		return puoRegistrareClienti;
	}
	public void setPuoRegistrareClienti(boolean puoRegistrareClienti) {
		this.puoRegistrareClienti = puoRegistrareClienti;
	}
	public boolean puoRegistrareImpiegati() {
		return puoRegistrareImpiegati;
	}
	public void setPuoRegistrareImpiegati(boolean puoRegistrareImpiegati) {
		this.puoRegistrareImpiegati = puoRegistrareImpiegati;
	}
}
