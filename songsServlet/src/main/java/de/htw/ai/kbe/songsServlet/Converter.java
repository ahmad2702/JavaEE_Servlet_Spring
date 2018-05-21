package de.htw.ai.kbe.songsServlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;

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
	
	public static Songs getSongsFromXmlString(String xmlText) throws JAXBException, IOException {		
		InputStream in = IOUtils.toInputStream(xmlText, "UTF-8");

		JAXBContext jaxbContext = JAXBContext.newInstance(Songs.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		Songs songs = (Songs) jaxbUnmarshaller.unmarshal(in);
		
		return songs;
	}
	
}
