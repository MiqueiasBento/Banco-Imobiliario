package principal_class;

import java.util.*;
import properties.*;

import Data.Propriedades;
import enums.StatusJogador;

public class BancoImobiliario {
	private ArrayList<Jogador> jogadores;
	private Jogador winner;
	private Tabuleiro board;

	public BancoImobiliario() {
		jogadores = new ArrayList<>();
		winner = null;
		board = new Tabuleiro();
	}

	// Executa as principais funções do jogo
	public void jogar(Jogador j) {
				int d1 = jogada();
				int d2 = jogada();
				
				try {
					board.moverJogador(j, d1, d2);
					verificarPosicao(j, (d1 + d2));
					if (winner != null) {
						return;
					}
				} catch (Exception e) {
					write(e.getMessage());
				}

		// Vencedor do jogo
		write("Vencedor: \n" + winner);
		return;
	}

	public void winner() {
		int playersLose = 0;
		for (Jogador j : jogadores) {
			if (j.getStatus() == StatusJogador.FALIDO) {
				playersLose++;
			}
		}

		if (playersLose == jogadores.size() - 1) {
			for (Jogador j : jogadores) {
				if (!(j.getStatus() == StatusJogador.FALIDO)) {
					winner = j;
					return;
				}
			}
		}
	}
	
	// Prender jogador
	public void prenderJogador(Jogador jogador) {
		Prisao.entrar(jogador);
	}
	
	// Soltar jogador
	public void libertarJogador(Jogador jogador) {
		Prisao.sair(jogador);
	}

	public double hipotecar(Propriedade propriedade) {
		return 0;
	}

	public void pagarConta(Jogador jogador) {

	}
	
	// Irá fazer toda uma verificação da atual posição do player, se o local já está alocado, se precisa pagar aluguel e etc
	public void verificarPosicao(Jogador jogador, int n) {
		int id = jogador.getPosicao();

		// Se for uma Rua, pode ser comprável ou deve pagar o aluguel
		if (Propriedades.getPropriedade(id) instanceof Rua) {
			Rua r = (Rua) Propriedades.getPropriedade(id);

			if (r.getProprietario() == null) {
				// Se estiver sem proprietario, pode ser comprada pelo jogador que está na
				// posicao
				write(r);
				write("Deseja comprar? (sim / nao)");

				String response = input();
				if (response.equals("sim")) {
					jogador.comprarPropriedade(r);
				} else {
					return;
				}
			} else {
				// Paga o valor do aluguel da propriedade ou vai para a prisão
				write("Aluguel da propriedade: R$ " + r.valorAluguel());
			}
		} else if(Propriedades.getPropriedade(id) instanceof CartaEvento) {
			// Se estiver na posicao de uma Carta/Reves, puxa a carta e a executa
			CartaEvento ce = (CartaEvento) Propriedades.getPropriedade(id);
			ce.executarCarta(jogador);
		} else if (Propriedades.getPropriedade(id) instanceof Companhia) {
			Companhia c = (Companhia) Propriedades.getPropriedade(id);

			if (c.getProprietario() == null) {
				// Se estiver sem proprietario, pode ser comprada pelo jogador que está na
				// posicao
				write(c);
				write("Deseja comprar? (sim / nao)");

				String response = input();
				if (response.equals("sim")) {
					jogador.comprarPropriedade(c);
				} else {
					return;
				}
			} else {
				// Paga o valor do aluguel da propriedade ou vai para a prisão
				write("Aluguel da propriedade: R$ " + c.valorAluguel(n));
			}
		} else if (Propriedades.getPropriedade(id) instanceof Propriedade) {
			Propriedade p = (Propriedade) Propriedades.getPropriedade(id);

			if(p.getId() == 1) {
				// Posicao do inicio, ganha 200k
				jogador.setSaldo(jogador.getSaldo() + 2000000);
			} else if(p.getId() == 31) {
				// Posicao da prisao, entao vai em cana
				jogador.setStatus(StatusJogador.PRESO);
				jogador.setEstaLivre(3);
			} else if(p.getId() == 11 || p.getId() == 21) {
				// Posicao da detenção e férias, então não acontece nada
				return;
			} 
		}
	}
	
	// Sorteio um dado de seis faces (1 a 6)
	public int jogada() {
		Random r = new Random();
		return r.nextInt(6) + 1;
	}
	
	public Scanner sc = new Scanner(System.in);

	public void write(Object value) {
		System.out.println(value);
	}

	public String input() {
		return sc.nextLine();
	}

	public int number(String value) {
		return Integer.parseInt(value);
	}
	
	@Override
	public String toString() {
		return "";
	}
	
	// Adicionar jogadores, tendo que ser no minimo 2 e no maximo 6
	public void addJogadores() {
		for(int i = 0; i < 3; i++) {
			System.out.print("Jogador " + (i+1) + ": ");
			String in = sc.nextLine();
			jogadores.add(new Jogador(in));
		}
	}
	
	// Retorna todos os jogadores da partida
	public ArrayList<Jogador> getJogadores(){
		return jogadores;
	}
	
	public boolean isWinner() {
		if(winner == null) return false;
		else return true;
	}
}