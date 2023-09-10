package it.gspera.ids.dzuk.entity;

public class Impiegato extends Utente{
	// I campi non hanno un setter in quanto non necessario
	private String nome;
	
	private boolean puoRegistrareClienti;
	private boolean puoRegistrareImpiegati;
	
	
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
