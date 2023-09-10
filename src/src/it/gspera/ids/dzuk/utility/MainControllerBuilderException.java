package it.gspera.ids.dzuk.utility;

import it.gspera.ids.dzuk.utility.ClienteBuilderException.Tipo;

/**
 * MainControllerBuilderException è l'exception usata da <i>MainController.Builder</i>,
 * raggruppa un insieme di errori che possono avvenire durante la costruzione di un <i>MainController</i>.
 * Lo specifico errore è definito dall'enum <i>MainControllerBuilderException.Tipo</i>,
 * <br>
 * E' possibile ottenere una descrizione dell'errore tramite <i>MainControllerBuilderException#toString</i>
 * o il valore dell'errore tramite <i>MainControllerBuilderException#getTipo</i>.
 * 
 * @see it.gspera.ids.dzuk.controller.MainController.Builder
 * @see it.gspera.ids.dzuk.utility.ClienteBuilderException.Tipo
 * @author Giovanni Spera
 * @since  1.0
 */
public class MainControllerBuilderException extends Exception {
	private static final long serialVersionUID = -8775456657558078301L;
	public enum Tipo {
		ProdottoDAONonDefinito("Il prodotto dao non è stato selezionato"),
		UtenteDAONonDefinito("L'utente dao non è stato selezionato"),
		MainControllerGiaCostruito("Il main controller è già stato costruito"),
		ClienteDAONonDefinito("Il cliente dao non è stato selezionato"),
		;
		
		private final String msg;
		
		private Tipo(String msg) {
			this.msg = msg;
		}
		
		public String toString() { return this.msg; }
	}
	
	private Tipo tipo;
	public MainControllerBuilderException(Tipo t) {
		super(t.toString());
		tipo = t;
	}
	
	public Tipo getTipo() { return tipo; }
	public String toString() { return this.tipo.toString(); }
}
