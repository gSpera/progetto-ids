package it.gspera.ids.dzuk.utility;

import it.gspera.ids.dzuk.entity.Fattura;

/**
 * <i>FattureManager si occupa dell'invio delle fatture</i>
 */
public interface FattureManager {
	public Risultato<None> inviaFattura(Fattura f);
}
