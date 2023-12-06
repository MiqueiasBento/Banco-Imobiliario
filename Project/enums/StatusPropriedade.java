package enums;

public enum StatusPropriedade {
	LIVRE, HIPOTECADA, ALOCADA;

	@Override
	public String toString() {
		switch (this) {
		case LIVRE:
			return "LIVRE";
		case HIPOTECADA:
			return "HIPOTECADA";
		case ALOCADA:
			return "ALOCADA";
		default:
			return "Sem status";
		}
	}
}
