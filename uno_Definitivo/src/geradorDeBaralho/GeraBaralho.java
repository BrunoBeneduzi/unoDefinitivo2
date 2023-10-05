package geradorDeBaralho;
import java.util.ArrayList;
import java.util.List;
import cartas.*;
public class GeraBaralho {

	private List<Carta> baralho = new ArrayList<>();


	public GeraBaralho() {
		this.GeradorBaralho();//chama o metodo gerador de baralho para ser ativado na hora que chamar o construtor na main
	}

	private void GeradorBaralho() {
		for(int i = 1; i < 10; i++) {//gera as cartas comuns 
			for(int j = 0; j < 2; j++) {//gera duas cartas iguais 

				baralho.add(new CartaComum(i, CorDaCarta.AMARELO ,TipoDeCarta.COMUM));
				baralho.add(new CartaComum(i, CorDaCarta.AZUL ,TipoDeCarta.COMUM));
				baralho.add(new CartaComum(i, CorDaCarta.VERDE ,TipoDeCarta.COMUM));
				baralho.add(new CartaComum(i, CorDaCarta.VERMELHO ,TipoDeCarta.COMUM));

				if(i == 1 && j == 0) {//gera 4 numeros 0
					baralho.add(new CartaComum(0, CorDaCarta.AMARELO ,TipoDeCarta.COMUM));
					baralho.add(new CartaComum(0, CorDaCarta.AZUL ,TipoDeCarta.COMUM));
					baralho.add(new CartaComum(0, CorDaCarta.VERDE ,TipoDeCarta.COMUM));
					baralho.add(new CartaComum(0, CorDaCarta.VERMELHO ,TipoDeCarta.COMUM));
				}

				if(i < 2){//gera 8 cartas de reverso de cada cor
					baralho.add(new CartaEspecial(10 ,CorDaCarta.AMARELO, TipoDeCarta.REVERSO));
					baralho.add(new CartaEspecial(10 ,CorDaCarta.VERDE,TipoDeCarta.REVERSO));
					baralho.add(new CartaEspecial(10 ,CorDaCarta.VERMELHO,TipoDeCarta.REVERSO));
					baralho.add(new CartaEspecial(10 ,CorDaCarta.AZUL,TipoDeCarta.REVERSO));
				}
				if(i < 2) {// gera 8 cartas de pular de cada cor
					baralho.add(new CartaEspecial(11, CorDaCarta.AMARELO, TipoDeCarta.PULA));
					baralho.add(new CartaEspecial(11,  CorDaCarta.VERDE, TipoDeCarta.PULA));
					baralho.add(new CartaEspecial(11,  CorDaCarta.VERMELHO, TipoDeCarta.PULA));
					baralho.add(new CartaEspecial(11,  CorDaCarta.AZUL, TipoDeCarta.PULA));
				}

				if(i < 2) {// gera 8 cartas de comprar +2 de cada cor
					baralho.add(new CartaEspecial(12, CorDaCarta.AMARELO, TipoDeCarta.COMPRADOIS));
					baralho.add(new CartaEspecial(12, CorDaCarta.VERDE, TipoDeCarta.COMPRADOIS));
					baralho.add(new CartaEspecial(12, CorDaCarta.VERMELHO, TipoDeCarta.COMPRADOIS));
					baralho.add(new CartaEspecial(12, CorDaCarta.AZUL, TipoDeCarta.COMPRADOIS));
				}

				if(i < 3) {//gera 4 cartas do tipo +4
					baralho.add(new CartaEspecial(13, CorDaCarta.PRETO, TipoDeCarta.COMPRAQUATRO));
				}

				if(i < 3) {//gera 4 cartas do tipo mudar cor
					baralho.add(new CartaEspecial(14, CorDaCarta.PRETO, TipoDeCarta.MUDACOR));
				}
			}
		}
	}

	public List<Carta> getBaralho() {
		return baralho;
	}
}