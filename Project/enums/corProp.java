package enums;

public enum corProp {
	CAPIM,
	VERMELHA,
	AZUL,
	ROSA,
	ROXA,
	VERDE,
	LARANJA,
	AMARELA;
	
	@ Override
	public String toString() {
		if(this == CAPIM)  return "VERDE CLARO";
		else if(this == VERMELHA) return "VERMELHA";
		else if(this == AZUL) return "AZUL";
		else if(this == ROSA) return "ROSA";
		else if(this == ROXA) return "ROXA";
		else if(this == VERDE) return "VERDE ESCURO";
		else if(this == LARANJA) return "LARANJA";
		else if(this == AMARELA) return "AMARELA";
		else return "sem cor";
	}
}
