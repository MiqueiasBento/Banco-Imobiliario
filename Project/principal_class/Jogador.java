package principal_class;

import enums.*;
import properties.Rua;
import Data.*;
import java.util.*;

public class Jogador {
	private ArrayList<Propriedade> propriedades;
	private String nome;
	private int estaLivre;
	private double saldo;
	private int posicao;
	private StatusJogador status;

	public Jogador(String nome) {
		super();
		this.nome = nome;
		this.propriedades = new ArrayList<>();
		this.estaLivre = 0;
		this.saldo = 1500000f;
		this.posicao = 0;
		this.status = StatusJogador.LIVRE;
	}

	public void irPrisao() throws Exception{
		this.setEstaLivre(3);
		this.setStatus(StatusJogador.PRESO);
	}

	public void sairPrisao() {
		this.setEstaLivre(0);
		this.setStatus(StatusJogador.PRESO);
	}
	
	public void pagarDivida(double valor, Jogador jogador) {
		jogador.setSaldo(valor + jogador.getSaldo());
		setSaldo(getSaldo() - valor);
	}
	
	@ Override
	public String toString() {
		String out = "Jogador: " + this.nome + "\n"
					+ "Saldo: R$ " + this.saldo + "\n"
					+ "Status: " + this.status + "\n"
					+ "Posição: " + Propriedades.getPropriedade(this.posicao) + "\n";
		
		if(this.propriedades.size() != 0) {
			out += "Sem propriedades";
		} else {
			for(Propriedade p : this.propriedades) {
				out += p.toString() + "\n";
			}
		}
		
		return out;
	}
	
	
	// Metodos da classe
	public void pagarDivida(Jogador receptor, double value) {
		setSaldo(getSaldo() - value);
		receptor.setSaldo(getSaldo() + value);
	}
	
	public void comprarPropriedade(PropriedadeAlocavel propriedade) {
		propriedades.add((Propriedade) propriedade);
		propriedade.setProprietario(this);
	}

	public void pagarAluguel(Jogador jogador, double value) {
		// Recebe o jogador que receberá o pagamento e o valor a ser pago pelo aluguel
		jogador.setSaldo(jogador.getSaldo() + value);
		setSaldo(getSaldo() - value);
	}
	
	// Adiciona um novo imóvel a propriedade (que só pode ser uma rua), isso aumenta o valor do aluguel
	public void construirCasa(Rua rua) throws Exception{
		// Se o saldo não for suficiente não pode comprar
		if(this.getSaldo() - rua.getValorImovel() < 0) {
			throw new Exception("Ops! Saldo insuficiente para a compra imóvel");
		}
		
		// Recebe a propriedadee adiciona um imovel, o valor do imovel é retirado do jogador
		if(rua.getQuantImoveis() == 4) {
			construirHotel(rua);
		} else {
			setSaldo(getSaldo() - rua.getValorImovel());
		}
		
		rua.setQuantImoveis(rua.getQuantImoveis() + 1);
		
		
	}
	
	// Construir hotel, é 5x mais que o imóvel comum
	public void construirHotel(Rua rua) throws Exception{
		if(this.getSaldo() - (rua.getValorImovel() * 5) < 0) {
			throw new Exception("Ops! Saldo insuficiente para a comprar hotel");
		}
		
		rua.setQuantImoveis(rua.getQuantImoveis() + 4);
		setSaldo(getSaldo() - (rua.getValorImovel() * 5));
	}
	
	
	// Getters e Setters
	public ArrayList<Propriedade> getPropriedades() {
		return propriedades;
	}

	public int isEstaLivre() {
		return estaLivre;
	}

	public double getSaldo() {
		return saldo;
	}

	public int getPosicao() {
		return posicao;
	}

	public StatusJogador getStatus() {
		return status;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPropriedades(ArrayList<Propriedade> propriedades) {
		this.propriedades = propriedades;
	}

	public void setEstaLivre(int estaLivre) {
		this.estaLivre = estaLivre;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public void setStatus(StatusJogador status) {
		this.status = status;
	}
}
