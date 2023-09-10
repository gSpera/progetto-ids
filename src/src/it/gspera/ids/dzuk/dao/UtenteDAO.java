package it.gspera.ids.dzuk.dao;

import it.gspera.ids.dzuk.entity.Utente;
import it.gspera.ids.dzuk.utility.Risultato;

public interface UtenteDAO {
	/**
	 * Controlla che username e password corrispondano
	 * @param username dell'utente
	 * @param password dell'utente
	 * @return true se il login ha successo
	 */
	public boolean provaLogin(String username, String password);

	/**
	 * Ottiene un utente tramite l'username
	 * @param username dell'utente
	 * @return
	 */
	public Risultato<Utente> daUsername(String username);
}
