package principal_class;

import Controls.Main;
import java.util.ArrayList;
import java.util.Random;

import properties.*;
import Data.Propriedades;
import enums.StatusJogador;

public class BancoImobiliario {
	private ArrayList<Jogador> jogadores;
	private Jogador winner;
	private Tabuleiro board;

	public BancoImobiliario() {
		this.jogadores = new ArrayList<>();
		this.winner = null;
		this.board = new Tabuleiro();
		
		this.jogadores.add(new Jogador("Jane"));
		this.jogadores.add(new Jogador("Maicon"));
	}

	// Executa as principais funções do jogo
	public void jogar(Jogador j) {
//				int d1 = jogada();
//				int d2 = jogada();
				int d1 = 5;
				int d2 = 0;
				
				Main.write("Dados: " + d1 + " " + d2);
				
				try {
					board.moverJogador(j, d1, d2);
					verificarPosicao(j, (d1 + d2));
				} catch (Exception e) {
					Main.write(e.getMessage());
				}

		// Se tiver um vencedor do jogo
		if (winner != null) {
			Main.write("Vencedor: \n" + winner);
			return;
		}
	}

	public boolean winner() {
		int playersLose = 0;
		
		// Conta players falidos, que já perderam o jogo
		for (Jogador j : jogadores) {
			if (j.getStatus() == StatusJogador.FALIDO) {
				playersLose++;
			}
		}
		
		// Se a quantidade de players falidos estiver faltando um para o total, entao temos um vencedor
		if (playersLose == jogadores.size() - 1) {
			for (Jogador j : jogadores) {
				if (j.getStatus() != StatusJogador.FALIDO) {
					winner = j;
					Main.write(j.getNome().toUpperCase() + " VENCEU !!!!!!!!");
					return true;
				}
			}
		}
		
		return false;
	}

	public double hipotecar(Propriedade propriedade) {
		return 0;
	}

	public void pagarConta(Jogador jogador) {

	}
	
	// Irá fazer toda uma verificação da atual posição do player, se o local já está alocado, se precisa pagar aluguel e etc
	public void verificarPosicao(Jogador jogador, int n)  throws Exception{
		int id = jogador.getPosicao();
		
		// Se for uma Rua, pode ser comprável ou deve pagar o aluguel
		if (Propriedades.getPropriedade(id) instanceof Rua) {
			Rua r = (Rua) Propriedades.getPropriedade(id);

			if (r.getProprietario() == null) {
				// Se estiver sem proprietario, pode ser comprada pelo jogador que está na posicao
				Main.write(r.getPropriedade());
				Main.print("\nDeseja comprar? (sim / nao) ");
				String response = Main.input();

				if (response.equals("sim")) {
					if(jogador.getSaldo() - r.getValorPropriedade() < 0) throw new Exception("Saldo insuficiente");
					
					jogador.comprarPropriedade(r);
					jogador.setSaldo(jogador.getSaldo() - r.getValorPropriedade());
				} else {
					return;
				}
			} else {
				// Paga o valor do aluguel da propriedade, independente se tem saldo ou nao
				Main.write("Aluguel da propriedade: R$ " + Main.format(r.valorAluguel()));
				
				jogador.setSaldo(jogador.getSaldo() - r.valorAluguel());	// Saldo descontado no saldo do jogador da vez
				r.getProprietario().setSaldo(r.getProprietario().getSaldo() + r.valorAluguel());	// Saldo acrescentado ao proprietário
			}
		} else if(Propriedades.getPropriedade(id) instanceof CartaEvento) {
			// Se estiver na posicao de uma Carta/Reves, puxa a carta e a executa
			CartaEvento.executarCarta(jogador);
			
			verificarPosicao(jogador, n);
		} else if (Propriedades.getPropriedade(id) instanceof Companhia) {
			// Se for uma Companhia, pode ser comprável ou deve pagar o aluguel (que é os dados x valor fixo do aluguel)
			Companhia c = (Companhia) Propriedades.getPropriedade(id);

			if (c.getProprietario() == null) {
				// Se estiver sem proprietario, pode ser comprada pelo jogador que está na posicao
				Main.write(c.getPropriedade());
				Main.print("\nDeseja comprar? (sim / nao) ");

				String response = Main.input();
				if (response.equals("sim")) {
					if (response.equals("sim")) {
						if(jogador.getSaldo() - c.getValorCompanhia() < 0) throw new Exception("Saldo insuficiente");
						
						jogador.comprarPropriedade(c);
						jogador.setSaldo(jogador.getSaldo() - c.getValorCompanhia());
					}
				} else {
					return;
				}
			} else {
				// Paga o valor do aluguel da propriedade, independente se tem saldo ou nao
				Main.write("Aluguel da propriedade: R$ " + Main.format(c.valorAluguel(n)));
				
				jogador.setSaldo(jogador.getSaldo() - c.valorAluguel(n));	// Saldo descontado no saldo do jogador da vez
				c.getProprietario().setSaldo(c.getProprietario().getSaldo() + c.valorAluguel(n));	// Saldo acrescentado ao proprietário
			}
		} else if (Propriedades.getPropriedade(id) instanceof Propriedade) {
			// Para as demais propriedades, como: Início, Prisão, Detenção e Férias, Receita Federal e Loteria.
			Propriedade p = (Propriedade) Propriedades.getPropriedade(id);

			if(p.getId() == 1) {
				// Posicao do inicio, ganha 200k
				Main.write("Inicio: + R$ 200.000,00");
				jogador.setSaldo(jogador.getSaldo() + 200000);
			} else if(p.getId() == 31) {
				// Posicao da prisao, entao para a prisão
				Prisao.entrar(jogador);
			} else if(p.getId() == 11 || p.getId() == 21) {
				// Posicao da detenção e férias, então não acontece nada
				Main.write("--- " + p.getLabel() + " ---");
				return;
			} else if(p.getId() == 25) {
				// Posicao da Receita Federal, o jogador perde 200k
				Main.write("Tok Tok...");
				Main.write("Receita Federal, pague seus impostos: R$ 200.000,00");
				jogador.setSaldo(jogador.getSaldo() - 200000);
			} else if(p.getId() == 38) {
				// Posicao da Loteria, o jogador ganha 200k
				Main.write("Hurruh");
				Main.write("Parabéns! Você ganhou na loteria, receba: R$ 200.000,00");
				jogador.setSaldo(jogador.getSaldo() - 200000);
			}
		}
	}
	
	// Sorteio um dado de seis faces (1 a 6)
	public int jogada() {
		Random r = new Random();
		return r.nextInt(6) + 1;
	}
	
	// Adicionar jogadores, tendo que ser no minimo 2 e no maximo 6
	public void addJogadores() {
		for(int i = 0; i < 3; i++) {
			System.out.print("Jogador " + (i+1) + ": ");
			String in = Main.sc.nextLine();
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
	
	@ Override
	public String toString() {
		String out = "\n";
		for(Jogador j : jogadores) {
			out += j.toString() + "\n";
		}
		
		return out;
	}
}