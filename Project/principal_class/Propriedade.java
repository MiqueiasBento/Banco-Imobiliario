package principal_class;

public class Propriedade {
	protected int id;
	protected String label;

	public Propriedade(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
	
	public int getId() {
		return this.id;
	}
	
	@ Override
	public String toString() {
		return String.format("%02d", this.id) + ": " + this.label;
	}
}
