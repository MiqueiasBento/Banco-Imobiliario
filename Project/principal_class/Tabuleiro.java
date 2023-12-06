package principal_class;

import Controls.Main;
import Data.Propriedades;
import enums.ANSI;
import enums.PropriedadeAlocavel;
import enums.StatusJogador;
import enums.StatusPropriedade;
import properties.*;

import java.util.ArrayList;

public class Tabuleiro {
	private ArrayList<Jogador> jogadores;
	
	public Tabuleiro(){
		this.jogadores = new ArrayList<>();
		
//		this.jogadores.add(new Jogador("Jane"));
//		this.jogadores.add(new Jogador("Maicon"));
	}
	
	public void moverJogador(Jogador jogador, int d1, int d2) throws Exception {
		// Pega o numero da jogada e move o jogador, verificando se ele está livre ou
		// não
		if (jogador.getStatus() == StatusJogador.PRESO) {
			Prisao.jogadaPresa(jogador, d1, d2);

			// Caso tenha feito uma jogada com os valores iguais ou pago a fiança, sai da
			// prisão e se locomove
			if (jogador.getStatus() == StatusJogador.LIVRE) {
				// Se o player estiver antes da posição Inicio, e na jogada cair em cima ou
				// depois, ganhará mais 200k
				passarInicio(jogador, (d1 + d2));
				jogador.setPosicao((jogador.getPosicao() + (d1 + d2)) % 40);
			}
			if (jogador.getStatus() == StatusJogador.PRESO) {
				throw new Exception("Ops! Jogador não pode se mover no momento");
			}
		} else {
			// Move o jogador, o mod 40 serve para nunca ultrapassar a posição com valor 40,
			// que não possui no tabuleiro
			// Se o player estiver antes da posição Inicio, e na jogada cair em cima ou
			// depois, ganhará mais 200k
			passarInicio(jogador, (d1 + d2));
			jogador.setPosicao((jogador.getPosicao() + (d1 + d2)) % 40);
		}
	}

	// Metodo para hipotecar as propriedades do jogador
	public void hipotecar(Jogador jogador) throws Exception {
		// Muda o status da propriedade passada para hipotecada e recebe metade do valor
		// da propriedade
		// O aluguel fica 0

		// Se o jogador nao possuir nenhuma propriedade, nao poderá hipotecar e o metodo se encerra por aqui
		if (jogador.quantPropriedade() == 0) {
			Main.write("\n" + ANSI.RED + " -- SEM PROPRIEDADES PARA HIPOTECAR NO MOMENTO -- " + ANSI.RESET + "\n");
			return;
		}

		Main.write("-- DIGITE 0 PARA CANCELAR --");
		jogador.getPropriedades();

		Main.print(ANSI.LIGHTWHITE + "- ID DA PROPRIEDADE: " + ANSI.RESET);
		int id = Main.sc.nextInt();

		if (id == 0) {
			Main.write("");
			Main.write(ANSI.YELLOW + "OPERAÇÃO CANCELADA" + ANSI.RESET);
			return;
		}

		// Verifica se a propriedade é mesmo do jogador
		if (jogador.getPropriedade(id) == null) {
			throw new Exception("TENTE NOVAMENTE: PROPRIEDADE ESCOLHIDA NÃO É SUA");
		}
		
		PropriedadeAlocavel pa = (PropriedadeAlocavel) jogador.getPropriedade(id);
		if(pa.getStatus() == StatusPropriedade.HIPOTECADA) {
			Main.write("DESEJA DESIPOTECAR?");
			Main.write("1 - SIM");
			Main.write("2 - NAO");
			Main.print("-> ");
			int response = Main.sc.nextInt();
			
			if(response == 2) {
				return;
			} else {
				if(jogador.getSaldo() - (pa.getValorPropriedade() / 2) < 0) {
					throw new Exception("OPS! SALDO INSUFICIENTE PARA DESIPOTECAR PROPRIEDADE");
				}
				
				pa.setStatus(StatusPropriedade.ALOCADA);
				jogador.setSaldo(jogador.getSaldo() - (pa.getValorPropriedade() / 2));
				
				Main.write(ANSI.LIGHTGREEN + "\n* * * DESIPOTECADA * * *" + ANSI.RESET);
			}
		} else {
			Main.write("DESEJA DESIPOTECAR?");
			Main.write("1 - SIM");
			Main.write("2 - NAO");
			Main.print("-> ");
			int response = Main.sc.nextInt();
			
			if(response == 2) {
				return;
			} else {
				pa.setStatus(StatusPropriedade.HIPOTECADA);
				jogador.setSaldo(jogador.getSaldo() + (pa.getValorPropriedade() / 2));
				
				Main.write(ANSI.LIGHTGREEN + "\n* * * HIPOTECADA -> +R$ " + Main.format((pa.getValorPropriedade() / 2)) + " * * *" + ANSI.RESET);
			}
		}
	}

