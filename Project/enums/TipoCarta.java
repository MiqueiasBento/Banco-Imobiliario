package enums;

public enum TipoCarta {
	POSICAO,
	PERDER,
	GANHAR;
	
	@ Override
	public String toString() {
		switch(this) {
			case POSICAO:
				return "Posicao";
			case PERDER:
				return "Perder";
			case GANHAR:
				return "Ganhar";
			default:
				return "Sem status";
		}
	}
}
