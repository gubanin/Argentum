package br.com.argentum.reader;

import java.io.InputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.argentum.model.Negociacao;
import br.com.argentum.xstream.LocalDateTimeConverter;

public class LeitorXml {

		public List<Negociacao> carrega(InputStream inputStream){
		
		XStream xstream = new XStream(new DomDriver());
		xstream.registerLocalConverter(Negociacao.class, "data", new LocalDateTimeConverter());
		xstream.alias("negociacao", Negociacao.class);
		
		return (List<Negociacao>) xstream.fromXML(inputStream);
	}

}
