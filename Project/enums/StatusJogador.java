package enums;

public enum StatusJogador{
	LIVRE,
	PRESO,
	FALIDO;
	
	@ Override
	public String toString() {
		switch(this) {
			case LIVRE:
				return "LIVRE";
			case PRESO:
				return "PRESO";
			case FALIDO:
				return "FALIDO";
			default:
				return "sem status";
		}
	}
}
