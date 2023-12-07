package principal_class;

import Controls.Main;
import enums.ANSI;
import enums.StatusJogador;

public class Prisao{
//	public static final long serialVersionUID = 1L;
	
	public static void entrar(Jogador jogador) {
		System.out.println(ANSI.RED + "+=================================================================+" + ANSI.RESET);
		System.out.println(ANSI.LIGHTRED + "|  Você está preso - passe 3 rodadas na prisão ou pague a fiança  |" + ANSI.RESET);
		System.out.println(ANSI.RED + "+=================================================================+" + ANSI.RESET);
		jogador.setStatus(StatusJogador.PRESO);
		jogador.setEstaLivre(3);
	}
	
	public static void pagarFianca(Jogador jogador) {
		System.out.println(ANSI.LIGHTGREEN + " -- Fiança: R$ 50.000,00 - PAGO -- \n" + ANSI.RESET);
		jogador.setSaldo(jogador.getSaldo() - 50000);
		jogador.setStatus(StatusJogador.LIVRE);
	}
	
	// Se o jogador estiver 
	public static void jogadaPresa(Jogador jogador, int d1, int d2) {
		// Se os dados forem iguais está livre e já se move
		if(d1 == d2) {
			Main.write("Você está com sorte, alguém pagou sua fiança");
			Main.write(". . . . . . . VOCÊ ESTÁ LIVRE . . . . . . .\n");
			jogador.setEstaLivre(0);
			jogador.setStatus(StatusJogador.LIVRE);
		} else {
			System.out.print(ANSI.BOLD + "\nVOCÊ ESTÁ PRESO - Pagar fiança? (Y / n) " + ANSI.RESET);
			String response = Main.input();
			
			if(response.equals("y") || response.contains("s")) {
				pagarFianca(jogador);
			} else {
				jogador.setEstaLivre(jogador.isEstaLivre() - 1);
				
				if(jogador.isEstaLivre() == 0) {
					pagarFianca(jogador);
				}
			}
		}
	}
}
