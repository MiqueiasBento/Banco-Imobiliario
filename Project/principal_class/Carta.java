package principal_class;
import enums.TipoCarta;

public class Carta {
	private int id;
	private String label;
	private TipoCarta tipo;
	private int value;
	private boolean usada;
	
	public Carta(int id, String label, TipoCarta tipo, int value) {
		this.id = id;
		this.label = label;
		this.tipo = tipo;
		this.value = value;
		
		this.usada = false;
	}
	
	@ Override
	public String toString() {
		return " ??? CARTA | REVÉS ??? " + "\n" + this.label + "\n";
	}
	
	
	// Metodos da classe
	
	// Getters e Setters
	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public TipoCarta getTipo() {
		return tipo;
	}

	public int getValue() {
		return value;
	}

	public boolean isUsada() {
		if(this.usada)	return true;
		else			return false;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setTipo(TipoCarta tipo) {
		this.tipo = tipo;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setUsada(boolean usada) {
		this.usada = usada;
	}
}
