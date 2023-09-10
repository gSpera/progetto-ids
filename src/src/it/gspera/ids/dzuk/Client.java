package it.gspera.ids.dzuk;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * A volte le cose più semplici sono quelle che funzionano meglio
 */
public class Client {
	
	private static final String SERVER_HOSTNAME = "127.0.0.1";
	private static final int SERVER_PORT = 3031;

	public static void exec(String[] args) throws UnknownHostException, IOException {
		Socket sock = new Socket(SERVER_HOSTNAME, SERVER_PORT);
		DataOutputStream sockToServer = new DataOutputStream(sock.getOutputStream());
		BufferedReader sockFromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Connessione con il server stabilita");
		
		// Senza creare thread
		while(true) {
			if (sockFromServer.ready()) {
				String line = sockFromServer.readLine();
				System.out.println(line);
			}
			if (stdin.ready()) {
				String line = stdin.readLine();
				sockToServer.writeChars(line + "\n");
			}
		}
	}
	
	public static void main(String []args) {
		boolean printStackTrace = false;
		for (String arg : args) {
			if (arg.equalsIgnoreCase("-stacktrace")) {
				printStackTrace = true;
			}
		}
		
		try {
			exec(args);
		} catch(ConnectException | UnknownHostException e) {
			System.err.println("Impossibile stabilire la connessione con il server");
			if (printStackTrace) e.printStackTrace();
			else System.err.println("Usa -stacktrace per avere lo stacktrace");
		} catch(IOException e) {
			System.err.println("Errore di input output");
			if (printStackTrace) e.printStackTrace();
			else System.err.println("Usa -stacktrace per avere lo stacktrace");
		} catch(Exception e) {
			System.err.println("Doveva annà così fratellì");
			e.printStackTrace();
		}
	}
}
