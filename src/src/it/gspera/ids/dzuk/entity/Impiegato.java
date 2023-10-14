package it.gspera.ids.dzuk.entity;

public class Impiegato extends Utente{
	private String nome;
	
	public Impiegato(String username, String password, String nome) {
		this.username = username;
		this.password = password;
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
