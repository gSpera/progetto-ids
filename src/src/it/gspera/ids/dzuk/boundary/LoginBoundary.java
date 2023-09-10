package it.gspera.ids.dzuk.boundary;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;

import it.gspera.ids.dzuk.controller.MainController;
import it.gspera.ids.dzuk.entity.Impiegato;
import it.gspera.ids.dzuk.entity.Prodotto;
import it.gspera.ids.dzuk.entity.Utente;
import it.gspera.ids.dzuk.utility.Input;

public class LoginBoundary {
	private Input in;
	private PrintStream out;
	private Logger log;
	
	public LoginBoundary(Scanner in, PrintStream out, Logger log) {
		this.in = new Input(in, out);
		this.out = out;
		this.log = log;
	}
	
	public void main() {
		this.log.info("LoginManager: main");
		this.out.println("Inserire le credenziali");
		this.out.println("Per visualizzare l'inventario digitare inventario o dare invio a vuoto");
		while(true) {
			String username = this.in.nextLine("Username:");
			if (username.length() == 0 || username.equalsIgnoreCase("inventario")) {
				mostraInventario();
				continue;
			}
			String password = this.in.nextLine("Password:", p -> p.length() > 0, "Inserire una password");
			
			this.log.info("LoginManager: username=" + username);
			Optional<Utente> utente = MainController.the().effettuaLogin(username, password);
			
			if (utente.isEmpty()) {
				out.println("Impossibile loggare, controlla che le credenziali siano corrette");
				continue;
			}
			
			if (utente.get() instanceof Impiegato) {
				new BuondaryImpiegato((Impiegato) utente.get(), in, out, log).Main();
			} else {
				out.println("Utente non implementato");
			}
		}
	}
	
	private void mostraInventario() {
		this.log.info("Loginmanager: mostraInventario");
		List<Prodotto> prodotti = MainController.the().prodottiDisponibili();
		if (prodotti.size() == 0) {
			this.out.println("Non sono disponibili prodotti");
			return;
		}
		
		this.out.println("Inventario: Sono disponibili "+prodotti.size() + " prodotti");
		for (int i=0;i<prodotti.size(); i++) {
			if (i % 24 == 0) {
				this.in.nextLine("Premere invio per continuare");
			}
			Prodotto prodotto = prodotti.get(i);
			
			this.out.println(prodotto.toString());
		}
	}
}
