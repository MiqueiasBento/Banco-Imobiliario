package properties;

import principal_class.*;
import enums.*;

public class Rua extends Propriedade implements PropriedadeAlocavel {
	private StatusPropriedade status;
	private String cor;
	private double valorPropriedade;
	private double valorImovel;
	private double aluguelBase;
	private Jogador proprietario;
	private int quantImoveis;

	public Rua(int id, String label, String cor, double valorPropriedade, double valorImovel, double aluguelBase) {
		super(id, label);
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

	@Override
	public String toString() {
		return "";
	}

	
	// Getters e Setters
	public StatusPropriedade getStatus() {
		return status;
	}

	public String getCor() {
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

	public void setCor(String cor) {
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