	// Metodo para comprar propriedade de outros players
	public void negociarCompra(Jogador jogador) throws Exception {
		// Tenta negociar sua propriedade com outro jogador, se aceitar o negociante será proprietário dessa propriedade
		// Se a propriedade possuir imóveis, será do negociante também

		// Pega todas as propriedades alocaveis que nao possui proprietario e que o proprietario nao é o negociador
		ArrayList<PropriedadeAlocavel> propCompraveis = Propriedades.getPropriedadesNaoJogador(jogador);

		// Se nao possui propriedades negociaveis no momento, encerra-se por aqui
		if (propCompraveis.size() == 0) {
			Main.write(ANSI.RED + "\nSEM PROPRIEDADES PARA NEGOCIAR NO MOMENTO\n" + ANSI.RESET);
			return;
		}

		// Id 0 para cancelar, e mostra as propriedades que podem ser negociaveis no momento
		Main.write("+=============================================================+");
		Main.write(ANSI.RED + "--                   Digite 0 para cancelar                  --" + ANSI.RESET);
		Main.write(ANSI.YELLOW + "--                       PROPRIEDADES:                       --" + ANSI.RESET);
		for (PropriedadeAlocavel p : propCompraveis) {
			Main.write(p.previaPropriedade());
		}
		Main.write("+=============================================================+");
		
		Main.write("");
		Main.print(ANSI.LIGHTWHITE + "- ID DA PROPRIEDADE: " + ANSI.RESET);
		int id = Main.sc.nextInt();

		if (id == 0) {
			Main.write(ANSI.YELLOW + "\nOPERAÇÃO CANCELADA" + ANSI.RESET);
			return;
		}

		// Pega a propriedade escolhida pelo negociador
		PropriedadeAlocavel pn = (PropriedadeAlocavel) Propriedades.getPropriedade(id - 1);

		// Realizao da operação final
		if (pn == null) {
			throw new Exception("TENTE NOVAMENTE: A PROPRIEDADE ESCOLHIDA NÃO É NOGOCIÁVEL NO MOMENTO");
		} else {
			Jogador proprietario = pn.getProprietario();	// Proprietário da propriedade escolhida
			
			Main.print("\nPROPRIETÁRIO: " + ANSI.GREEN + proprietario.getNome() + ANSI.RESET);
			Main.write("\n" + pn.previaPropriedade());

			Main.print(ANSI.BOLD + "\n- VALOR OFERTADO: R$ " + ANSI.RESET);
			double value = Main.sc.nextDouble();

			Main.sc.nextLine();
			Main.print("- " + ANSI.GREEN + proprietario.getNome() + ANSI.RESET + ", aceita a proposta? (sim / nao) ");
			String response = Main.sc.nextLine();

			if (response.equals("nao")) {
				Main.write(ANSI.RED + "\n - - - NEGOCIAÇÃO RECUSADA! - - -" + ANSI.RESET);
				return;
			} else {
				Main.write(ANSI.LIGHTBLUE + "\n + + + NEGOCIAÇÃO ACEITA! + + + " + ANSI.RESET);
				proprietario.rmPropriedade(pn);
				proprietario.setSaldo(proprietario.getSaldo() + value);

				jogador.addPropriedade(pn);
				jogador.setSaldo(jogador.getSaldo() - value);
				pn.setProprietario(jogador);
				return;
			}
		}
	}
	
