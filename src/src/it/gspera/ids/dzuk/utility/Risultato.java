package it.gspera.ids.dzuk.utility;

import java.util.Optional;

/**
 * <i>Risultato<i> è un monoide presente in molti linguaggi, spesso come Result, Either, o altri nomi.
 * E' simile a Optional, è possibile ci sia un valore, ma, se non è presente il valore, è invece presente
 * un messaggio di errore.
 * <br>
 * 
 * In realtà non implementiamo tutte le funzionalità di un monoide, implementiamo solo le funzionalità che ci servono.
 * <i>Risultato</i> è implementato per permettere a funzioni di inviare un messaggio di errore senza implementare un tipo particolare,
 * ed è implementato anche perchè personalmente non mi piace il try/catch.
 * @param <T> Il risultato, opzionalmente presente.
 */
public class Risultato<T> {
	private T t;
	private String err;
	
	public Risultato(T t) {
		this.t = t;
	}
	public Risultato(String error) {
		this.err = error;
	}
	
	/**
	 * haAvutoSuccesso controlla che l'operazione che ha creato <i>Risultato</i>
	 * abbia avuto successo.
	 * @return true se è disponibile il risultato
	 */
	public boolean haAvutoSuccesso() {
		return t != null;
	}
	
	public T getRisultato() {
		// Buona norma vorrebbe che controllassimo t non fosse null,
		// in realtà questo non ha senso, perchè a quel punto dovremmo lanciare un Exception
		// o, magari, ritornare un Optional,
		// poichè Risultato già si occupa di "wrappare" l'errore.
		// D'altronde si può anche controllare che getRisultato sia null e agire di conseguenza
		return t;
	}
	
	public String getErrore() {
		// Si veda il commento di getRisultato
		return err;
	}
	
	/**
	 * comeOptional converte <i>Risultato</i> in un <i>Optional</i>
	 * @return T se il risultato è disponibile, altrimenti Empty
	 */
	public Optional<T> comeOptional() {
		// Ok, ora sto esagerando, lo sto facendo tanto per
		return Optional.ofNullable(t);
	}
}
