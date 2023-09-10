package it.gspera.ids.dzuk.boundary;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.gspera.ids.dzuk.controller.MainController;

/**
 * <i>BoundaryBackgroundScheduler</i> si occupa di schedulare, ed eseguire le varie attività
 * dipendenti dal tempo, cioè la generazione dei report e delle fatture, con relativo invio.
 */
public class BoundaryBackgroundScheduler {
	private ScheduledExecutorService scheduler;
	private Logger log;
	
	public BoundaryBackgroundScheduler() {
		scheduler = Executors.newScheduledThreadPool(1);
		log = LogManager.getLogger("BackgroundScheduler");
		
		Calendar delayCalendar = Calendar.getInstance();
		delayCalendar.set(Calendar.SECOND, 0);
		delayCalendar.set(Calendar.MINUTE, 0);
		delayCalendar.set(Calendar.HOUR, 0);  // Riporto a mezzanotte, l'importante è non metterlo tra le 2 e le 3 di notte
		delayCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		delayCalendar.add(Calendar.DATE, 7); // Lunedì prossimo
		long delay = delayCalendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
		
		log.info("started");
		// Idealmente avremmo impostato direttamente uno scheduling ogni lunedì
		scheduler.scheduleAtFixedRate(() -> command(), delay, 7 * 24 * 3600 * 1000, TimeUnit.MILLISECONDS);
	}
	
	public void command() {
		log.info("scheduled");
		boolean ok = MainController.the().generaReport();
		if (!ok) {
			log.error("Non è stato possibile generare il report");
		} else {
			log.info("Report generato con successo");
		}
		
		ok = MainController.the().generaEInviaFatture();
		if (!ok) {
			log.error("Non è stato possibile generare e inviare le fatture");
		} else {
			log.info("Fatture generate e inviate con successo");
		}
	}
}
