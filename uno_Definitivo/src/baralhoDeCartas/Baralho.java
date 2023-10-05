package baralhoDeCartas;
import geradorDeBaralho.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import cartas.*;

public class Baralho {//os baralhos são do tipo static para que não sejam resetados sempre que um novo jogador entrar no jogo
	private Random sorteiaCarta = new Random();
	private static GeraBaralho baralho = new GeraBaralho();
	private static List<Carta> baralhoReserva = new ArrayList<>();//guarda o baralho reserva caso acabe o principal
	private static List<Carta> baralhoPrincipal = baralho.getBaralho();//guarda o baralho principal
	private static int posicao;


	public Baralho() {

	}

	public Carta cartaComprada() {//trasnfere uma carta comprada para o jogador que comprou a carta
		int sorteia = sorteiaCarta.nextInt(Baralho.baralhoPrincipal.size());

		Carta carta = Baralho.baralhoPrincipal.get(sorteia);

		return carta;
	}

	public  Carta colocaCartaInicialMesa() {//coloca a carta incial na mesa
		boolean liberaCarta = true;
		Carta carta;
		int sorteia;
		do {
			sorteia = sorteiaCarta.nextInt(Baralho.baralhoPrincipal.size());
			carta = Baralho.baralhoPrincipal.get(sorteia);
			if(TipoDeCarta.COMUM.equals(carta.getTipoDoEspecal())) liberaCarta = false; //se for uma carta do tipo comum ela vai ser jogada como carta inicial
		}while(liberaCarta);

		this.transBaralhoReserva(carta);

		return carta;
	}

	private void transBaralhoReserva(Carta transfere) {//transfere a carta principal para o baralho reserva e elimina ela do baralho principal
		Baralho.baralhoReserva.add(transfere);
		Baralho.baralhoPrincipal.remove(transfere);
	}

	public void AdicionaNoBaralhoReserva(Carta carta) {
		Baralho.baralhoReserva.add(carta);
	}

	public void setPosicao(int posicao) {this.posicao = posicao;}

	public int getPosicao() {return posicao;}

	public static List<Carta> getBaralhoReserva() {return baralhoReserva;}

	public static List<Carta> getBaralhoPrincipal() {return baralhoPrincipal;}
}