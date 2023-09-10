package it.gspera.ids.dzuk.dao;

import java.util.List;

import it.gspera.ids.dzuk.entity.CategoriaProdotto;
import it.gspera.ids.dzuk.entity.Cliente;
import it.gspera.ids.dzuk.entity.Prodotto;
import it.gspera.ids.dzuk.utility.Risultato;

public interface ProdottoDAO {
	public List<Prodotto> prodottiDisponibili();

	public Risultato<Prodotto> registraProdottoEAssegnaCodice(CategoriaProdotto categoria, String descrizione, float prezzoAlKg,
			float pesoInKg);

	public List<Prodotto> prodottiDaRiportarePerCliente(Cliente c);

	public void segnaProdottiRiportati(List<Prodotto> prodotti);

	public List<Prodotto> prodottiDaFatturarePerCliente(Cliente c);
}
