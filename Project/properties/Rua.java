package properties;

import principal_class.*;
import Controls.Main;
import enums.*;

public class Rua extends Propriedade implements PropriedadeAlocavel {
	private StatusPropriedade status;
	private corProp cor;
	private double valorPropriedade;
	private double valorImovel;
	private double aluguelBase;
	private Jogador proprietario;
	private int quantImoveis;

	public Rua(int id, String label, corProp cor, double valorPropriedade, double valorImovel, double aluguelBase) {
		super(id, label);
		this.status = StatusPropriedade.LIVRE;
		this.cor = cor;
		this.valorPropriedade = valorPropriedade;
		this.valorImovel = valorImovel;
		this.aluguelBase = aluguelBase;
	}

	// Metodos da classe
	public void setProprietario(Jogador proprietario) {
		this.proprietario = proprietario;
	}

	public double valorAluguel() {
		return this.aluguelBase * Math.pow(2, this.quantImoveis);
	}

	public String getPropriedade() {
		return super.toString() + ": " + this.cor + " - " + this.status + "\n" + "VALOR DA PROPRIEDADE: R$ "
				+ Main.format(this.valorPropriedade) + "\n" + "VALOR DO ALUGUEL: R$ " + Main.format(this.aluguelBase)
				+ "\n" + "VALOR DO IMÓVEL: R$ " + Main.format(this.valorImovel);
	}

	public String previaPropriedade() {
		String out = String.format("%02d", super.getId()) + ": " + super.getLabel();

		if (this.getStatus() == StatusPropriedade.LIVRE) {
			out += " - VALOR DO ALUGUEL: R$" + Main.format(this.valorAluguel());
		} else if (this.getStatus() == StatusPropriedade.ALOCADA) {
			out += " - VALOR DA PROPRIEDADE: R$" + Main.format(this.getValorPropriedade());
		}

		out += " - " + getStatus();
		return out;
	}

	@Override
	public String toString() {
		String out = super.toString() + " - ";

		if (this.getStatus() == StatusPropriedade.HIPOTECADA)
			out += this.getStatus();
		else
			out += "VALOR ALUGUEL: R$ " + Main.format(this.valorAluguel());

		return out;
	}

	// Getters e Setters
	public boolean isAlocada() {
		if(this.getStatus() == StatusPropriedade.ALOCADA) 	return true;
		else 												return false;
	}
	
	public StatusPropriedade getStatus() {
		return status;
	}

	public corProp getCor() {
		return cor;
	}

	public double getValorPropriedade() {
		return valorPropriedade;
	}

	public double getValorImovel() {
		return valorImovel;
	}

	public double getAluguelBase() {
		return aluguelBase;
	}

	public Jogador getProprietario() {
		return proprietario;
	}

	public int getQuantImoveis() {
		return this.quantImoveis;
	}

	public void setStatus(StatusPropriedade status) {
		this.status = status;
	}

	public void setCor(corProp cor) {
		this.cor = cor;
	}

	public void setValorPropriedade(double valorPropriedade) {
		this.valorPropriedade = valorPropriedade;
	}

	public void setValorImovel(double valorImovel) {
		this.valorImovel = valorImovel;
	}

	public void setAluguelBase(double aluguelBase) {
		this.aluguelBase = aluguelBase;
	}

	public void setQuantImoveis(int quantImoveis) {
		this.quantImoveis = quantImoveis;
	}
}
