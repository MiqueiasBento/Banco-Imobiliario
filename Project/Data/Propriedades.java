package Data;

import java.util.*;

import enums.PropriedadeAlocavel;
import enums.StatusPropriedade;
import enums.corProp;
import principal_class.*;
import properties.*;

public class Propriedades {
	private static TreeMap<Integer, Propriedade> propriedades = new TreeMap<>();
	
	static {
		int i = 0; 
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Rua(i, "Jardim Botânico", corProp.CAPIM, 70000, 50000, 6000));
		propriedades.put(i++, new Rua(i, "Av. Niemeyer", corProp.CAPIM, 75000, 50000, 4000));
		propriedades.put(i++, new Companhia(i, "Companhia de força e luz", 200000, 50000));
		propriedades.put(i++, new Rua(i, "Av. Beira Mar", corProp.CAPIM, 75000, 50000, 2000));
		propriedades.put(i++, new CartaEvento(i, "Revés/Sorte"));
		propriedades.put(i++, new Rua(i, "Av. Jucelino Kubischek", corProp.VERMELHA, 240000, 150000, 20000));;
		propriedades.put(i++, new Rua(i, "Rua Oscar Freire", corProp.VERMELHA, 220000, 150000, 18000));
		propriedades.put(i++, new Companhia(i, "Pontocom", 150000, 40000));
		propriedades.put(i++, new Rua(i, "Av. Ibirabuera", corProp.VERMELHA, 220000, 150000, 18000));
		propriedades.put(i++, new Propriedade(i, "Detenção"));
		propriedades.put(i++, new CartaEvento(i, "Revés/Sorte"));
		propriedades.put(i++, new Rua(i, "Praça da Sé", corProp.AZUL, 200000, 100000, 16000));
		propriedades.put(i++, new Rua(i, "Viaduto do Chá", corProp.AZUL, 180000, 100000, 14000));
		propriedades.put(i++, new Companhia(i, "Companhia de mineração", 200000, 50000));
		propriedades.put(i++, new Rua(i, "Rua da Consolação", corProp.AZUL, 180000, 100000, 14000));
		propriedades.put(i++, new CartaEvento(i, "Revés/Sorte"));
		propriedades.put(i++, new Rua(i, "Higienôpolis", corProp.ROSA, 400000, 200000, 70000));
		propriedades.put(i++, new Rua(i, "Jardins", corProp.ROSA, 350000, 200000, 60000));
		propriedades.put(i++, new Companhia(i, "Crédito de carbono", 150000, 40000));
		propriedades.put(i++, new Propriedade(i, "Férias"));
		propriedades.put(i++, new CartaEvento(i, "Revés/Sorte"));
		propriedades.put(i++, new Rua(i, "São João", corProp.ROXA, 120000, 50000, 6000));
		propriedades.put(i++, new Rua(i, "Av. Ipiranca", corProp.ROXA, 100000, 50000, 8000));
		propriedades.put(i++, new Propriedade(i, "Receita Federal"));
		propriedades.put(i++, new Rua(i, "Ponta da Guaiuba", corProp.VERDE, 140000, 100000, 10000));
		propriedades.put(i++, new Rua(i, "Av. Paulista", corProp.VERDE, 160000, 100000, 12000));
		propriedades.put(i++, new Companhia(i, "Companhia petrolífera", 200000, 50000));
		propriedades.put(i++, new Rua(i, "Av. Recife", corProp.VERDE, 140000, 100000, 10000));
		propriedades.put(i++, new CartaEvento(i, "Revés/Sorte"));
		propriedades.put(i++, new Propriedade(i, "Prisão"));
		propriedades.put(i++, new Rua(i, "Praça dos Três Poderes", corProp.LARANJA, 320000, 200000, 28000));
		propriedades.put(i++, new CartaEvento(i, "Revés/Sorte"));
		propriedades.put(i++, new Rua(i, "Praça Castro Alvares", corProp.LARANJA, 300000, 200000, 26000));
		propriedades.put(i++, new Rua(i, "Av. do Contorno", corProp.LARANJA, 300000, 200000, 26000));
		propriedades.put(i++, new Companhia(i, "Companhia de Água e Saneamento", 200000, 50000));
		propriedades.put(i++, new Rua(i, "Ponte Rio Niterói", corProp.AMARELA, 280000, 150000, 26000));
		propriedades.put(i++, new Rua(i, "Marina da Glória", corProp.AMARELA, 260000, 150000, 22000));
		propriedades.put(i++, new Propriedade(i, "Loteria"));
		propriedades.put(i++, new Rua(i, "Barra da Tijuca", corProp.AMARELA, 260000, 50000, 22000));
	}
	
	public static Propriedade getPropriedade(int id) {
		return propriedades.get(id);
	}
	
	public static ArrayList<PropriedadeAlocavel> getPropriedadesJaAlocadas() {
		var propriedades = getPropriedades();
		ArrayList<PropriedadeAlocavel> propriedadesJaAlocadas = new ArrayList<>();
		for(var a : propriedades) {
			if(a.getProprietario() != null) propriedadesJaAlocadas.add(a);
		}
		return propriedadesJaAlocadas;
	}
	
	public static ArrayList<PropriedadeAlocavel> getPropriedadesNaoAlocadas() {
		var propriedades = getPropriedades();
		ArrayList<PropriedadeAlocavel> propriedadesNaoAlocadas = new ArrayList<>();
		for(var a : propriedades) {
			if(a.getProprietario() == null) propriedadesNaoAlocadas.add(a);
		}
		return propriedadesNaoAlocadas;
	} 
	
	public static ArrayList<PropriedadeAlocavel> getPropriedadesJogador(Jogador jogador) {
		var propriedades = getPropriedades();
		ArrayList<PropriedadeAlocavel> propriedadesJogador = new ArrayList<>();
		for(var a : propriedades) {
			if(a.getProprietario() == jogador) propriedadesJogador.add(a);
		}
		return propriedadesJogador;
	}
	
	public static ArrayList<PropriedadeAlocavel> getPropriedadesNaoJogador(Jogador jogador) {
		var propriedades = getPropriedades();
		ArrayList<PropriedadeAlocavel> propriedadesJogador = new ArrayList<>();
		for(var a : propriedades) {
			if(a.getProprietario() != jogador && a.getStatus() == StatusPropriedade.ALOCADA) propriedadesJogador.add(a);
		}
		return propriedadesJogador;
	}
	// Retorna uma lista de todas as propriedades de acordo com a cor passada como parâmetro
	public static ArrayList<Rua> propsTipo(corProp cor){
		ArrayList<Rua> list = new ArrayList<>();
		
		for(Propriedade p : propriedades.values()) {
			if(p instanceof Rua) {
				Rua r = (Rua) p;
				if(r.getCor() == cor) {
					list.add(r);
				}
			}
		}
		
		return list;
	}
	
	public static ArrayList<PropriedadeAlocavel> getPropriedades(){
		ArrayList<PropriedadeAlocavel> list = new ArrayList<>();
		
		for(var p : propriedades.values()) {
			if(p instanceof PropriedadeAlocavel) {
				list.add((PropriedadeAlocavel) p);
			}
		}
		
		return list;
	}
	
	
	
	@ Override
	public String toString() {
		String out = "";
		
		for(Propriedade p : propriedades.values()) {
			out += p + "\n";
		}
		
		return out;
	}
	
//	public String toString() {
//		String out = "";
//		
//		for(Map.Entry<Integer, Propriedade> entry : propriedades.entrySet()) {
//	        Integer key = entry.getKey();
//	        Propriedade p = entry.getValue();
//	        out += p.toString() + " " + key + "\n";
//	    }
//		
//		return out;
//	}
}