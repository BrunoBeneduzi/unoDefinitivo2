package cartas;

public abstract class Carta {
	private int numero;
	private CorDaCarta cor;
	private TipoDeCarta tipoDoEspecal;
	
	public Carta() {
		
	}
	
	public Carta(int numeroDaCarta, CorDaCarta cor, TipoDeCarta tipo) {
		this.numero = numeroDaCarta;
		this.cor = cor;
		this.tipoDoEspecal = tipo;
	}

	public int getNumero() {
		return numero;
	}

	public CorDaCarta getCor() {
		return cor;
	}

	public TipoDeCarta getTipoDoEspecal() {
		return tipoDoEspecal;
	}	
}