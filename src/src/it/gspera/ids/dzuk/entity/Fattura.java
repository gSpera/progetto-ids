package it.gspera.ids.dzuk.entity;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * <i>Fattura</i> rappresenta una fattura già generata dal software,
 * viene usata principalmente per tenere traccia di quali prodotti sono già stati fatturati.
 */
public class Fattura {
	private int codice; // Identificativo unico della fattura
	
	private Cliente cliente;
	private List<Prodotto> prodotti;
	
	public Fattura(Cliente cliente, List<Prodotto> prodotti) {
		this.cliente = cliente;
		this.prodotti = prodotti;
	}
	
	public String toXML() throws ParserConfigurationException {
		// Si ringrazia https://www.fatturapa.gov.it/it/lafatturapa/esempi/
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document dom = db.newDocument();
		Element root = dom.createElement("p:FatturaElettronica");
		dom.createElement("FatturaElettronicaHeader");
		return null;
	}
}
