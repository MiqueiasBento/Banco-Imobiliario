package principal_class;

import enums.StatusJogador;

public class Prisao {
	public static void entrar(Jogador jogador) {
		jogador.setEstaLivre(3);
		
	}
	
	public static void sair(Jogador jogador) {
		
	}
	
	public static void pagarPena(Jogador jogador) {
		
	}
	
	// Se o jogador estiver 
	public static void jogadaPresa(Jogador jogador, int d1, int d2) {
		// Se os dados forem iguais está livre e já se move
		if(d1 == d2) {
			jogador.setEstaLivre(0);
			jogador.setStatus(StatusJogador.LIVRE);
		} else {
			jogador.setEstaLivre(jogador.isEstaLivre() - 1);
		}
	}
}
