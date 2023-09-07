package it.gspera.ids.dzuk.entity;

public class Impiegato {
	// I campi non hanno un setter in quanto non necessario
	private String nome;
	
	private String username; // Login
	private String password;
	
	private boolean puoRegistrareClienti;
	private boolean puoRegistrareImpiegati;
	
	
	public boolean isPuoRegistrareClienti() {
		return puoRegistrareClienti;
	}
	public void setPuoRegistrareClienti(boolean puoRegistrareClienti) {
		this.puoRegistrareClienti = puoRegistrareClienti;
	}
	public boolean isPuoRegistrareImpiegati() {
		return puoRegistrareImpiegati;
	}
	public void setPuoRegistrareImpiegati(boolean puoRegistrareImpiegati) {
		this.puoRegistrareImpiegati = puoRegistrareImpiegati;
	}
}
