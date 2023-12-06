package enums;

public class ANSI {
	// Códigos de escape ANSI para cores
	public static final String RED = "\033[31m";
	public static final String GREEN = "\033[32m";
	public static final String YELLOW = "\033[33m";
	public static final String BLUE = "\033[34m";
	public static final String PURPLE = "\033[35m";
	public static final String CYAN = "\033[36m";
	public static final String WHITE = "\033[37m";
	public static final String LIGHTRED = "\033[91m";
	public static final String LIGHTGREEN = "\033[92m";
	public static final String LIGHTYELLOW = "\033[93m";
	public static final String LIGHTBLUE = "\033[94m";
	public static final String LIGHTPURPLE = "\033[95m";
	public static final String LIGHTCYAN = "\033[96m";
	public static final String LIGHTWHITE = "\033[97m";

	// Códigos de escape ANSI para efeitos
	public static final String BOLD = "\033[1m";
	public static final String ITALIC = "\033[2m";
	public static final String UNDERLINED = "\033[4m";
	public static final String ENVELOPE = "\033[5m";
	public static final String INVERTED = "\033[7m";

	// Código de escape ANSI para restaurar a cor padrão
	public static final String RESET = "\033[0m";
}