	// Metodo para venda de propriedades do jogador
	public void negociarVenda(Jogador jogador) throws Exception{
		// Verifica se há propriedades para vender
		var pvs = Propriedades.getPropriedadesJogador(jogador);
		
		// Verifica se tem propriedades para comprar no momento
		if (pvs.size() == 0) {
			Main.write(ANSI.RED + "\nSEM PROPRIEDADES PARA COMPRAR NO MOMENTO\n" + ANSI.RESET);
			return;
		}
		
		Main.write("+=============================================================+");
		Main.write(ANSI.RED + "--                   Digite 0 para cancelar                  --" + ANSI.RESET);
		Main.write(ANSI.YELLOW + "--                  SUAS PROPRIEDADES:                       --" + ANSI.RESET);
		jogador.getPropriedades();
		Main.write("+=============================================================+");
		
		Main.write("");
		Main.print(ANSI.LIGHTWHITE + "- ID DA PROPRIEDADE: " + ANSI.RESET);
		int id = Main.sc.nextInt();

		if (id == 0) {
			Main.write(ANSI.YELLOW + "\nOPERAÇÃO CANCELADA" + ANSI.RESET);
			return;
		}

		// Verifica se a propriedade é mesmo do jogador
		if (Propriedades.getPropriedade(id - 1) == null) {
			throw new Exception("TENTE NOVAMENTE: ESSA PROPRIEDADE NÃO É SUA");
		}
		
		// Mostra os jogadores no jogo que ainda podem comprar esse propriedade
		Main.write("\n-- -- -- -- -- -- -- -- -- + ");
		Main.write(ANSI.LIGHTBLUE + "JOGADORES: " + ANSI.RESET);
		for(int i = 0; i < jogadores.size(); i++) {
			if(jogadores.get(i).getStatus() != StatusJogador.FALIDO && jogadores.get(i) != jogador){
				Main.write(i + ": " + jogadores.get(i).getNome());
			}
		}
		Main.write("-- -- -- -- -- -- -- -- -- + ");
		
		Main.write("");
		Main.print("- ID DO JOGADOR: ");
		int idx = Main.sc.nextInt();
		Main.print("- VALOR DA PROPOSTA: R$ ");
		double value = Main.sc.nextDouble();
		Main.sc.nextLine();		// Limpar o buffer
		
		Main.print("\n-> " + ANSI.GREEN + jogadores.get(idx).getNome() + ANSI.RESET + ", ACEITA A PROPOSTA? (sim / nao) ");
		String response = Main.sc.nextLine();
		
		if (response.equals("nao")) {
			Main.write(ANSI.RED + "\n - - - NEGOCIAÇÃO RECUSADA! - - -" + ANSI.RESET);
			return;
		} else {
			Main.write(ANSI.BLUE + "\n + + + NEGOCIAÇÃO ACEITA! + + + " + ANSI.RESET);
			
			Jogador proprietario = jogadores.get(idx);
			PropriedadeAlocavel p = (PropriedadeAlocavel) jogador.getPropriedade(id);
			
			jogador.rmPropriedade(p);
			jogador.setSaldo(jogador.getSaldo() + value);

			proprietario.addPropriedade(p);
			proprietario.setSaldo(proprietario.getSaldo() - value);
			p.setProprietario(proprietario);
			return;
		}
	}
	
	// Metodo para compra de imóveis das propriedades do jogador
	public void comprarImoveis(Jogador jogador) throws Exception{
		// Se o jogador nao possuir nenhuma propriedade, nao poderá gerenciar e o metodo se encerra por aqui

		Main.write("-- Digite 0 para cancelar --");
		Main.write("--   SUAS PROPRIEDADES    --");
		jogador.getPropriedades();

		Main.print("- ID DA PROPRIEDADE: ");
		int id = Main.sc.nextInt();

		if (id == 0) {
			Main.write("\nOperação cancelada");
			return;
		}
		if (jogador.getPropriedade(id) == null) {
			// Verifica se o proprietario e o jogador realmente
			throw new Exception("Tente novamente: Essa propriedade não é sua");
		}
		
		if (jogador.getPropriedade(id) instanceof Companhia) {
			// Verifica se a propriedade escolhida é uma companhia, que nao pode possuir imóveis
			throw new Exception("Tente novamente: Companhias não possuem imóveis");
		}
		
		// Verifica se possui todas a propriedades da mesma cor
		Rua rua = (Rua) Propriedades.getPropriedade(id);
		ArrayList<Rua> condmn = Propriedades.propsTipo(rua.getCor());
		for(Rua r : condmn) {
			if(r.getProprietario() != jogador) {
				throw new Exception("Ops! você nao possui todas as propriedades da cor: " + rua.getCor());
			}
		}
		
		// Compra repetidamente de imóveis
		while(true) {
			Main.write("-- -- -- -- -- -- -- -- --");
			Main.write("Propriedades: " + rua.getCor());
			Main.write("0 0: CANCELAR");
			for(int i = 0; i < condmn.size(); i++) {
				Main.write(String.format("%02d", (i+1)) + ": " 
						+ condmn.get(i).getLabel() 
						+ " - " + (condmn.get(i).getQuantImoveis() <= 4 ?condmn.get(i).getQuantImoveis() + " imoveis" : "1 hotel"));
			}
			Main.print("- ID e N de Imóveis: (id, quant) ");
			int i = Main.sc.nextInt();
			int n = Main.sc.nextInt();
			
			if(i == 0) return;
			
			try {
				jogador.construirCasa(condmn.get((i-1)), n);
			} catch(Exception e) {
				Main.write(e.getMessage());
				return;
			}
		}
	}

	
	// Para verificar se o player passou pela casa Início e receber 200k
	public void passarInicio(Jogador jogador, int dados) {
		if ((jogador.getPosicao() < 39 && jogador.getPosicao() >= 29)
				&& ((((jogador.getPosicao() + dados) % 40) == 0) || ((jogador.getPosicao() + dados) % 40) > 0)) {
			jogador.setSaldo(jogador.getSaldo() + 200000);
		}
	}

