package it.dev.testjava.bo;

public enum OperazioneT {
	ADDIZIONE("+"), SOTTRAZIONE("-"), MOLTIPLICAZIONE("x"), DIVISIONE(":");
	private String value;

	OperazioneT(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
