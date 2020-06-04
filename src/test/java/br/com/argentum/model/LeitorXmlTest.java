package br.com.argentum.model;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import br.com.argentum.reader.LeitorXml;

public class LeitorXmlTest {

	@Test
	public void carregaXmlComApenasUmaNegociacao() {
		
		String xml = 
				"<list>\n"
				+ "  <negociacao>\n"
				+ "    <preco>10.0</preco>\n"
				+ "    <quantidade>4</quantidade>\n"
				+ "    <data>\n"
				+ "      <data>1459782000000</data>\n"
				+ "      <timezone>America/Sao_Paulo</timezone>\n"
				+ "    </data>\n"
				+ "  </negociacao>\n"
				+"</list>";
		
		LeitorXml leitor = new LeitorXml();
		InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
		List<Negociacao> negociacoes = leitor.carrega(inputStream);
		Negociacao negociacaoEsperada = new Negociacao(10.0, 4, LocalDateTime.of(2016, 4, 4, 12, 00));
		assertEquals(1, negociacoes.size());
		assertEquals(negociacaoEsperada, negociacoes.get(0));
	}
}
