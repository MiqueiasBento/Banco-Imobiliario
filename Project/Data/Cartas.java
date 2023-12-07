package Data;

import java.util.*;

import enums.TipoCarta;
import principal_class.Carta;

public class Cartas {
	static LinkedHashMap<Integer, Carta> cartas = new LinkedHashMap<>();
	
	static {
		int i = 0;
		// Cartas de sorte
		cartas.put(i++, new Carta(i, "Você foi promovido a Diretor Executivo, receba R$ 200.000,00", TipoCarta.GANHAR, 200000));
		cartas.put(i++, new Carta(i, "Você encontrou um tesouro escondido, receba R$ 80.000,00", TipoCarta.GANHAR, 80000));
		cartas.put(i++, new Carta(i, "Você ganhou um bônus no trabalho, receba R$ 50.000,00", TipoCarta.GANHAR, 200000));
		cartas.put(i++, new Carta(i, "Você encontrou dinheiro na rua, receba R$ 25.000,00", TipoCarta.GANHAR, 25000));
		cartas.put(i++, new Carta(i, "Use essa carta para sair da prisão", TipoCarta.GANHAR, 50000));
		cartas.put(i++, new Carta(i, "Você encontrou um bilhete premiado na rua, receba R$ 50.000,00", TipoCarta.GANHAR, 50000));
		cartas.put(i++, new Carta(i, "Você ganhou na loteria, receba R$ 500.000,00", TipoCarta.GANHAR, 500000));
		
		// Cartas de revés
		cartas.put(i++, new Carta(i, "Hora de pagar os impostos de renda, pague R$ 75.000,00", TipoCarta.PERDER, 75000));
		cartas.put(i++, new Carta(i, "A sorte não está do seu lado, você perdeu na loteria, pague R$ 100.000,00.", TipoCarta.PERDER, 100000));
		cartas.put(i++, new Carta(i, "Você foi multado por excesso de velocidade, pague R$ 20.000,00", TipoCarta.PERDER, 20000));
		cartas.put(i++, new Carta(i, "Obra terminada, pague pela reforma de suas propriedades, pague R$ 220.000,00", TipoCarta.PERDER, 220000));
		cartas.put(i++, new Carta(i, "Uhnn, você perdeu uma aposta, pague R$ 100.000,00", TipoCarta.PERDER, 100000));
		cartas.put(i++, new Carta(i, "Você foi multado por não recolher o lixo, pague R$ 30.000,00", TipoCarta.PERDER, 30000));
		
		// Cartas de posição
		cartas.put(i++, new Carta(i, "Ops! Vá para a prisão sem passar pelo ponto de partida.", TipoCarta.POSICAO, 30));
		cartas.put(i++, new Carta(i, "Ops! Vá para a prisão sem passar pelo ponto de partida.", TipoCarta.POSICAO, 30));
		cartas.put(i++, new Carta(i, "Visite a Avenida Paulista", TipoCarta.POSICAO, 26));
		cartas.put(i++, new Carta(i, "Já viu o quanto produz a Companhia de Mineração? Venha visitá-la", TipoCarta.POSICAO, 14));
		cartas.put(i++, new Carta(i, "Vá para a rua Marina da Glória", TipoCarta.POSICAO, 37));
		cartas.put(i++, new Carta(i, "Que tal tirar umas férias, descansar né que o caba num é de ferro", TipoCarta.POSICAO, 20));
		
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
	
	// Verifica se todas a cartas foram usadas, e depois remarca-as como não usadas e todas tiverem sido usadas
	public static void recuperarCartas() {
	    boolean todasUsadas = cartas.values().stream().allMatch(Carta::isUsada);
	    
	    if(todasUsadas) {
	        cartas.values().forEach(carta -> carta.setUsada(false));
	    }
	}

	
//	@ Override
//	public String toString() {
//		String out = "";
//		
//		for(Carta c : cartas.values()) {
//			out += c + "\n\n";
//		}
//		
//		return out;
//	}
}