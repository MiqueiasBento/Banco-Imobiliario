package principal_class;

import Controls.Main;

import java.util.ArrayList;
import java.util.Random;

import properties.*;
import Data.Propriedades;
import enums.ANSI;
import enums.StatusJogador;

public class BancoImobiliario {
	private Jogador winner;
	private Tabuleiro board;
	private int rDados;
	
	public BancoImobiliario() {
		this.winner = null;
		this.board = new Tabuleiro();
		this.rDados = 0;
	}

	// Executa as principais funções do jogo
	public void jogar(Jogador jogador) {
		int d1 = jogada();
		int d2 = jogada();
//		int d1 = 3;
//		int d2 = 3;
		
		mostrarDados(d1, d2);
		
		try {
			board.moverJogador(jogador, d1, d2);
			verificarPosicao(jogador, (d1 + d2));
			verificarJogador(jogador);
		} catch (Exception e) {
			Main.write(e.getMessage());
		}

		// Se tiver um vencedor do jogo
		if (winner()) {
			Main.write("Vencedor: \n" + winner);
			return;
		}
	}
	
	// Executa as ações de administração, o negaciar e administrar (para comprar imóveis)
	public void administracao(Jogador jogador, String acao) {
		// Ficará em looping até realizar a acao, caso o player erre tentará novamente
		while(true) {
			// Tenta realizar a ação, se passar uma informação errada recebe exceção e tenta de novo
			try {
				if(acao.equals("HIPOTECAR")) {
					board.hipotecar(jogador);
					verificarJogador(jogador);
					return;
				}
				else if(acao.equals("NEGOCIAR")) {
					Main.write("\n===========================+");
					Main.write("0 - CANCELAR               |");
					Main.write("1 - VENDER                 |");
					Main.write("2 - COMPRAR                |");
					Main.write("===========================+");
					Main.print("-> ");
					int response = Main.sc.nextInt();
					
					if(response == 1) 		board.negociarVenda(jogador);
					else if(response == 2)	board.negociarCompra(jogador);
					else return;
					
					verificarJogador(jogador);
					return;
				}
				else if(acao.equals("ADMINISTRAR")) {
					board.comprarImoveis(jogador);
					verificarJogador(jogador);
					return;
				}
			} catch(Exception e) {
				Main.write("\n" + ANSI.RED + e.getMessage() + ANSI.RESET + "\n");
			}
		}
	}
	
	// Verfica se tem somente um player não falido, para assim a jogo acabar
	public boolean winner() {
		int playersLose = 0;
		
		// Conta players falidos, que já perderam o jogo
		for (Jogador j : board.getJogadores()) {
			if (j.getStatus() == StatusJogador.FALIDO) {
				playersLose++;
			}
		}
		
		// Se a quantidade de players falidos estiver faltando um para o total, entao temos um vencedor
		if (playersLose == board.getJogadores().size() - 1) {
			for (Jogador j : board.getJogadores()) {
				if (j.getStatus() != StatusJogador.FALIDO) {
					winner = j;
					Main.write(j.getNome().toUpperCase() + " VENCEU !!!!!!!!");
					return true;
				}
			}
		}
		
		return false;
	}
	
	// Irá fazer toda uma verificação da atual posição do player, se o local já está alocado, se precisa pagar aluguel e etc
	public void verificarPosicao(Jogador jogador, int n)  throws Exception{
		int id = jogador.getPosicao();
		
		// Se for uma Rua, pode ser comprável ou deve pagar o aluguel
		if (Propriedades.getPropriedade(id) instanceof Rua) {
			Rua r = (Rua) Propriedades.getPropriedade(id);

			if (r.getProprietario() == null) {
				// Se estiver sem proprietario, pode ser comprada pelo jogador que está na posicao
				Main.write("Saldo: R$ " + Main.format(jogador.getSaldo()));
				Main.write(r.getPropriedade());
				Main.print("\nDeseja comprar? (sim / nao) ");
				String response = Main.input();

				if (response.equals("sim")) {
					if(jogador.getSaldo() - r.getValorPropriedade() < 0) throw new Exception("Saldo insuficiente");
					
					jogador.comprarPropriedade(r);
				} else {
					return;
				}
			} else {
				// Chama metodo para pagar aluguel
				board.pagarAluguel(jogador, r, n);
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
				Main.write("Saldo: R$ " + Main.format(jogador.getSaldo()));
				Main.write(c.getPropriedade());
				Main.print("\nDeseja comprar? (sim / nao) ");

				String response = Main.input();
				if (response.equals("sim")) {
					if (response.equals("sim")) {
						if(jogador.getSaldo() - c.getValorPropriedade() < 0) throw new Exception("Saldo insuficiente");
						
						jogador.comprarPropriedade(c);
					}
				} else {
					return;
				}
			} else {
				// Chama metodo para pagar aluguel
				board.pagarAluguel(jogador, c, n);
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
	
	// Retorna os jogadores nao falidos
	public ArrayList<Jogador> jogAtivos(){
		ArrayList<Jogador> list = new ArrayList<>();
		
		for(Jogador j : board.getJogadores()) {
			if(j.getStatus() != StatusJogador.FALIDO) {
				list.add(j);
			}
		}
		return list;
	}
	
	// Para verificar se o jogador está com saldo negativo e não permití-lo continuar enquanto isso
	public void verificarJogador(Jogador jogador) {
		// Se a repetição de dados for igual a 3, vai para a prisao
		if(rDados == 3) {
			Prisao.entrar(jogador);
			rDados = 0;
		}
		
		if(jogador.getSaldo() >= 0) {
			return;
		}
		
		Main.write(ANSI.RED + "\n- - - SEU SALDO ESTÁ NEGATIVO - - -" + ANSI.RESET);
		while(jogador.getSaldo() < 0) {
			Main.write("==================================+");
			Main.write("1 - NEGOCIAR                      |");
			Main.write("2 - HIPOTECAR                     |");
			Main.write("3 - DESISTIR                      |");
			Main.write("==================================+");
			Main.print("-> ");
			String response = Main.sc.nextLine();
			
			if(response.contains("1")) {
				this.administracao(jogador, "NEGOCIAR");
			} else if(response.contains("2")) {
				this.administracao(jogador, "HIPOTECAR");
			} else if(response.contains("3")) {
				board.desistir(jogador);
				return;
			}
		}
		
	}
	
	// Sorteio um dado de seis faces (1 a 6)
	public int jogada() {
		Random r = new Random();
		return r.nextInt(6) + 1;
	}
	
	public void mostrarDados(int valor1, int valor2) {
        System.out.println(ANSI.YELLOW + "+-----+   +-----+");
        for (int i = 1; i <= 3; i++) {
            if (i == 2) {
                System.out.println(ANSI.YELLOW + "|  " + ANSI.LIGHTYELLOW + valor1 + ANSI.YELLOW + "  |   |  " + ANSI.LIGHTYELLOW + valor2 + ANSI.YELLOW + "  |");
            } else {
                System.out.println(ANSI.YELLOW + "|     |   |     |" + ANSI.RESET);
            }
        }
        System.out.println(ANSI.YELLOW + "+-----+   +-----+" + ANSI.RESET);
        
        if(valor1 == valor2)	rDados++;
        else 					rDados = 0;
	}        

	public boolean repete() {
		if(rDados > 0) 	return true;
		else 			return false;
	}
	
	public void chamaAddPlayers() {
		board.addJogadores();
	}
}