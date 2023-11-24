package principal_class;
import enums.TipoCarta;

import Data.*;

public class Carta {
	private int id;
	private String label;
	private TipoCarta tipo;
	private int value;
	private boolean usada;
	private String comando;
	
	public Carta(int id, String label, TipoCarta tipo, int value, String comando) {
		this.id = id;
		this.label = label;
		this.tipo = tipo;
		this.value = value;
		this.comando = comando;
		
		this.usada = false;
	}
	
	@ Override
	public String toString() {
		String out = this.id + " - CARTA: " + this.tipo + "\n" 
				+ this.label + "\n";
		
		if(this.tipo == TipoCarta.GANHAR) {
			out += "Você ganhou " + value;
		} else if(this.tipo == TipoCarta.PERDER) {
			out += "Você perdeu " + value;
		} else if(this.tipo == TipoCarta.POSICAO) {
			// Dirigir-se a propriedade marcada com o número
			out += "Vá para " + Propriedades.getPropriedade(value).getLabel();
		}
		
		return out;
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
		return usada;
	}

	public String getComando() {
		return comando;
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

	public void setComando(String comando) {
		this.comando = comando;
	}
	
	
}
