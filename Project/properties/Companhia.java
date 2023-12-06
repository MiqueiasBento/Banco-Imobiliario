package properties;

import principal_class.*;
import Controls.Main;
import enums.*;

public class Companhia extends Propriedade implements PropriedadeAlocavel{
	private StatusPropriedade status;
	private double valorCompanhia;
	private double aluguelBase;
	private Jogador proprietario;
	
	public Companhia(int id, String label, double valorCompanhia, double aluguelBase) {
		super(id, label);
		this.status = StatusPropriedade.LIVRE;
		this.valorCompanhia = valorCompanhia;
		this.aluguelBase = aluguelBase;
		this.proprietario = null;
	}
	
	public double valorAluguel(int q) {
		return q * aluguelBase;
	}
	
	public String getPropriedade() {
		String out = super.toString() + " - " + this.status + "\n"
				+ "VALOR DA COMPANHIA: R$ " + Main.format(this.valorCompanhia) + "\n"
				+ "ALUGUEL BASE: R$ " + Main.format(this.aluguelBase);
		
		return out;
	}
	
	public String previaPropriedade() {
		String out = String.format("%02d", super.getId()) + ": " + super.getLabel();
		
		if(this.getStatus() == StatusPropriedade.ALOCADA) {
			out += " - ALUGUEL BASE: R$" + Main.format(this.getAluguelBase());
		} else if(this.getStatus() == StatusPropriedade.LIVRE) {
			out += " - VALOR DA PROPRIEDADE: R$ " + Main.format(this.getValorPropriedade());
		}
		
		out += " - " + getStatus();
		return out;
	}
	
	@Override
	public String toString() {
		String out = super.toString() + " - ";
		
		if(this.getStatus() == StatusPropriedade.HIPOTECADA)	out += this.getStatus();
		else 			 out += "ALUGUEL BASE: R$ " + Main.format(this.getAluguelBase());
		
		return out;
	}
	
	// Getters e Setters
	public StatusPropriedade getStatus() {
		return status;
	}

	public double getValorPropriedade() {
		return valorCompanhia;
	}

	public double getAluguelBase() {
		return aluguelBase;
	}

	public Jogador getProprietario() {
		return proprietario;
	}

	public void setStatus(StatusPropriedade status) {
		this.status = status;
	}

	public void setValorCompanhia(double valorCompanhia) {
		this.valorCompanhia = valorCompanhia;
	}

	public void setAluguelBase(double aluguelBase) {
		this.aluguelBase = aluguelBase;
	}

	public void setProprietario(Jogador proprietario) {
		this.proprietario = proprietario;
	}
}