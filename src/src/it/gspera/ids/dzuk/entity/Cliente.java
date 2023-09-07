package it.gspera.ids.dzuk.entity;

import it.gspera.ids.dzuk.utility.ClienteBuilderException;

/***
 * Cliente mantiene tutte le informazioni riguardo un cliente(una pescheria o un ristorante)
 * Per costruire un nuovo Cliente si usi <i>Cliente.Builder</i>
 * @see it.gspera.ids.dzuk.Cliente.Builder
 * @author Giovanni Spera
 * @since  1.0
 */
public class Cliente {
	/***
	 * Builder costruisce nuove instanze di <i>Cliente</i>.
	 * Un Builder appena costruito può essere già usato per la costruzione tramite i metodi <i>Builder#con*</i>.
	 * E' possibile usare <i>Builder#costruisci</i> per finalizzare la costruzione e ottenere il <i>Cliente</i>,
	 * per poter riutilizzare dopo aver costruito un <i>Cliente</i> è necessario usare il metodo <i>Builder#nuovo</i>
	 * 
	 * @see it.gspera.ids.dzuk.entity.Cliente
	 */
	public class Builder {
		// Possiamo accedere agli attributi private
		private Cliente cliente;
		
		public Builder() {
			this.nuovo();
		}
		
		public void nuovo() {
			this.cliente = new Cliente();
		}
		
		public Builder conTipo(Tipo tipo) {
			cliente.tipo = tipo;
			return this;
		}
		
		public Builder conNome(String nome) {
			this.cliente.nome = nome;
			return this;
		}
		
		public Builder conIndirizzo(String indirizzo) {
			this.cliente.indirizzo = indirizzo;
			return this;
		}
		
		public Builder conEmail(String email) {
			this.cliente.email = email;
			return this;
		}
		
		public Builder conUsername(String username) {
			this.cliente.username = username;
			return this;
		}
		public Builder conPassword(String password) {
			this.cliente.password = password;
			return this;
		}
		
		public Builder conCodiceFatturazione(String codiceFatturazione) {
			this.cliente.codice_univoco_fatturazione = codiceFatturazione;
			return this;
		}
		
		public Cliente costruisci() throws ClienteBuilderException {
			if (cliente == null) {
				throw new ClienteBuilderException(ClienteBuilderException.Tipo.ClienteGiaCostruito);
			}
			
			if (cliente.tipo == null) {
				throw new ClienteBuilderException(ClienteBuilderException.Tipo.TipoClienteNonSpecificato);
			}
			if (cliente.nome == null || cliente.nome.length() == 0) {
				throw new ClienteBuilderException(ClienteBuilderException.Tipo.NomeVuoto);
			}
			if (cliente.username == null || cliente.username.length() == 0) {
				throw new ClienteBuilderException(ClienteBuilderException.Tipo.UsernameVuoto);
			}
			if (cliente.password == null || cliente.password.length() == 0) {
				throw new ClienteBuilderException(ClienteBuilderException.Tipo.PasswordVuota);
			}
			if (cliente.codice_univoco_fatturazione == null || cliente.codice_univoco_fatturazione.length() == 0) {
				throw new ClienteBuilderException(ClienteBuilderException.Tipo.CodiceFatturazioneNonSpecificato);
			}
			Cliente risultato = this.cliente;
			this.cliente = null;
			return risultato;
		}
	}
	
	public enum Tipo {
		Pescheria,
		Ristorante,
	}
	
	private Tipo tipo; // Non ha un setter
	private String nome;
	private String indirizzo;
	private String email;
	
	private String username;  // Login, non ha un setter
	private String password;
	
	private String codice_univoco_fatturazione; // Fatturazione Elettronica

	public Tipo getTipo() {
		return tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}
}
