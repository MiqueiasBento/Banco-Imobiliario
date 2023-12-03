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
				+ "VALOR DA COMPANHIA: R$ " + this.valorCompanhia + "\n"
				+ "ALUGUEL BASE: R$ " + this.aluguelBase;
		
		return out;
	}

	@Override
	public String toString() {
		return super.toString() + " - " + "VALOR DO ALUGUEL: R$ " + Main.format(this.aluguelBase);
	}
	
	// Getters e Setters
	public StatusPropriedade getStatus() {
		return status;
	}

	public double getValorCompanhia() {
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