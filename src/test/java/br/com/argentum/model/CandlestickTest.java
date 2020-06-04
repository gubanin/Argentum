package br.com.argentum.model;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

public class CandlestickTest {

	@Test(expected = IllegalArgumentException.class)
	public void maximoNaoDeveSerMenorQueMinimo() {			
		CandleBuilder builder = new CandleBuilder();
		Candlestick candle = builder.comAbertura(10.0).comMaximo(10.0).comMinimo(15.0)
									.comFechamento(20.0).comVolume(45.0)
									.comData(LocalDateTime.now()).geraCandle();
	}
	
	@Test
	public void ehAltaSeFechamentoForIgualAbertura() {
		CandleBuilder builder = new CandleBuilder();
		Candlestick candle = builder.comAbertura(10.0).comMaximo(30.0).comMinimo(15.0)
									.comFechamento(10.0).comVolume(65.0)
									.comData(LocalDateTime.now()).geraCandle();
		assertTrue(candle.isAlta());
		
	}
}
