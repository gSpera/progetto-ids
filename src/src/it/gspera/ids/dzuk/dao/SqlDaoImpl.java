package it.gspera.ids.dzuk.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import it.gspera.ids.dzuk.entity.CategoriaProdotto;
import it.gspera.ids.dzuk.entity.Cliente;
import it.gspera.ids.dzuk.entity.Prodotto;
import it.gspera.ids.dzuk.entity.Utente;
import it.gspera.ids.dzuk.utility.Risultato;

/**
 * <i>SqlDaoImpl</i> implementa diversi DAO in una sola classe,
 * questo permette di avere tutta l'implementazione in un solo file,
 * in questo modo la gestione delle varie connessioni al database è centralizzata in una sola classe,
 * questo benchè possibile anche dividendo l'implementazione in un Manager e varie sotto classi, una per DAO,
 * non risulta ottima per progetti piccoli.
 */
public class SqlDaoImpl implements ClienteDAO, ProdottoDAO, UtenteDAO {
	private Connection conn;
	private Logger log;
	
	public SqlDaoImpl(String connessione, Logger log) throws SQLException, NoSuchAlgorithmException {
		this.conn = DriverManager.getConnection(connessione);
		this.log = log;
	}
	
	@Override
	public boolean provaLogin(String username, String password) {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM utente WHERE username=? AND password=?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true; // C'è almeno una riga
			} else {
				return false; // Non ci sono righe
			}
		} catch (SQLException e) {
			log.error("Impossibile eseguire le query: "+ e.toString());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Risultato<Utente> daUsername(String username) {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM utente WHERE username=?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return new Risultato<Utente>("Utente non trovato");
			}
			// TODO: Implementare
		} catch (SQLException e) {
			log.error("Impossibile eseguire le query: "+ e.toString());
			e.printStackTrace();
			return new Risultato<Utente>("Errore durante la query");
		}
		
		return null;
	}

	@Override
	public List<Prodotto> prodottiDisponibili() {
		List<Prodotto> risultato = new ArrayList<Prodotto>();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM prodotto WHERE venduto=false");
			ResultSet rs = stmt.executeQuery();
			// TODO: Implementare
		} catch (SQLException e) {
			log.error("Impossibile eseguire le query: "+ e.toString());
			e.printStackTrace();
			return null;
		}
		
		return risultato;
	}

	@Override
	public Risultato<Prodotto> registraProdottoEAssegnaCodice(CategoriaProdotto categoria, String descrizione,
			float prezzoAlKg, float pesoInKg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Prodotto> prodottiDaRiportarePerCliente(Cliente c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void segnaProdottiRiportati(List<Prodotto> prodotti) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Prodotto> prodottiDaFatturarePerCliente(Cliente c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean controllaDisponibilitaNomeCliente(String nome) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Risultato<Cliente> registraCliente(Cliente c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> clientiPerIlReport() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> clientiDaFatturare() {
		// TODO Auto-generated method stub
		return null;
	}

}
