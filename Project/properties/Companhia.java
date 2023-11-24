package properties;

import principal_class.*;
import enums.*;

public class Companhia extends Propriedade implements PropriedadeAlocavel{
	private StatusPropriedade status;
	private double valorCompanhia;
	private double aluguelBase;
	private Jogador proprietario;
	
	public Companhia(int id, String label, double valorCompanhia, double aluguelBase) {
		super(id, label);
		this.valorCompanhia = valorCompanhia;
		this.aluguelBase = aluguelBase;
		this.proprietario = null;
	}
	
	public double valorAluguel(int q) {
		return q * aluguelBase;
	}
	
	@ Override
	public String toString() {
		String out = super.toString() + "\n"
				+ "Preço: R$ " + this.valorCompanhia + "\n"
				+ "Aluguel base: R$ " + this.aluguelBase + "\n"
				+ "Propretário: " + this.proprietario != null ? proprietario.getNome() : "Sem proprietario" + "\n";
		
		return out;
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