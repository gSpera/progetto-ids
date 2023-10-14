package it.gspera.ids.dzuk.utility;

import it.gspera.ids.dzuk.entity.Fattura;

public class NoOpEmailServer implements EMailServer {

	@Override
	public Risultato<None> inviaFattura(Fattura f) {
		System.err.println("No Op Email Server");
		return null;
	}

}
