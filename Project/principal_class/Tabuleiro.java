package principal_class;

import enums.StatusJogador;

import java.util.TreeMap;

public class Tabuleiro {
	private TreeMap<Integer, Jogador> jogadores;
	
	public Tabuleiro() {
		jogadores = new TreeMap<>();
		jogadores.put(0, new Jogador("Banco"));
	}
	
	public void moverJogador(Jogador jogador, int d1, int d2) throws Exception{
		// Pega o numero da jogada e move o jogador, verificando se ele está livre ou não
		if(jogador.getStatus() != StatusJogador.LIVRE) {
			Prisao.jogadaPresa(jogador, d1, d2);
			
			// Caso após fazer a jogada e os dados darem valores iguais, sai da prisão e se locomove
			if(jogador.getStatus() == StatusJogador.LIVRE) {
				jogador.setPosicao(jogador.getPosicao() + (d1 + d2));
			}

			throw new Exception("Ops! Jogador não pode se mover no momento");
		}
		jogador.setPosicao(jogador.getPosicao() + (d1 + d2));
	}
	
	public void prenderJogador(Jogador jogador) {
		// Muda o status do jagador passado para preso
	}
	
	public double hipotecar(Propriedade propriedade) {
		// Muda o status da propriedade passada para hipotecada e recebe metade do valor da propriedade
		// O aluguel fica 0
		return 0f;
	}
	
	public void pagarConta(Jogador jogador) {
		// Paga o valor do aluguel da propriedade para o outro jogador
	}
	
	@ Override
	public String toString() {
		return "";
	}
}
