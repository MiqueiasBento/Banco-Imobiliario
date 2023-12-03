package Controls;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

import principal_class.*;

public class Main {
	public static void main(String args[]) {
		BancoImobiliario BI = new BancoImobiliario();
		
		// BI.addJogadores();
		
		// Looping até ter um vencedor
		while(!BI.isWinner()) {
			
			// Jogador por jogador faz um jogada
			for(Jogador jog : BI.getJogadores()) {
				
				// Pode administrar e negociar quantas vezes quiser, mas só passa para o próximo quando jogar os dados
				write("\nPLAYER: " + jog.getNome());
				
				while(true) {
					write("JOGAR[J]	      ADMINISTRAR[A]	    NEGOCIAR[N]			STATUS[S]");
					String in = input();
					
					if(in.equals("J") || in.equals("j")) {
						// Faz a jogada com os dados
						BI.jogar(jog);
						break;
					} else if(in.equals("A") || in.equals("a")) {
						// Chama a função para addministrar as propriedades
					} else if(in.equals("N") || in.equals("n")) {
						// Chama função para negociar com o outro proprietário
					} else if(in.equals("S") || in.equals("s")) {
						// Mostra o status do jogador atual
						write(jog);
					} else {
						write("Tente novamente");
					}
				}
				
				if(BI.winner()) {
					return;
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
}
