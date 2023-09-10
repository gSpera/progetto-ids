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
import it.gspera.ids.dzuk.entity.Fattorino;
import it.gspera.ids.dzuk.entity.Fattura;
import it.gspera.ids.dzuk.entity.Impiegato;
import it.gspera.ids.dzuk.entity.Prodotto;
import it.gspera.ids.dzuk.entity.Utente;
import it.gspera.ids.dzuk.utility.ClienteBuilderException;
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
	
	/**
	 * <i>getConnection</i> permette di ottenere la connessione, per poter eseguire query personalizzate.
	 * Non andrebbe usato normalmente
	 * @return
	 */
	public Connection getConnection() {
		return this.conn;
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
			Utente.Tipo tipo = Utente.Tipo.daInt(rs.getInt("tipo"));
			switch(tipo) {
			case Cliente:
				return clienteDaUsername(username).bind(c -> (Utente) c);
			case Impiegato:
				return impiegatoDaUsername(username).bind(i -> (Utente) i);
			case Fattorino:
				return fattorinoDaUsername(username).bind(f -> (Fattorino) f);
			}
			return new Risultato<Utente>("Tipo utente non valido");
		} catch (SQLException e) {
			log.error("Impossibile eseguire le query: "+ e.toString());
			e.printStackTrace();
			return new Risultato<Utente>("Errore durante la query");
		}
		
	}

	private Risultato<Fattorino> fattorinoDaUsername(String username) {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM fattorino WHERE username=?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return new Risultato<Fattorino>("Fattorino non trovato");
			}
			String password = rs.getString("password");
			return new Risultato<Fattorino>(new Fattorino(username, password));
		}catch(SQLException e) {
			log.error("Impossibile eseguire le query: "+ e.toString());
			e.printStackTrace();
			return new Risultato<Fattorino>("Errore durante la query");
		}
	}

	private Risultato<Impiegato> impiegatoDaUsername(String username) {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM impiegato WHERE username=?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return new Risultato<Impiegato>("Impiegato non trovato");
			}
			String password = rs.getString("password");
			boolean puoRegistrareClienti = rs.getBoolean("puoRegistrareClienti");
			boolean puoRegistrareImpiegati = rs.getBoolean("puoRegistrareImpiegati");
			return new Risultato<Impiegato>(new Impiegato(username, password, puoRegistrareClienti, puoRegistrareImpiegati));
		}catch(SQLException e) {
			log.error("Impossibile eseguire le query: "+ e.toString());
			e.printStackTrace();
			return new Risultato<Impiegato>("Errore durante la query");
		}
	}

	private Risultato<Cliente> clienteDaUsername(String username) {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cliente WHERE username=?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return new Risultato<Cliente>("Cliente non trovato");
			}
			String nome = rs.getString("username");
			Cliente.Tipo tipoCliente = Cliente.Tipo.daInt(rs.getInt("tipo"));
			String password = rs.getString("password");
			String indirizzo = rs.getString("indirizzo");
			String email = rs.getString("email");
			String codice_fatturazione = rs.getString("codice_univoco_fatturazione");
			
			Cliente cliente;
			try {
				cliente = new Cliente.Builder()
						.conTipo(tipoCliente)
						.conNome(nome)
						.conUsername(username)
						.conPassword(password)
						.conEmail(email)
						.conIndirizzo(indirizzo)
						.conCodiceFatturazione(codice_fatturazione)
						.costruisci();
			} catch (ClienteBuilderException e) {
				System.err.println("PANIC: SqlDaoImpl#clientedaUsername errore durante la creazione del cliente: " + e.toString());
				e.printStackTrace();
				System.exit(1);
				return null;
			}
			
			return new Risultato<Cliente>(cliente);
		}catch(SQLException e) {
			log.error("Impossibile eseguire le query: "+ e.toString());
			e.printStackTrace();
			return new Risultato<Cliente>("Errore durante la query");
		}
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
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO prodotto VALUES (?, ?, ?, ?);");
			stmt.setString(1, categoria.toString());
			stmt.setString(2, descrizione);
			stmt.setFloat(3, prezzoAlKg);
			stmt.setFloat(4, pesoInKg);
			
			boolean ok = stmt.execute();
			if (!ok) {
				return new Risultato<Prodotto>("Errore durante la query");
			}
			return new Risultato<Prodotto>(new Prodotto(-1, categoria, descrizione, pesoInKg, pesoInKg, descrizione, false, false));
		} catch (SQLException e) {
			log.error("Impossibile eseguire le query: "+ e.toString());
			e.printStackTrace();
			return new Risultato<Prodotto>("Errore durante la query");
		}
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

	@Override
	public Risultato<Fattura> checkEStatoFatturato(Prodotto p) {
		// TODO Auto-generated method stub
		return null;
	}

}
