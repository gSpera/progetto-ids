package it.gspera.ids.dzuk.utility;

import it.gspera.ids.dzuk.entity.Fattura;

public class NoOpFattureManagerImpl implements FattureManager {

	@Override
	public Risultato<None> inviaFattura(Fattura f) {
		return new Risultato<None>(new None());
	}

}
