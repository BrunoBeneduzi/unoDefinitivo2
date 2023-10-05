package mesa;
import java.util.ArrayList;
import java.util.List;
import cartas.Carta;
import cartas.CartaEspecial;
import cartas.CorDaCarta;
import cartas.TipoDeCarta;
import jogador.*;
import juiz.*;
import baralhoDeCartas.*;

public class Mesa {
	private  int numeroDaCartaMesa;
	private TipoDeCarta especialCartaMesa;
	private CorDaCarta corCartaMesa;
	private List<Jogador> player = new ArrayList<>();
	private  Juiz juiz = new Juiz();
	private TipoDeCarta direcao = TipoDeCarta.DIREITA;//controla a direção do jogo
	private static int vez = 0;//controla de quem é a vez de jogar
	private CartaEspecial especial = new CartaEspecial();
	private Baralho baralho = new Baralho();
	private boolean proximoCompraQuatro = true;
	private boolean proximoCompraDois = true;
	private static int contMaisQuatro = 0;
	private static int contMaisDois = 0;

	public Mesa() {

	}

	public Mesa(String nome) {//esse construtor inicia o jogo colocando uma carta na mesa
		Carta carta= juiz.colocaCartaIncialMesa();
		this.numeroDaCartaMesa = carta.getNumero();
		this.corCartaMesa = carta.getCor();
		this.especialCartaMesa = carta.getTipoDoEspecal();
	}

	public void jogaCartasNaMesa() {//recebe a carta que o jogador jogou mas antes mostra a carta que está na mesa para poder jogar	
		System.out.println("********************* "+this.player.get(this.vez).getNomeDoJogador().toUpperCase() + " É a sua vez"+ " *********************");
		this.mostrarCartaDaMesa();
		this.partida(this.player.get(this.vez).jogarCarta());
	}

	public void direçãoDoJogoMaisReverso(TipoDeCarta direcao, Carta carta) {//direciona o jogo, caso jogue reverso o jogo muda de posicao
		if(!TipoDeCarta.PULA.equals(carta.getTipoDoEspecal()))this.vez += especial.reverso(this.player.size() - 1, this.vez, this.direcao);
	}

	public void pular(Carta carta) {
		if(TipoDeCarta.PULA.equals(carta.getTipoDoEspecal())) this.vez += Math.abs(especial.pula(this.player.size() - 1, this.vez, this.direcao));
	}

	public void partida(Carta carta) {
		if(this.numeroDaCartaMesa == carta.getNumero() || (this.corCartaMesa.equals(carta.getCor()) || CorDaCarta.PRETO.equals(carta.getCor()))) {
			switch(carta.getTipoDoEspecal()) {
			case COMUM:
				this.colocaCartaNaMesa(carta);//coloca a carta que foi jogada na mesa
				this.colocarCartaNoBaralhoReserva(carta);//remove a carta do jogador e coloca no baralho reserva
				this.compraDoisAgora(carta);
				this.comprarQuatroAgora();//compra +4
				this.direçãoDoJogoMaisReverso(this.direcao, carta);//define a direçaõ do jogo
				break;
			case PULA:
				this.colocaCartaNaMesa(carta);
				this.colocarCartaNoBaralhoReserva(carta);
				this.compraDoisAgora(carta);
				this.comprarQuatroAgora();
				this.pular(carta);// se a carta foi de pular ele vai realizar a ação
				this.direçãoDoJogoMaisReverso(this.direcao, carta);
				break;
			case REVERSO:
				this.colocaCartaNaMesa(carta);
				this.colocarCartaNoBaralhoReserva(carta);
				this.compraDoisAgora(carta);
				this.comprarQuatroAgora();
				this.reverso(carta);//se a carta for um reverso, vai ser modificado a direção do jogo
				this.direçãoDoJogoMaisReverso(this.direcao, carta);//define a direçaõ do jogo
				break;
			case COMPRADOIS:
				proximoCompraDois = true;
				this.contMaisDois += especial.compraDois();
				this.colocaCartaNaMesa(carta);
				this.colocarCartaNoBaralhoReserva(carta);
				this.direçãoDoJogoMaisReverso(this.direcao, carta);//se a carta for um reverso, vai ser modificado a direção do jogo
				break;
			case COMPRAQUATRO:
				proximoCompraQuatro = true;
				this.contMaisQuatro += especial.compraQuatro();
				this.compraDoisAgora(carta);
				this.numeroDaCartaMesa = carta.getNumero();
				this.corCartaMesa = especial.mudaCor();
				this.especialCartaMesa = carta.getTipoDoEspecal();
				
				this.colocarCartaNoBaralhoReserva(carta);
				this.direçãoDoJogoMaisReverso(this.direcao, carta);//se a carta for um reverso, vai ser modificado a direção do jogo
				break;

			case MUDACOR:
				this.comprarQuatroAgora();//compra +4
				this.compraDoisAgora(carta);
				this.numeroDaCartaMesa = carta.getNumero();
				this.corCartaMesa = especial.mudaCor();
				this.especialCartaMesa = carta.getTipoDoEspecal();
				
				this.colocarCartaNoBaralhoReserva(carta);
				this.direçãoDoJogoMaisReverso(this.direcao, carta);//se a carta for um reverso, vai ser modificado a direção do jogo
				break;
			}
			
			
		}else {
			System.out.println("Carta invalida");
			this.jogaCartasNaMesa();//se for uma carta que não é a mesma da mesa, vai ser feito o jogador jogar novamente
		}
	}

