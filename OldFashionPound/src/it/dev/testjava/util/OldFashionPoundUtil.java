package it.dev.testjava.util;

import it.dev.testjava.bo.Prezzo;
import it.dev.testjava.bo.PrezzoConResto;
import it.dev.testjava.exception.TestJavaException;

public class OldFashionPoundUtil {

	private final static int SCELLINI_TO_PENCE = 12;
	private final static int STERLINE_TO_SCELLINI = 20;
	private final static int STERLINE_TO_PENCE = SCELLINI_TO_PENCE * STERLINE_TO_SCELLINI;

	public static int convertiPrezzoInPence(Prezzo prezzo) {
		return prezzo.getSterline() * STERLINE_TO_PENCE + prezzo.getScellini() * SCELLINI_TO_PENCE + prezzo.getPence();
	}

	public static void normalizzaPrezzo(Prezzo prezzo) {
		int pence = prezzo.getPence() % SCELLINI_TO_PENCE;
		int scellini = prezzo.getScellini() + prezzo.getPence() / SCELLINI_TO_PENCE;
		int sterline = prezzo.getSterline() + scellini / STERLINE_TO_SCELLINI;

		prezzo.setPence(pence);
		prezzo.setScellini(scellini % STERLINE_TO_SCELLINI);
		prezzo.setSterline(sterline);

	}

	public static Prezzo add(Prezzo prezzo1, Prezzo prezzo2) {
		int pence = convertiPrezzoInPence(prezzo1) + convertiPrezzoInPence(prezzo2);
		Prezzo prezzoFinale = Prezzo.creaPrezzo(pence);
		normalizzaPrezzo(prezzoFinale);
		return prezzoFinale;

	}

	public static Prezzo subtract(Prezzo prezzo1, Prezzo prezzo2) {
		int pence = Math.abs(convertiPrezzoInPence(prezzo1) - convertiPrezzoInPence(prezzo2));
		Prezzo prezzoFinale = Prezzo.creaPrezzo(pence);
		normalizzaPrezzo(prezzoFinale);
		return prezzoFinale;

	}

	public static Prezzo multiply(Prezzo prezzo, int moltiplicatore) {

		int pence = convertiPrezzoInPence(prezzo) * Math.abs(moltiplicatore);
		Prezzo prezzoFinale = Prezzo.creaPrezzo(pence);
		normalizzaPrezzo(prezzoFinale);
		return prezzoFinale;
	}

	public static Prezzo divide(Prezzo prezzo, int divisore) throws TestJavaException {

		if (divisore <= 0)
			throw new TestJavaException("Divisore " + divisore + " non accettato.");

		int pence = convertiPrezzoInPence(prezzo);
		int res = pence / divisore;
		PrezzoConResto prezzoFinale = PrezzoConResto.creaPrezzoConResto(res);
		normalizzaPrezzo(prezzoFinale);
		res = pence % divisore;
		Prezzo resto = Prezzo.creaPrezzo(res);
		normalizzaPrezzo(resto);
		prezzoFinale.setResto(resto);
		return prezzoFinale;
	}

}
