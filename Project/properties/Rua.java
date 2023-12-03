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

	public void comprarImovel(Jogador proprieatario) {
		// Compra a propriedade, diminui o valor do saldo do jogador e coloca o
		// proprietário da rua
		// como o jogador passado como parâmetro
	}

	public double aluguelRua(int id) {
		// Calcula o valor do aluguel da rua passada de acordo com o id
		return 0f;
	}
	
	public String getPropriedade() {
		return super.toString() + ": " + this.status + " - " + this.cor + "\n"
				+ "VALOR DA PROPRIEDADE: R$ " + Main.format(this.valorPropriedade) + "\n"
				+ "VALOR DO ALUGUEL: R$ " + Main.format(this.aluguelBase) + "\n"
				+ "VALOR DO IMÓVEL: R$ " + Main.format(this.valorImovel);
	}
	
	@Override
	public String toString() {
		return super.toString() + " - " + "VALOR DO ALUGUEL: R$ " + Main.format(this.aluguelBase);
	}

	
	// Getters e Setters
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
