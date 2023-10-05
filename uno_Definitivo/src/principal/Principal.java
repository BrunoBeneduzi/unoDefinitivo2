package principal;
import mesa.*;
import jogador.*;
import baralhoDeCartas.*;
public class Principal {

	public static void main(String[] args) {
		Mesa mesa = new Mesa("Bem vindo");

		mesa.setPlayer(new Jogador("Bruno"));
		mesa.setPlayer(new Jogador("Vitoria"));
		mesa.setPlayer(new Jogador("Murilo"));

		Baralho baralho = new Baralho();
		for(int i = 0; i < 2; i++) {
			mesa.jogaCartasNaMesa();
			mesa.jogaCartasNaMesa();
		}
		
			
		
		
		System.out.println(baralho.getBaralhoPrincipal().size());
		System.out.println(baralho.getBaralhoReserva().size());
		
	}
}