package Controls;
import java.util.Scanner;
import principal_class.*;
import Data.*;

public class Main {
	public static void main(String args[]) {
		BancoImobiliario BI = new BancoImobiliario();
		
		BI.addJogadores();
		while(!BI.isWinner()) {
			for(Jogador j : BI.getJogadores()) {
				// Pode administrar e negociar quantas vezes quiser, mas só passa para o próximo quando jogar os dados
				write("\nPLAYER: " + j.getNome());
				
				while(true) {
					write("JOGAR[J]	 ADMINISTRAR[A]	    NEGOCIAR[N]");
					String in = input();
					
					if(in.equals("J") || in.equals("j")) {
						// Faz a jogada com os dados
						BI.jogar(j);
						break;
					} else if(in.equals("A") || in.equals("a")) {
						// Chama a função para addministrar as propriedades
					} else if(in.equals("N") || in.equals("n")) {
						// Chama função para negociar com o outro proprietário
					} else {
						write("Tente novamente");
					}
				}
			}
		}
	}
	private static Scanner sc = new Scanner(System.in);
	private static void write(Object value)	{ System.out.println(value); }
	private static String input() 			{ return sc.nextLine(); }
//	private static int number(String value)	{ return Integer.parseInt(value); }
}
