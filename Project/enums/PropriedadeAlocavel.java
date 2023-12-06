package enums;

import principal_class.*;

public interface PropriedadeAlocavel {
	public Jogador getProprietario();
	public void setProprietario(Jogador jogador);
	public String getPropriedade();
	public String previaPropriedade();
	public double getValorPropriedade();
	public void setStatus(StatusPropriedade status);
	public StatusPropriedade getStatus();
}
