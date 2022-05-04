package it.dev.testjava.bo;

public class PrezzoConResto extends Prezzo {

	private Prezzo resto;

	public Prezzo getResto() {
		return resto;
	}

	public void setResto(Prezzo resto) {
		this.resto = resto;
	}

	private PrezzoConResto() {
		super();
		this.resto = new Prezzo();
	}

	private PrezzoConResto(int sterline, int scellini, int pence) {
		super(sterline, scellini, pence);
		this.resto = new Prezzo();
	}

	public static PrezzoConResto creaPrezzoConResto() {
		return new PrezzoConResto();
	}

	public static PrezzoConResto creaPrezzoConResto(int pence) {
		return new PrezzoConResto(0, 0, pence);

	}

	public static PrezzoConResto creaPrezzoConResto(int sterline, int scellini, int pence) {
		return new PrezzoConResto(sterline, scellini, pence);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(getSterline() + "p ");
		sb.append(getScellini() + "s ");
		sb.append(getPence() + "d ");

		if (resto != null && (resto.getSterline() > 0 || resto.getScellini() > 0 || resto.getPence() > 0)) {
			sb.append("(");
			if (resto.getSterline() > 0) {
				sb.append(resto.getSterline() + "p ");
			}
			if (resto.getScellini() > 0) {
				sb.append(resto.getScellini() + "s ");
			}
			if (resto.getPence() > 0) {
				sb.append(resto.getPence() + "p");
			}
			sb.append(") ");
		}

		return sb.toString();

	}
}
