package de.htw.ai.kbe.songsServlet;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Converter {
	
	public static String getXmlFromSongs(Songs songs) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Songs.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        
        StringWriter sw = new StringWriter();
        marshaller.marshal(songs, sw);
        String xmlString = sw.toString();
		
        return xmlString;
	}
	
}
