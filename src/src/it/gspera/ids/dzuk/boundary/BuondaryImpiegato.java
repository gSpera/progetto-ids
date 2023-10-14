package it.gspera.ids.dzuk.boundary;

import java.io.PrintStream;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;

import it.gspera.ids.dzuk.controller.MainController;
import it.gspera.ids.dzuk.entity.Impiegato;
import it.gspera.ids.dzuk.utility.Input;

public class BuondaryImpiegato {
	private Impiegato impiegato;
	private Input in;
	private PrintStream out;
	private Logger log;
	
	public BuondaryImpiegato(Impiegato i, Input in, PrintStream out, Logger log) {
		this.impiegato = i;
		this.in = in;
		this.out = out;
		this.log = log;
	}
	
	public void Main() {
		this.log.info("BoundaryImpiegato: Main");
		
		while(true) {
			out.println("Benvenuto");
			out.println("1) Registra prodotto");
			out.println("2) Prepara ordine");
			out.println("0) Esci");
			
			int scelta = in.nextInt("Cosa vuoi fare??", v -> v >= 0 && v <= 4, "La selezione non è valida");
			switch(scelta) {
			case 1:
				log.info("BoundaryImpiegato: Registra prodotto");
				registraProdotto();
				break;
			case 2:
				log.info("BoundaryImpiegato: Prepara ordine");
				preparaOrdine();
				break;
			case 0:
				out.println("BoundaryImpiegato: Esci");
				return;
			}
		}
	}


	private void preparaOrdine() {
		log.info("BoundaryImpiegato: preparaOrdine");
	}

	private void registraProdotto() {
		log.info("BoundaryImpiegato: registraProdotto");
		
		while(true) {
			out.println("Registrazione nuovo prodotto");
			String categoria = in.nextLine("Inserire la categoria:", s -> s.length() > 0, "La categoria non può essere vuota");
			String descrizione = in.nextLine("Inserire la descrizione:");
			float prezzoAlKg = in.nextFloat("Inserire il prezzo al kg:", p -> p>0, "Il prezzo non può essere negativo o nullo");
			float pesoInKg = in.nextFloat("Inserire il peso in kg:", p -> p>0, "Il peso non può essere negativo o nullo");
			boolean ok = MainController.the().registraProdotto(categoria, descrizione, prezzoAlKg, pesoInKg);
			if (!ok) {
				out.println("ERRORE durante la registrazione del prodotto");
				out.println("E' possibile riprovare a registrare il prodotto");
			}
		}
	}
}
