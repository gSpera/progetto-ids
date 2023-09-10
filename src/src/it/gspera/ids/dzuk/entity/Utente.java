package it.gspera.ids.dzuk.entity;

/**
 * Un utente registrato al software,
 * <i>Utente</i> è un wrapper attorno all'effettiva instanza dell'utente,
 * che estente <i>Utente</i>.
 */
public abstract class Utente {
	/**
	 * Il tipo di utente
	 */
	public enum Tipo {
		Cliente(0),
		Impiegato(1),
		Fattorino(2),
		;
		
		public final int id;
		
		Tipo(int id) {
			this.id = id;
		}
		
		public static Tipo daInt(int id) {
			switch(id) {
			case 0:
				return Cliente;
			case 1:
				return Impiegato;
			case 2:
				return Fattorino;
			default:
				return null;
			}
		}
		
		public int comeInt() {
			return this.id;
		}
	};
	
	protected String username;
	protected String password;
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
}