	// Metodo para controle do pagamento do aluguel
	public void pagarAluguel(Jogador jogador, PropriedadeAlocavel pa, int n) {
		// O proprietario nao paga seu proprio aluguel
		if (pa.getProprietario() == jogador) {
			Main.write(pa.previaPropriedade());
			return;
		}
		// Paga o valor do aluguel da propriedade, independente se tem saldo ou nao
		if (pa instanceof Rua) {
			Rua r = (Rua) pa;

			// Propriedades hipotecadas nao recebem por aluguel
			if (r.getStatus() == StatusPropriedade.HIPOTECADA) {
				Main.write(pa.previaPropriedade());
				return;
			}

			Main.write(r.getLabel() + " - " + r.getProprietario().getNome());
			Main.write("Valor do aluguel: R$ " + Main.format(r.valorAluguel()));

			jogador.setSaldo(jogador.getSaldo() - r.valorAluguel()); // Saldo descontado no saldo do jogador da vez
			r.getProprietario().setSaldo(pa.getProprietario().getSaldo() + r.valorAluguel()); // Saldo acrescentado ao
																								// proprietário
		} else {
			// Propriedades hipotecadas nao recebem por aluguel
			Companhia c = (Companhia) pa;

			if (c.getStatus() == StatusPropriedade.HIPOTECADA) {
				Main.write(pa.previaPropriedade());
				return;
			}

			Main.write(c.getLabel() + " - " + c.getProprietario().getNome());
			Main.write("Valor do aluguel: R$ " + Main.format(c.valorAluguel(n)));

			jogador.setSaldo(jogador.getSaldo() - c.valorAluguel(n)); // Saldo descontado no saldo do jogador da vez
			c.getProprietario().setSaldo(pa.getProprietario().getSaldo() + c.valorAluguel(n)); // Saldo acrescentado ao
																								// proprietário
		}
	}

	// Retorna todos os players do jogo
	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}
	
	// Adicionar jogadores, tendo que ser no minimo 2 e no maximo 6
	public void addJogadores() {
		Main.write(ANSI.LIGHTYELLOW + "+===== ADICIONAR JOGADORES (2 - 6) =====+" + ANSI.RESET);
		Main.write(ANSI.LIGHTYELLOW + "---    digite 'ok' para finalizar    --- " + ANSI.RESET);
		for(int i = 0; i < 6; i++) {
			Main.print("JOGADOR " + (jogadores.size() + 1) + ": ");
			String in = Main.sc.nextLine();
			
			if(in.contains("ok") && jogadores.size() < 2) {
				Main.write(ANSI.LIGHTYELLOW + "+=====   ADICIONE MAIS JOGADORES   =====+" + ANSI.RESET);
				i--;
			} else if(in.contains("ok")  && jogadores.size() >= 2) {
				Main.write(ANSI.LIGHTYELLOW + "+=======================================+" + ANSI.RESET);
				return;
			}
			
			if(!in.contains("ok"))
				jogadores.add(new Jogador(in));
		}
		Main.write(ANSI.LIGHTYELLOW + "+=======================================+" + ANSI.RESET);
	}
	
	public void desistir(Jogador jogador) {
		Main.write(ANSI.PURPLE + "$$$ " + jogador.getNome() + " FALIU $$$" + ANSI.RESET);
		jogador.setStatus(StatusJogador.FALIDO);
	}

}