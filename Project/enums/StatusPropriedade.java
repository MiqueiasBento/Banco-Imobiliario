package enums;

public enum StatusPropriedade {
	LIVRE, HIPOTECADA, ALOCADA;

	@Override
	public String toString() {
		switch (this) {
		case LIVRE:
			return "Livre";
		case HIPOTECADA:
			return "Hipotecada";
		case ALOCADA:
			return "Alocada";
		default:
			return "Sem status";
		}
	}
}
