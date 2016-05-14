package br.com.util;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

import br.com.livro.domain.Carro;
import br.com.livro.domain.ListaCarros;
import br.com.livro.domain.Response;

/*
*	Classe que utiliza a bilbiotece JABX-RS para gerar arquivos no formato XML ou JSON.
*/

public class JAXBUtil {
	private static JAXBUtil instance;
	private static JAXBContext context;

	public static JAXBUtil getInstance() {
		return instance;
	}
	
	/*
	*	Insere no contexto todas as classe, já mapeada com as anotações, para que o JABX-RS identifique quais 
	* 	são as classes que ele vai transformar
	*/
	static {
		try {
			// Informa ao JAXB que é para gerar XML ou JSON destas classes.
			context = JAXBContext.newInstance(ListaCarros.class, Carro.class, Response.class);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	* Metodo que formata em XML os dados.
	*/
	public static String toXML(Object object) throws IOException {
		try {
			StringWriter writer = new StringWriter();
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(object, writer);
			String xml = writer.toString();
			return xml;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	* Metodo que formata em JSON os dados.
	*/
	public static String toJSON(Object object) throws IOException {
		try {
			StringWriter writer = new StringWriter();
			Marshaller m = context.createMarshaller();
			MappedNamespaceConvention con = new MappedNamespaceConvention();
			XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, writer);
			m.marshal(object, xmlStreamWriter);
			String json = writer.toString();
			return json;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

}
