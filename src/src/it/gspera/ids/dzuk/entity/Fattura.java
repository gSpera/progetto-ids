package it.gspera.ids.dzuk.entity;

import java.util.List;

/**
 * <i>Fattura</i> rappresenta una fattura già generata dal software,
 * viene usata principalmente per tenere traccia di quali prodotti sono già stati fatturati.
 */
public class Fattura {
	private int codice; // Identificativo unico della fattura
	
	private Cliente cliente;
	private List<Prodotto> prodotti;
	
	public Fattura(Cliente cliente, List<Prodotto> prodotti) {
		this.cliente = cliente;
		this.prodotti = prodotti;
	}
}
