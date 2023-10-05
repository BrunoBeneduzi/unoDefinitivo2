package cartas;
import java.util.Scanner;

public class CartaEspecial extends Carta {
	Scanner sc = new Scanner(System.in);

	public CartaEspecial() {

	}

	public CartaEspecial(int numeroDaCarta, CorDaCarta cor, TipoDeCarta tipo) {
		super(numeroDaCarta, cor, tipo);
	}

	public CorDaCarta mudaCor() {//muda a cor da mesa usando a carta de mudar cor

		System.out.print("Escolha a cor\n"+"1- Azul\n"+"2- Verde\n"+"3- Amarelo\n"+"4- Vermelho\n"+"->");
		switch(sc.nextInt()) {
		case 1:
			return CorDaCarta.AZUL;
		case 2:
			return CorDaCarta.VERDE;
		case 3:
			return CorDaCarta.AMARELO;
		case 4:
			return CorDaCarta.VERMELHO;
		default:
			return null;
		}
	}

	public int reverso(int numeroDeJogadores ,int jogador ,TipoDeCarta direcao) {//muda a direção do jogo
		if(TipoDeCarta.DIREITA.equals(direcao)) {
			if(jogador == numeroDeJogadores) return -numeroDeJogadores;
			else return +1;
		}
		else {
			if(jogador == 0) return +numeroDeJogadores;
			else return -1;	
		}
	}

	public int compraQuatro() {return 4;}

	public int compraDois() {return 2;}

	public int pula(int numeroDeJogadores,int jogador, TipoDeCarta direcao) {//pula o jogador
		if(TipoDeCarta.DIREITA.equals(direcao)) {
			if(jogador == numeroDeJogadores) return -numeroDeJogadores - 1;
			else if(jogador == numeroDeJogadores - 1) return -numeroDeJogadores;
			else return +2;
		}else {
			if(jogador == 0) return +2;
			else if(jogador == 1) return +2;
			else return -2;
		}
	}
}