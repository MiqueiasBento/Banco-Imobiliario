package Controls;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

import enums.ANSI;
import principal_class.*;

public class Main {
	public static void main(String args[]) {
		BancoImobiliario BI = new BancoImobiliario();
		
		// Adiciona de 2 a 6 players
		BI.chamaAddPlayers();;

		// Looping até ter um vencedor
		while (!BI.winner()) {

			// Jogador por jogador faz um jogada
			for (Jogador jog : BI.jogAtivos()) {

				// Pode administrar e negociar quantas vezes quiser, mas só passa para o próximo
				// player quando jogar os dados
				write(ANSI.LIGHTGREEN + "__________________________________________________________________________" + ANSI.RESET);
				write("PLAYER: " + ANSI.LIGHTGREEN + jog.getNome() + ANSI.RESET);

				String in;
				while (true) {
					opcoes();
					print("-> ");
					in = input();

					if (in.contains("1")) {
						// Faz a jogada com os dados
						BI.jogar(jog);
						
						if(BI.repete()) {
							write(ANSI.LIGHTGREEN + "\nJOGUE MAIS UMA VEZ" + ANSI.RESET);
							continue;
						} else{
							break;
						}
					} else if (in.contains("2")) {
						// Chama a função para addministrar as propriedades
						BI.administracao(jog, "ADMINISTRAR");
					} else if (in.contains("3")) {
						// Chama função para negociar com o outro proprietário
						BI.administracao(jog, "NEGOCIAR");
					} else if (in.contains("4")) {
						// Chama função para hipotecar um de suas propriedades
						BI.administracao(jog, "HIPOTECAR");
					} else if (in.contains("5")) {
						// Mostra o status do jogador atual
						write(jog);
					} else {
						print(ANSI.LIGHTYELLOW + "Tente novamente" + ANSI.RESET);
					}
					write(ANSI.LIGHTGREEN + "\n--------------------------------------------------------------------------" + ANSI.RESET);
					
					sc.nextLine();		// Limpar o buffer
					
					if (BI.winner()) {
						return;
					}
				}

			}
			// Mostra como estão as posições no tabuleiro
			// write(BI);
		}
	}

	public static Scanner sc = new Scanner(System.in);

	public static void write(Object value) {
		System.out.println(value);
	}

	public static void print(Object value) {
		System.out.print(value);
	}

	public static String input() {
		return sc.nextLine();
	}

	public static int number(String value) {
		return Integer.parseInt(value);
	}

	public static String format(double numero) {
		Locale ptBr = new Locale("pt", "BR");
		return NumberFormat.getNumberInstance(ptBr).format(numero) + ",00";
	}

	public static void opcoes() {
		write("===========================+");
		write("1 - JOGAR DADOS            |");
		write("2 - ADMINISTRAR            |");
		write("3 - NEGOCIAR               |");
		write("4 - HIPOTECAR              |");
		write("5 - VER STATUS             |");
		write("===========================+");
	}
}