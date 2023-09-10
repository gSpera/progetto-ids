package it.gspera.ids.dzuk.entity;

/***
 * <i>CategoriaProdotto</i> rappresenta la categoria di un prodotto,
 * non è un enum per poter aggiungere facilmente nuove categorie
 * 
 * E' possibile creare nuove <i>CategoriaProdotto</i> liberamente,
 * due <i>CategoriaProdotto</i> con lo stesso nome sono equivalenti tra di loro.
 * Questa scelta aumenta il consumo di memoria[1] ma semplifica l'implementazione,
 * inoltre difficilmente altre classi creeranno nuove <i>CategoriaProdotto</i>
 * 
 * [1]: Il consumo di memoria sarebbe in ogni caso da misurare, l'utilizzo di un Manager
 * richiederebbe di avere una struttura dati dove registrare tutte le <i>CategoriaProdotto</i>
 * che a sua volta occuperebbe memoria. Come sempre non si parla senza benchmark.
 */
public class CategoriaProdotto {
	private String nome;
	
	public CategoriaProdotto(String nome) {
		this.nome = nome;
	}
	
	public boolean equals(CategoriaProdotto other) {
		return this.nome.equals(other.nome);
	}
}
