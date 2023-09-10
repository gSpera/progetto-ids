package it.gspera.ids.dzuk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.gspera.ids.dzuk.boundary.LoginBoundary;
import it.gspera.ids.dzuk.controller.MainController;
import it.gspera.ids.dzuk.dao.SqlDaoImpl;
import it.gspera.ids.dzuk.utility.FattureManager;
import it.gspera.ids.dzuk.utility.MainControllerBuilderException;
import it.gspera.ids.dzuk.utility.NoOpFattureManagerImpl;

/**
 * Il server ascolta per nuove connessioni e ne effettua il login
 */
public class Server extends Thread{
	public static void main(String []args) {
		boolean printStackTrace = false;
		for (String arg : args) {
			if (arg.equalsIgnoreCase("-stacktrace")) {
				printStackTrace = true;
			}
		}
		
		try {
			exec(args);
		} catch(IOException e) {
			System.err.println("Errore di input output");
			if (printStackTrace) e.printStackTrace();
			else System.err.println("Usa -stacktrace per avere lo stacktrace");
		} catch(MainControllerBuilderException e) {
			System.err.println("Impossibile costruire il MainController: "+ e.getTipo().toString());
			if (printStackTrace) e.printStackTrace();
			else System.err.println("Usa -stacktrace per avere lo stacktrace");
		} catch(Exception e) {
			System.err.println("Doveva annà così fratellì");
			e.printStackTrace();
		}
	}
	
	public static void exec(String[] args) throws IOException, MainControllerBuilderException {
		ServerSocket server = new ServerSocket(Constants.SERVER_PORT);
		Random random = new Random();
		SqlDaoImpl dao = null;
		FattureManager fattureManager = new NoOpFattureManagerImpl();
		
		try {
			dao = new SqlDaoImpl(Constants.DATABASE_URL, LogManager.getLogger("SqlDao"));
		} catch (Exception e) {
			System.err.println("Impossibile creare il DAO:" + e.toString());
			e.printStackTrace();
			System.exit(1);
		}
		
		new MainController.Builder()
			.conLogger(LogManager.getLogger("MainController"))
			.conProdottoDAO(dao)
			.conClienteDAO(dao)
			.conUtenteDAO(dao)
			.conFattureManager(fattureManager)
			.costruisciESostituisci();
		
		System.out.println("In attesa di una connessione");
		while(true) {
			Socket socket = server.accept();
			int connectionID = random.nextInt();
			Logger log = LogManager.getLogger("connessione-" + connectionID); // Ogni connessione ha un suo logger
			PrintStream out = new PrintStream(socket.getOutputStream());
			Scanner in = new Scanner(socket.getInputStream());
			
			Thread t = new Thread(() -> {
				System.out.println("Connessione ricevuta");
				out.println("Benvenuto su dzuk, versione: "+ Constants.VERSION.toString());
				new LoginBoundary(in, out, log).main();
				try {
					socket.close();
				} catch (IOException e) {
					System.err.println("Impossibile chiudere la socket");
					e.printStackTrace();
				}
			});
			t.run();
		}
	}
}
