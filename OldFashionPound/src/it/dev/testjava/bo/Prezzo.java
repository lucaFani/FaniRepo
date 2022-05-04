package it.dev.testjava.bo;

public class Prezzo {

	private int sterline;
	private int scellini;
	private int pence;

	public int getSterline() {
		return sterline;
	}

	public void setSterline(int sterline) {
		this.sterline = sterline;
	}

	public int getScellini() {
		return scellini;
	}

	public void setScellini(int scellini) {
		this.scellini = scellini;
	}

	public int getPence() {
		return pence;
	}

	public void setPence(int pence) {
		this.pence = pence;
	}

	protected Prezzo() {
		this.sterline = 0;
		this.scellini = 0;
		this.pence = 0;
	}

	protected Prezzo(int sterline, int scellini, int pence) {
		this.sterline = sterline;
		this.scellini = scellini;
		this.pence = pence;
	}

	public static Prezzo creaPrezzo() {
		return new Prezzo();
	}

	public static Prezzo creaPrezzo(int pence) {
		Prezzo prezzo = new Prezzo(0, 0, pence);

		return prezzo;
	}

	public static Prezzo creaPrezzo(int sterline, int scellini, int pence) {
		Prezzo prezzo = new Prezzo(sterline, scellini, pence);

		return prezzo;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(sterline + "p ");
		sb.append(scellini + "s ");
		sb.append(pence + "d");

		return sb.toString();

	}
}
