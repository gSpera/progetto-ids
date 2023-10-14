package it.gspera.ids.dzuk.boundary;

import java.io.PrintStream;
import java.util.List;

import org.apache.logging.log4j.Logger;

import it.gspera.ids.dzuk.controller.MainController;
import it.gspera.ids.dzuk.entity.Fattorino;
import it.gspera.ids.dzuk.entity.Ordine;
import it.gspera.ids.dzuk.utility.Input;

public class BoundaryFattorino {
	private Fattorino fattorino;
	private MainController controller;
	
	public void visualizzaSpedizioni() {
		List<Ordine> ordini = this.controller.ordiniPerFattorino(this.fattorino);
		for (Ordine o : ordini) {
			System.out.println("Ordine: " + o);
		}
	}
	
	public void confermaSpedizione(Ordine o) {
		this.controller.consegnaOrdine(o);
	}
	
	public void Main(Input in, PrintStream out, Logger log) {
		out.println("NON IMPLEMENTATO");
	}
	
}
