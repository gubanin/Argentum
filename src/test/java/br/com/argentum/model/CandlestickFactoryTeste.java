package br.com.argentum.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CandlestickFactoryTeste {

	@Test
	public void sequenciaDeNegociacoesSimples() {
		LocalDateTime hoje = LocalDateTime.now();
		
		Negociacao n1 = new Negociacao(40.0, 100, hoje);
		Negociacao n2 = new Negociacao(35.0, 100, hoje);
		Negociacao n3 = new Negociacao(45.0, 100, hoje);
		Negociacao n4 = new Negociacao(20.0, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(n1,n2,n3,n4);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.geraCandleParaData(negociacoes, hoje);
		
		assertEquals(40.0, candle.getAbertura(), 0.000001);
		assertEquals(20.0, candle.getFechamento(), 0.000001);
		assertEquals(45.0, candle.getMaximo(), 0.000001);
		assertEquals(20.0, candle.getMinimo(), 0.000001);
		assertEquals(14000.0, candle.getVolume(), 0.000001);
	}
	
	@Test
	public void geraCandlestickComApenasUmaNegociacao() {
		LocalDateTime hoje = LocalDateTime.now();
		
		Negociacao n = new Negociacao(40.0, 100, hoje);
		List<Negociacao> negociacoes = Arrays.asList(n);
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.geraCandleParaData(negociacoes, hoje);
		
		assertEquals(40.0, candle.getAbertura(), 0.000001);
		assertEquals(40.0, candle.getFechamento(), 0.000001);
		assertEquals(40.0, candle.getMaximo(), 0.000001);
		assertEquals(40.0, candle.getMinimo(), 0.000001);
		assertEquals(4000.0, candle.getVolume(), 0.000001);
	}
	
	@Test
	public void geraCandlestickComZeroEmCasoDeNenhumaNegociacao() {
		LocalDateTime hoje = LocalDateTime.now();
		
		List<Negociacao> negociacoes = new ArrayList<>();
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.geraCandleParaData(negociacoes, hoje);
		
		assertEquals(0.0, candle.getAbertura(), 0.000001);
		assertEquals(0.0, candle.getFechamento(), 0.000001);
		assertEquals(0.0, candle.getMaximo(), 0.000001);
		assertEquals(0.0, candle.getMinimo(), 0.000001);
		assertEquals(0.0, candle.getVolume(), 0.000001);
	}
	
	@Test
	public void negociacaoDeTresDiasDiferentesgeraTresCandlesDiferentes() {
		LocalDateTime hoje = LocalDateTime.now();
		
		Negociacao n1 = new Negociacao(50.0, 20, hoje);
		Negociacao n2 = new Negociacao(100.0, 20, hoje);
		Negociacao n3 = new Negociacao(150.0, 20, hoje);
		
		LocalDateTime amanha = hoje.plusDays(1);
		
		Negociacao n4 = new Negociacao(50.0, 100, amanha);
		Negociacao n5 = new Negociacao(10.0, 20, amanha);

		LocalDateTime depois = amanha.plusDays(1);
		
		Negociacao n6 = new Negociacao(35.0, 20, depois);
		Negociacao n7 = new Negociacao(35.0, 20, depois);

		List<Negociacao> negociacoes = Arrays.asList(n1,n2,n3,n4,n5,n6,n7);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		List<Candlestick> candle = fabrica.controiCandlestick(negociacoes);
		
		assertEquals(3, candle.size());
		assertTrue(negociacoes.get(0).isMesmoDia(candle.get(0).getData()));
		assertTrue(negociacoes.get(3).isMesmoDia(candle.get(1).getData()));
		assertTrue(negociacoes.get(5).isMesmoDia(candle.get(2).getData()));
		
		assertEquals(6000.0, candle.get(0).getVolume(), 0.0000001);
		assertEquals(50.0, candle.get(0).getMinimo(), 0.0000001);
		assertEquals(150.0, candle.get(0).getMaximo(), 0.0000001);
		assertEquals(50.0, candle.get(0).getAbertura(), 0.0000001);
		assertEquals(150.0, candle.get(0).getFechamento(), 0.0000001);


	}
}
