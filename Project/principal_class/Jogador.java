package principal_class;

import enums.*;
import properties.Rua;
import Data.*;

import java.util.ArrayList;
import Controls.Main;

public class Jogador {
//	public static final long serialVersionUID = 1L;
	
	private ArrayList<PropriedadeAlocavel> propriedades;
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

	@Override
	public String toString() {
		String out = "\n+--------------- STATUS ----------------+\n";
		out += "JOGADOR: " + this.nome + "\n" + "SALDO: R$ " + Main.format(this.saldo) + "\n" + "STATUS: "
				+ this.status + "\n" + "POSIÇÃO: " + Propriedades.getPropriedade(this.posicao).getLabel() + "\n";

		if (this.propriedades.size() == 0) {
			out += ANSI.ITALIC + "SEM PROPRIEDADES" + ANSI.RESET;
		} else {
			out += "PROPRIEDADES:\n";
			for (PropriedadeAlocavel p : this.propriedades) {
				out += p.toString();
			}
		}
		out += "\n+---------------------------------------+";

		return out;
	}

	// Adiciona novos imóveis a propriedade (que só pode ser do tipo Rua), isso aumenta o valor do aluguel
	public void construirCasa(Rua rua, int quant) throws Exception {
		// Se já tiver construido um hotel, a funcao para aqui
		if (rua.getQuantImoveis() > 4) {
			throw new Exception("Ops! hotel já construido");
		}
		
		// Se o saldo não for suficiente não pode comprar
		if (this.getSaldo() - (rua.getValorImovel() * quant) < 0) {
			throw new Exception("Ops! Saldo insuficiente para a compra do imóvel");
		}
		
		// Recebe a propriedade adiciona um imovel, o valor do imovel é retirado do jogador
		if (rua.getQuantImoveis() == 4) {
			construirHotel(rua);
		} else {
			if (rua.getQuantImoveis() + quant > 4) {
				quant = 4 - rua.getQuantImoveis();
			}
			this.setSaldo(this.getSaldo() - (rua.getValorImovel() * quant));
			rua.setQuantImoveis(rua.getQuantImoveis() + quant);
		}
	}

	// Construir hotel, é 5x mais que o imóvel comum
	public void construirHotel(Rua rua) throws Exception {
		if (rua.getQuantImoveis() == 6) {
			throw new Exception("Ops! Hotel já construído");
		}
		if (this.getSaldo() - (rua.getValorImovel() * 5) < 0) {
			throw new Exception("Ops! Saldo insuficiente para a comprar do hotel");
		}

		rua.setQuantImoveis(rua.getQuantImoveis() + 2);
		this.setSaldo(this.getSaldo() - (rua.getValorImovel() * 5));
	}

	// Metodos da classe
	public void comprarPropriedade(PropriedadeAlocavel propriedade) {
		addPropriedade(propriedade);
		propriedade.setProprietario(this);
		propriedade.setStatus(StatusPropriedade.ALOCADA);

		this.setSaldo(this.getSaldo() - propriedade.getValorPropriedade());
	}

	// Verifica se possue certa propriedade e a retorna
	public PropriedadeAlocavel getPropriedade(int id) {
		for (PropriedadeAlocavel p : propriedades) {
			Propriedade prop = (Propriedade) p;
			if (prop.getId() == id) {
				return p;
			}
		}
		return null;
	}
	
	public ArrayList<PropriedadeAlocavel> getPropriedade(){
		return this.propriedades;
	}

	// Getters e Setters
	public void getPropriedades() {
		for (PropriedadeAlocavel p : propriedades) {
			Main.write(p.previaPropriedade());
		}
	}
	
	public int quantPropriedade() {
		return this.propriedades.size();
	}

	public void addPropriedade(PropriedadeAlocavel p) {
		this.propriedades.add(p);
	}

	public void rmPropriedade(PropriedadeAlocavel p) {
		int i = 0;
		for (PropriedadeAlocavel prop : propriedades) {
			if (p == prop) {
				propriedades.remove(i);
				break;
			}
			i++;
		}
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

	public void setPropriedades(ArrayList<PropriedadeAlocavel> propriedades) {
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
