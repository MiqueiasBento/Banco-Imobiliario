package properties;

import principal_class.*;
import Data.Cartas;
import enums.TipoCarta;

public class CartaEvento extends Propriedade{
	
	public CartaEvento(int id, String label) {
		super(id, label);
		
	}
	
	// Metodos da classe
	public void executarCarta(Jogador jogador) {
		// Puxa uma carta lá do banco de Cartas, executa sua função dependendo do tipo da carta
		Carta carta = Cartas.puxarCarta();
		
		System.out.println(carta);
		
		if(carta.getTipo() == TipoCarta.PERDER) {
			jogador.setSaldo(jogador.getSaldo() - carta.getValue());
		} else if(carta.getTipo() == TipoCarta.GANHAR) {
			jogador.setSaldo(jogador.getSaldo() + carta.getValue());
		} else if(carta.getTipo() == TipoCarta.POSICAO) {
			jogador.setPosicao(carta.getValue());
		}
	}
	
	@ Override
	public String toString() {
		return "";
	}
}
