package jogador;
import java.util.ArrayList;
import java.util.List;
import cartas.*;
import baralhoDeCartas.*;
import java.util.Scanner;

public class Jogador {
	private Scanner sc = new Scanner(System.in);
	private List<Carta> baralhoDoJogador = new ArrayList<>();
	private Baralho baralho = new Baralho();
	private String nomeDoJogador;
	
	public Jogador(String nome) {//coloca um novo jogador e entrega 9 cartas para ele
		this.nomeDoJogador = nome;
		for(int i = 0; i < 9; i++) this.baralhoDoJogador.add(this.baralho.cartaComprada());
	}
	
	public void compraCarta() {//esse metodo compra uma carta do BARALHO e coloca no baralho do jogador atual
		this.baralhoDoJogador.add(this.baralho.cartaComprada());
	}
	
	public void verCartas() {//olha as cartas que estão na mão
		int i = 0;
		for(Carta carta: this.baralhoDoJogador) {
			i++;
			System.out.printf("Carta %d -> Numero %-3d | %-10s | %-10s \n", i,carta.getNumero(), carta.getCor(), carta.getTipoDoEspecal());
			System.out.println("-------------------------------------------------");
		}
	}
	
	public Carta jogarCarta() {//joga a carta na mesa
		this.verCartas();
		System.out.print("Digite o codigo da carta -> ");
		int posicao = sc.nextInt() - 1;

		this.baralho.setPosicao(posicao);
		
		Carta carta = this.baralhoDoJogador.get(posicao);
		
		
		return carta;
	}

	
	public List<Carta> getBaralhoDoJogador() {return baralhoDoJogador;}

	public String getNomeDoJogador() {return nomeDoJogador;}

	public void setNomeDoJogador(String nomeDoJogador) {this.nomeDoJogador = nomeDoJogador;}
}