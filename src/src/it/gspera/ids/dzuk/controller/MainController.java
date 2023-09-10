package it.gspera.ids.dzuk.controller;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.gspera.ids.dzuk.dao.ClienteDAO;
import it.gspera.ids.dzuk.dao.ProdottoDAO;
import it.gspera.ids.dzuk.dao.UtenteDAO;
import it.gspera.ids.dzuk.entity.CategoriaProdotto;
import it.gspera.ids.dzuk.entity.Cliente;
import it.gspera.ids.dzuk.entity.Fattura;
import it.gspera.ids.dzuk.entity.Prodotto;
import it.gspera.ids.dzuk.entity.Utente;
import it.gspera.ids.dzuk.utility.ClienteBuilderException;
import it.gspera.ids.dzuk.utility.FattureManager;
import it.gspera.ids.dzuk.utility.MainControllerBuilderException;
import it.gspera.ids.dzuk.utility.NoOpFattureManagerImpl;
import it.gspera.ids.dzuk.utility.MainControllerBuilderException.Tipo;
import it.gspera.ids.dzuk.utility.Risultato;

/**
 * MainController è il controller principale,
 * è una singleton, è accessibile grazie a <i>MainController.the</i>,
 * non è quindi necessario passarlo come dipendenza.
 * 
 * MainController va costruito prima di poterlo usare, 
 * per costruire un MainController si guardi MainController.Builder.
 * 
 * @see it.gspera.ids.dzuk.controller.MainController.the
 * @see it.gspera.ids.dzuk.controller.MainController.Builder
 * @author Giovanni Spera
 */
public class MainController {
	// Builder e Singleton
	/**
	 * <i>MainController.Builder</i> si occupa della costruzione del MainController.
	 * E' necessario costruire il MainController tramite <i>Builder#costruisciESostituisci</i>
	 * prima di poter utilizzare il MainController.
	 * 
	 * Un esempio un po' più documentato è Cliente.Builder
	 */
	public static class Builder {
		private MainController controller;
		
		public Builder() {
			this.nuovo();
		}
		
		public void nuovo() {
			this.controller = new MainController();
		}
		
		/**
		 * conLogger imposta il logger utilizzato dal <i>MainController</i>,
		 * se questo metodo non viene usato verrà usato il logger di default.
		 */
		public Builder conLogger(Logger log) {
			controller.log = log;
			return this;
		}
		
		public Builder conProdottoDAO(ProdottoDAO p) {
			controller.prodottoDao = p;
			return this;
		}
		public Builder conUtenteDAO(UtenteDAO u) {
			controller.utenteDao = u;
			return this;
		}
		public Builder conClienteDAO(ClienteDAO c) {
			controller.clienteDao = c;
			return this;
		}
		/**
		 * conFattureManager importa il manager per le fatture,
		 * se non viene usato questo metodo verra usato <i>NoOpFattureManagerImpl</i>.
		 * 
		 * @see it.gspera.ids.dzuk.utility.NoOpFattureManagerImpl
		 */
		public Builder conFattureManager(FattureManager f) {
			controller.fattureManager = f;
			return this;
		}
		
		/**
		 * costruisciESostituisci finalizza la costruzione del <i>MainController</i>
		 * e lo impone come valore del singleton
		 * @throws MainControllerBuilderException 
		 */
		public void costruisciESostituisci() throws MainControllerBuilderException {
			if (controller.log == null) {
				controller.log = LogManager.getRootLogger();
			}
			if (controller.prodottoDao == null) {
				throw new MainControllerBuilderException(Tipo.ProdottoDAONonDefinito);
			}
			if (controller.utenteDao == null) {
				throw new MainControllerBuilderException(Tipo.UtenteDAONonDefinito);
			}
			if (controller.clienteDao == null) {
				throw new MainControllerBuilderException(Tipo.ClienteDAONonDefinito);
			}
			if (controller.fattureManager == null) {
				controller.fattureManager = new NoOpFattureManagerImpl();
			}
			
			MainController.theInstance = controller;
		}
	}
	
	private static MainController theInstance;
	
	private MainController() {}
	
	public static void costruisci(Builder b) throws MainControllerBuilderException {
		theInstance = b.controller;
		b.controller = null;
	}
	
	// La classe inzia qui
	private Logger log;
	private ProdottoDAO prodottoDao;
	private UtenteDAO utenteDao;
	private ClienteDAO clienteDao;
	private FattureManager fattureManager;
	
	
	public static MainController the() {
		if (theInstance == null) {
			// Questo non dovrebbe mai accadere, vuol dire che non è stato creato il controller
			// Usare un eccezione o ritornare un errore aggiungerebbe verbosità al codice
			// che sarebbe meglio evitare, d'altronde usiamo il pattern Singleton
			// proprio per diminuire la complessità del codice.
			// Magari potremmo mettere in dubbio l'uso del pattern stesso.
			
			// Questa non è di certo la soluzione migliore, anzi...
			// Essenzialmente questo è un panic, personalmente lo trovo accettabile quando
			// si parla di bug che verranno trovati quasi esclusivamente durante lo sviluppo.
			System.err.println("Il MainController non è stato inizializzato");
			System.exit(1);
		}
		
		return theInstance;
	}
	
	
	public List<Prodotto> prodottiDisponibili() {
		return this.prodottoDao.prodottiDisponibili();
	}
	
