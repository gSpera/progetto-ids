package it.gspera.ids.dzuk.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import it.gspera.ids.dzuk.entity.Cliente;
import it.gspera.ids.dzuk.entity.Cliente.Tipo;
import it.gspera.ids.dzuk.utility.ClienteBuilderException;
import it.gspera.ids.dzuk.utility.Risultato;

class SqlDaoClienteDAOImplTest {
	SqlDaoImpl rawImpl;
	ClienteDAO dao;
	
	@Before
	public void setup() {
		try {
			rawImpl = new SqlDaoImpl("jdbc:sqlite::memory:", LogManager.getRootLogger());
			dao = rawImpl;
		} catch (Exception e) {
			System.err.println("Impossibile aprire una connessione al database");
			e.printStackTrace();
			System.exit(1);
		}
		Connection conn = rawImpl.getConnection();
		try {
			String sql = Files.readString(FileSystems.getDefault().getPath(".", "init_testing.sql"));
			boolean ok = conn.prepareStatement(sql).execute();
			if (!ok) {
				System.err.println("Impossibile caricare i dati di test");
				System.exit(1);
			}
		} catch(Exception e) {
			System.err.println("Impossibile caricare i dati di test nel database");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@Test
	void testTCD01() {
		boolean ok = dao.controllaDisponibilitaNomeCliente("pescheria_esistente");
		if (ok) {
			fail("TCD01: Il nome risulta disponibile, ci si aspettava fosse occupato");
		}
	}
	
	@Test
	void testTCD02() {
		boolean ok = dao.controllaDisponibilitaNomeCliente("pescheria_NON_esistente");
		if (!ok) {
			fail("TCD02: Il nome NON risulta disponibile, ci si aspettava fosse libero");
		}
	}
	
	@Test
	void testTCD03() {
		Cliente c = null;
		try {
			c = new Cliente.Builder()
					.conNome("pescheria_esistente")
					.conTipo(Tipo.Pescheria)
					.conCodiceFatturazione("ABC123")
					.conIndirizzo("VIA INESISTENTE -1")
					.conUsername("INVALIDO")
					.conPassword("PASSWORD")
					.costruisci();
		} catch (ClienteBuilderException e) {
			fail("TCD03: Impossibile creare il cliente per il test");
			e.printStackTrace();
		}
		Risultato<Cliente> risultato = dao.registraCliente(c);
		if (risultato.haAvutoSuccesso()) {
			fail("TCD03: E' stato creato un cliente già esistente, nuovo cliente:" + risultato.getRisultato().toString());
		}
	}
	
	@Test
	void testTCD04() {
		Cliente c = null;
		try {
			c = new Cliente.Builder()
					.conNome("pescheria_NON_esistente")
					.conTipo(Tipo.Pescheria)
					.conCodiceFatturazione("ABC123")
					.conIndirizzo("VIA INESISTENTE -1")
					.conUsername("INVALIDO")
					.conPassword("PASSWORD")
					.costruisci();
		} catch (ClienteBuilderException e) {
			fail("TCD03: Impossibile creare il cliente per il test");
			e.printStackTrace();
		}
		Risultato<Cliente> risultato = dao.registraCliente(c);
		if (!risultato.haAvutoSuccesso()) {
			fail("TCD03: Non èstato creato un cliente non esistente, errore:" + risultato.getErrore());
		}
	}
}
