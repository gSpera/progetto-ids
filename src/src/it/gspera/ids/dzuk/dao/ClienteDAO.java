package it.gspera.ids.dzuk.dao;

import java.util.List;

import it.gspera.ids.dzuk.entity.Cliente;
import it.gspera.ids.dzuk.utility.Risultato;

public interface ClienteDAO {
	/**
	 * <i>controllaDisponibilitaNomeCliente</i> controlla che <i>nome</i> sia disponibile
	 * per la registrazione del cliente. E' importante notare che questo metodo non riserva
	 * il nome, è quindi importante controllare l'errore di <i>ClienteDAO#registraCliente</i>,
	 * per evitare problemi Time-of-Check Time-of-Use.
	 * 
	 * @param nome da controllare
	 * @return true se il nome è disponibile
	 */
	boolean controllaDisponibilitaNomeCliente(String nome);

	Risultato<Cliente> registraCliente(Cliente c);

	/**
	 * <i>clientiPerIlReport</i> calcola i clienti che hanno acquistato dei prodotti
	 * non ancora riportati in un report.
	 * 
	 * @return Lista dei clienti con prodotti per cui bisogna effettuare il report
	 */
	List<Cliente> clientiPerIlReport();

	List<Cliente> clientiDaFatturare();

}