	/**
	 * Prova ad effettuare un login
	 * @param username dell'utente
	 * @param password dell'utente
	 * @return Un utente se le credenziali sono corrette, altrimenti Empty
	 */
	public Optional<Utente> effettuaLogin(String username, String password) {
		log.info("effettuaLogin: " + username);
		boolean ok = this.utenteDao.provaLogin(username, password);
		if (!ok) {
			return Optional.empty();
		}
		
		Risultato<Utente> u = this.utenteDao.daUsername(username);
		if (!u.haAvutoSuccesso()) {
			this.log.error("Errore durante il login: " + u.getErrore());
			return Optional.empty();
		}
		
		return u.comeOptional();
	}

	public boolean registraProdotto(String categoriaString, String descrizione, float prezzoAlKg, float pesoInKg) {
		log.info("Registrazione nuovo prodotto:" + categoriaString + " " + descrizione + " " + prezzoAlKg + " " + pesoInKg);
		CategoriaProdotto categoria = new CategoriaProdotto(categoriaString);
		Risultato<Prodotto> risultato = this.prodottoDao.registraProdottoEAssegnaCodice(categoria, descrizione, prezzoAlKg, pesoInKg);
		if (!risultato.haAvutoSuccesso()) {
			log.error("Impossibile registrare un nuovo prodotto:" + risultato.getErrore());
			return false;
		}
		
		return true;
	}

	public boolean nomeClienteDisponibile(String nome) {
		return this.clienteDao.controllaDisponibilitaNomeCliente(nome);
	}
	public boolean registraCliente(String nome, String indirizzo, String email, String codice_fatturazione) {
		try {
			boolean nomeDisponibile = this.clienteDao.controllaDisponibilitaNomeCliente(nome);
			if (!nomeDisponibile) {
				return false;
			}
			
			Cliente c = new Cliente.Builder()
					.conNome(nome)
					.conIndirizzo(indirizzo)
					.conEmail(email)
					.conCodiceFatturazione(codice_fatturazione)
					.costruisci();
			
			Risultato<Cliente> risultato = this.clienteDao.registraCliente(c);
			
			return true;
		} catch (ClienteBuilderException e) {
			log.error("Impossibile creare il nuovo cliente:" + e.toString());
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean generaReport() {
		log.info("generaReport");
		List<Cliente> clienti = clienteDao.clientiPerIlReport();
		clienti.sort(new Comparator<Cliente>() {
			@Override
			public int compare(Cliente c1, Cliente c2) {
				return c1.getNome().compareTo(c2.getNome());
			}
		});
		
		float prezzoTotale = 0;
		PrintWriter report;
		String filename = "report-" + new Date().getTime() + ".txt";
		try {
			report = new PrintWriter(filename, "UTF-8");
		} catch (FileNotFoundException e) {
			log.error("Non è possibile generare il report, errore durante l'apertura del file:" + e.toString());
			e.printStackTrace();
			return false;
		} catch (UnsupportedEncodingException e) {
			log.error("Non è possibile generare il report, non è supportata la codice del file:" + e.toString());
			e.printStackTrace();
			return false;
		}
		
		log.info("Inizio report, file:" + filename);
		report.println("Report per DZUK, data: " + Calendar.getInstance().toString());
		report.println();
		
		for(Cliente c : clienti) {
			List<Prodotto> prodotti = prodottoDao.prodottiDaRiportarePerCliente(c);
			report.println("Cliente " + c.getNome());
			
			float totaleCliente = 0;
			for (Prodotto p : prodotti) {
				report.println(" - " + p.getDescrizione() + " " + p.getPrezzoAlKg() * p.getPesoInKg() + "€ (" + p.getPrezzoAlKg() + " €/Kg)");
				totaleCliente += p.getPrezzoAlKg() * p.getPesoInKg();
			}
			report.println("Totale per " + c.getNome() + ": " + totaleCliente + "€");
			report.println();
			prodottoDao.segnaProdottiRiportati(prodotti);
		}
		report.println("Totale: " + prezzoTotale + "€");
		report.close();
		log.info("Report generato");
		return true;
	}

	public boolean generaEInviaFatture() {
		log.info("generaEInviaFatture");
		List<Cliente> clienti = clienteDao.clientiDaFatturare();
		
		for (Cliente c : clienti) {
			log.info("generaEInviaFatture: Cliente " + c.getNome());
			List<Prodotto> prodotti = prodottoDao.prodottiDaFatturarePerCliente(c);
			
			Fattura fattura = new Fattura(c, prodotti);
			fattureManager.inviaFattura(fattura);
		}
		log.info("generaEInviaFatture: Tutte le fatture sono state inviate");
		return true;
	}
}
