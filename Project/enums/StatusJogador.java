package enums;

public enum StatusJogador {
	LIVRE,
	PRESO,
	FALIDO;
	
	@ Override
	public String toString() {
		switch(this) {
			case LIVRE:
				return "Livre";
			case PRESO:
				return "Preso";
			case FALIDO:
				return "Falido kkk";
			default:
				return "sem status";
		}
	}
}
