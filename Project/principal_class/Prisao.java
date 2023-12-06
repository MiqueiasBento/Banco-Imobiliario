package principal_class;

import java.util.Scanner;

import enums.ANSI;
import enums.StatusJogador;

public class Prisao {
	public static void entrar(Jogador jogador) {
		System.out.println(ANSI.YELLOW + "+=================================================================+" + ANSI.RESET);
		System.out.println(ANSI.LIGHTYELLOW + "|  Você está preso - passe 3 rodadas na prisão ou pague a fiança  |" + ANSI.RESET);
		System.out.println(ANSI.YELLOW + "+=================================================================+" + ANSI.RESET);
		jogador.setStatus(StatusJogador.PRESO);
		jogador.setEstaLivre(3);
	}
	
	public static void pagarFianca(Jogador jogador) {
		System.out.println("Fiança: R$ 50.000,00 - PAGO\n");
		jogador.setSaldo(jogador.getSaldo() - 50000);
		jogador.setStatus(StatusJogador.LIVRE);
	}
	
	// Se o jogador estiver 
	public static void jogadaPresa(Jogador jogador, int d1, int d2) {
		// Se os dados forem iguais está livre e já se move
		if(d1 == d2) {
			System.out.println("Você está com sorte, alguém pagou sua fiança");
			System.out.println(". . . . . . . VOCÊ ESTÁ LIVRE . . . . . . .\n");
			jogador.setEstaLivre(0);
			jogador.setStatus(StatusJogador.LIVRE);
		} else {
			System.out.print("Pagar fiança? (sim/nao) ");
			String response = sc.nextLine();
			
			if(response.equals("sim")) {
				pagarFianca(jogador);
			} else {
				jogador.setEstaLivre(jogador.isEstaLivre() - 1);
				
				if(jogador.isEstaLivre() == 0) {
					pagarFianca(jogador);
				}
			}
		}
	}
	
	static Scanner sc = new Scanner(System.in);
}