	private void reverso(Carta carta) {//verifica se a carta que foi jogada é um reverso
		if(TipoDeCarta.REVERSO.equals(carta.getTipoDoEspecal())) {
			if(this.direcao.equals(TipoDeCarta.DIREITA)) this.direcao = TipoDeCarta.ESQUERDA;
			else this.direcao = TipoDeCarta.DIREITA;
		}
	}


	private void colocaCartaNaMesa(Carta carta) {
		this.numeroDaCartaMesa = carta.getNumero();
		this.corCartaMesa = carta.getCor();
		this.especialCartaMesa = carta.getTipoDoEspecal();
	}

	private void comprarQuatroAgora() {//se o jogador não jogar outro mais quatro ele vai ter que comprar +4, caso ele jogue, o proximo vai ter que comprar ou jogar outro +4
		if(proximoCompraQuatro) {
			for(int i = 0; i < this.contMaisQuatro; i++) this.player.get(this.vez).compraCarta();
			proximoCompraQuatro = false;
		}
	}


	private void compraDoisAgora(Carta carta) {
		if(proximoCompraQuatro) {
			for(int i = 0; i < this.contMaisDois; i++) this.player.get(this.vez).compraCarta();
			proximoCompraDois = false;
		}
	}

	private void mostrarCartaDaMesa() {//mostra a carta que está na mesa
		System.out.printf("MESA  ->   Numero %-5d | %-10s | %-10s \n", this.numeroDaCartaMesa, this.corCartaMesa.toString(), this.especialCartaMesa.toString());
		System.out.println("_________________________________________________");
	}

	public void colocarCartaNoBaralhoReserva(Carta carta) {
		baralho.AdicionaNoBaralhoReserva(carta);
		this.getPlayer().get(this.vez).getBaralhoDoJogador().remove(baralho.getPosicao());
	}

	public void setPlayer(Jogador player) {this.player.add(player);}

	private TipoDeCarta getDirecao() {return direcao;}

	private int getVez() {return vez;}

	private int getNumeroDaCartaMesa() {return numeroDaCartaMesa;}

	private void setNumeroDaCartaMesa(int numeroDaCartaMesa) {this.numeroDaCartaMesa = numeroDaCartaMesa;}

	private TipoDeCarta getEspecialCartaMesa() {return especialCartaMesa;}

	private void setEspecialCartaMesa(TipoDeCarta especialCartaMesa) {this.especialCartaMesa = especialCartaMesa;}

	private CorDaCarta getCorCartaMesa() {return corCartaMesa;}

	private void setCorCartaMesa(CorDaCarta corCartaMesa) {this.corCartaMesa = corCartaMesa;}

	public List<Jogador> getPlayer() {return player;}
}