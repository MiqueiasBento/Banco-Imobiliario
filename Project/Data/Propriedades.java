package Data;

import java.util.*;
import principal_class.*;

public class Propriedades {
	private static TreeMap<Integer, Propriedade> propriedades = new TreeMap<>();
	
	public Propriedades() {
		int i = 0; 
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
		propriedades.put(i++, new Propriedade(i, "Inicio"));
	}
	
	public static Propriedade getPropriedade(int id) {
		return propriedades.get(id);
	}
	
	@ Override
	public String toString() {
		String out = "";
		
		for(Propriedade p : propriedades.values()) {
			out += p + "\n";
		}
		
		return out;
	}
}