package Data;

import java.util.*;

import enums.TipoCarta;
import principal_class.Carta;

public class Cartas {
	static LinkedHashMap<Integer, Carta> cartas = new LinkedHashMap<>();
	
	public Cartas() {
		int i = 0;
		// Cartas de sorte
		cartas.put(i++, new Carta(i, "Você foi promovido a Diretor Executivo, receba 200", TipoCarta.GANHAR, 200000, "Ganhar R$ 200.000,00	"));
		cartas.put(i++, new Carta(i, "Você encontrou um tesouro escondido, receba 80", TipoCarta.GANHAR, 80000, "Ganhar R$ 200.000"));
		cartas.put(i++, new Carta(i, "Você ganhou um bônus no trabalho, receba 50", TipoCarta.GANHAR, 200000, "Ganhar R$ 200.000"));
		cartas.put(i++, new Carta(i, "Você encontrou dinheiro na rua, receba 25", TipoCarta.GANHAR, 25000, "Ganhar R$ 25.000"));
		cartas.put(i++, new Carta(i, "Use essa carta para sair da prisão", TipoCarta.GANHAR, 50000, "Ganhar R$ 50.000"));
		cartas.put(i++, new Carta(i, "Você encontrou um bilhete premiado na rua, receba 50", TipoCarta.GANHAR, 50000, "Ganhar R$ 50.000,00"));
		cartas.put(i++, new Carta(i, "Você ganhou na loteria, receba 500", TipoCarta.GANHAR, 500000, "Ganhar R$ 500.000,00"));
		
		// Cartas de revés
		cartas.put(i++, new Carta(i, "Hora de pagar os impostos de renda, pague 75", TipoCarta.PERDER, 75000, "Pague R$ 75.000,00"));
		cartas.put(i++, new Carta(i, "A sorte não está do seu lado, você perdeu na loteria, pague 100.", TipoCarta.PERDER, 100000, "Pague R$ 100.000"));
		cartas.put(i++, new Carta(i, "Você foi multado por excesso de velocidade, pague 20", TipoCarta.PERDER, 20000, "Ganhar R$ 20.000"));
		cartas.put(i++, new Carta(i, "Obra terminada, pague pela reforma de suas propriedades, pague 220", TipoCarta.PERDER, 220000, "Pague R$ 220.000,00"));
		cartas.put(i++, new Carta(i, "Uhnn, você perdeu uma aposta, pague 100", TipoCarta.PERDER, 100000, "Pague R$ 100.000,00"));
		cartas.put(i++, new Carta(i, "Você foi multado por não recolher o lixo, pague 30", TipoCarta.PERDER, 30000, "Ganhar R$ 30.000,00"));
		
		// Cartas de posição
		cartas.put(i++, new Carta(i, "Ops! Vá para a prisão sem passar pelo ponto de partida.", TipoCarta.POSICAO, 31, "Vá para a prisão"));
		cartas.put(i++, new Carta(i, "Ops! Vá para a prisão sem passar pelo ponto de partida.", TipoCarta.POSICAO, 31, "Vá para a prisão"));
		cartas.put(i++, new Carta(i, "Visite a Avenida Paulista", TipoCarta.POSICAO, 27, "Vá para a Avenida Paulista"));
		cartas.put(i++, new Carta(i, "Já viu o quanto produz a Companhia de Mineração? Vá para lá", TipoCarta.POSICAO, 15, "Vá para a Companhia de Mineração"));
		cartas.put(i++, new Carta(i, "Vá para a rua Marina da Glória", TipoCarta.POSICAO, 38, "Vá para a Marina da Glória"));
		cartas.put(i++, new Carta(i, "Tire férias, descansar né que o caba num é de ferro", TipoCarta.POSICAO, 21, "Vá para férias"));
		
		embaralharCartas();
	}
	
	public static Carta puxarCarta() {
		recuperarCartas();
		
		for(Carta c : cartas.values()) {
			if(!c.isUsada()) {
				c.setUsada(true);
				return c;
			}
		}
		return null;
	}
	
	public static void embaralharCartas() {
		// Converte o Map para ArrayList
        List<Carta> listaCartas = new ArrayList<>(cartas.values());
        
        // Embaralha a lista
        Collections.shuffle(listaCartas);
        
        // Adicionando os elementos de volta ao mapa
        cartas.clear();
        for (Carta carta : listaCartas) {
            cartas.put(carta.getId(), carta);
        }
	}
	
	public static void recuperarCartas() {
		boolean todasUsadas = false;
		for(Carta c : cartas.values()) {
			if(c.isUsada()) {
				todasUsadas = true;
			}
		}
		
		if(todasUsadas) {
			for(Carta c : cartas.values()) {
				if(c.isUsada()) {
					c.setUsada(false);
				}
			}
		}
	}
	
	@ Override
	public String toString() {
		String out = "";
		
		for(Carta c : cartas.values()) {
			out += c + "\n\n";
		}
		
		return out;
	}
}