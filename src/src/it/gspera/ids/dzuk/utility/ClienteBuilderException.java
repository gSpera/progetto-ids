package it.gspera.ids.dzuk.utility;


/**
 * ClienteBuilderException è l'exception usata da <i>Cliente.Builder</i>,
 * raggruppa un insieme di errori che possono avvenire durante la costruzione di un <i>Cliente</i>.
 * Lo specifico errore è definito dall'enum <i>ClienteBuilderException.Tipo</i>,
 * <br>
 * E' possibile ottenere una descrizione dell'errore tramite <i>ClienteBuilderException#toString</i>
 * o il valore dell'errore tramite <i>ClienteBuilderException#getTipo</i>.
 * 
 * @see it.gspera.ids.dzuk.entity.Cliente.Builder
 * @see it.gspera.ids.dzuk.utility.ClienteBuilderException.Tipo
 * @author Giovanni Spera
 * @since  1.0
 */
public class ClienteBuilderException extends Exception {
	private static final long serialVersionUID = -8775456657558078300L;
	public enum Tipo {
		TipoClienteNonSpecificato("Non è stato specificata la tipologia del cliente"),
		NomeVuoto("Non è stato specificato il nome del cliente"),
		UsernameVuoto("Non è stato specificato un username per il cliente"),
		PasswordVuota("Non è stato specificata una password per il cliente"),
		CodiceFatturazioneNonSpecificato("Non è stato specificato il codice fatturazione del cliente"),
		ClienteGiaCostruito("Il cliente è già stato costruito"),
		;
		
		private final String msg;
		
		private Tipo(String msg) {
			this.msg = msg;
		}
		
		public String toString() { return this.msg; }
	}
	
	private Tipo tipo;
	public ClienteBuilderException(Tipo t) {
		super(t.toString());
		tipo = t;
	}
	
	public Tipo getTipo() { return tipo; }
	public String toString() { return this.tipo.toString(); }
}
