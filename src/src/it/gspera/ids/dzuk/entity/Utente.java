package it.gspera.ids.dzuk.entity;

/**
 * Un utente registrato al software,
 * <i>Utente</i> è un wrapper attorno all'effettiva instanza dell'utente,
 * che estente <i>Utente</i>.
 */
public class Utente {
	/**
	 * Il tipo di utente
	 */
	public enum Tipo {
		NonDefinito(-1), // Esiste solo per comodità, non dovrebbe mai essere usato
		Cliente(0),
		Impiegato(1),
		Fattorino(2),
		;
		
		Tipo(int id) {
			this.id = id;
		}

		public final int id;
	};
	
	protected String username;
	protected String password;
	protected Tipo tipo;
	
	protected Utente() {
		tipo = Tipo.NonDefinito;
	}
	
	protected Utente(Tipo t) {
		// In teoria il Tipo non dovrebbe essere passato come argomento,
		// Dovrebbe dipendere dalla classe chiamante,
		// ma assumiamo che chi chiama questa funzione sappia quello che sta facendo
		tipo = t;
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public Tipo getTipo() {
		return tipo;
	}
}
