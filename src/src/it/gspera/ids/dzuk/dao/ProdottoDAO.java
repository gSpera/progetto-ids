package it.gspera.ids.dzuk.dao;

import java.util.List;

import it.gspera.ids.dzuk.entity.CategoriaProdotto;
import it.gspera.ids.dzuk.entity.Cliente;
import it.gspera.ids.dzuk.entity.Fattura;
import it.gspera.ids.dzuk.entity.Prodotto;
import it.gspera.ids.dzuk.utility.Risultato;

public interface ProdottoDAO {
	public List<Prodotto> prodottiDisponibili();

	public Risultato<Prodotto> registraProdottoEAssegnaCodice(CategoriaProdotto categoria, String descrizione, float prezzoAlKg,
			float pesoInKg);

	public List<Prodotto> prodottiDaRiportarePerCliente(Cliente c);

	public void segnaProdottiRiportati(List<Prodotto> prodotti);

	public List<Prodotto> prodottiDaFatturarePerCliente(Cliente c);
	
	/**
	 * <i>checkEStatoFatturato</i> controlla se il <i>Prodotto</p> p
	 * è stato fatturato, se il prodotto è stato fatturato viene riportata la <i>Fattura</i>.
	 * 
	 * @see it.gspera.dzuk.entity.Fattura;
	 * @arg p <i>Prodotto</i> da controllare
	 * @return Risultato<Fattura> se il prodotto è stato fatturato o un messaggio di errore
	 */
	public Risultato<Fattura> checkEStatoFatturato(Prodotto p);
}
