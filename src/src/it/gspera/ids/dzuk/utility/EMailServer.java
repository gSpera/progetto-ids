package it.gspera.ids.dzuk.utility;

import it.gspera.ids.dzuk.entity.Fattura;

public interface EMailServer {
	public Risultato<None> inviaFattura(Fattura f);
}
