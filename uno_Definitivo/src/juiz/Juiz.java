package juiz;
import baralhoDeCartas.*;
import cartas.*;
import mesa.*;
public class Juiz {
	Baralho baralho = new Baralho();
	
	
	
	
	public Carta colocaCartaIncialMesa() {//coloca a primeira carta do tipo COMUM na mesa para iniciar o jogo
		
		
		Carta carta = baralho.colocaCartaInicialMesa();
		
		return carta;
	}
	

	
	
	public void uno() {
	}
}