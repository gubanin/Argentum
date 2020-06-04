package br.com.argentum.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

public class NegociacaoTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveCriarNegociacaoComPrecoNegativo() {
		new Negociacao(-20.0, 2, LocalDateTime.now());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveCriarNegociacaoComDataNula() {
		new Negociacao(20.0, 2, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveCriarNegociacaoComQuantidadeNegativa() {
		new Negociacao(10.0, -2, LocalDateTime.now());
	}
	
	@Test
	public void mesmoSegundoEhMesmoDia() {
		LocalDateTime hoje = LocalDateTime.now();
		LocalDateTime agora = hoje;
		
		Negociacao n = new Negociacao(100.0, 20, hoje);
		
		assertTrue(n.isMesmoDia(agora));
	}
	
	@Test
	public void horariosDiferentesEhMesmoDia() {
		LocalDateTime hoje = LocalDateTime.of(2016, 04, 04, 12, 00);
		LocalDateTime agora = LocalDateTime.of(2016, 04, 04, 13, 00);
		
		Negociacao n = new Negociacao(100.0, 20, hoje);
		
		assertTrue(n.isMesmoDia(agora));
	}
	
	@Test
	public void mesesDiferentesNaoEhMesmoDia() {
		LocalDateTime hoje = LocalDateTime.of(2016, 04, 04, 12, 00);
		LocalDateTime agora = LocalDateTime.of(2016, 03, 04, 13, 00);
		
		Negociacao n = new Negociacao(100.0, 20, hoje);
		
		assertFalse(n.isMesmoDia(agora));
	}
	
	@Test
	public void anosDiferentesNaoEhMesmoDia() {
		LocalDateTime hoje = LocalDateTime.of(2016, 04, 04, 12, 00);
		LocalDateTime agora = LocalDateTime.of(2017, 04, 04, 13, 00);
		
		Negociacao n = new Negociacao(100.0, 20, hoje);
		
		assertFalse(n.isMesmoDia(agora));
	}
}
