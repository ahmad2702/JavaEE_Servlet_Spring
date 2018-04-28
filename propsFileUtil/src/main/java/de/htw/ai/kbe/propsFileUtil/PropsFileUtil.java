package de.htw.ai.kbe.propsFileUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * class PropFileUtil opens FileInputStream and reads the properties, throws
 * Exceptions, if smth goes wrong
 *
 */
public class PropsFileUtil {

	@SuppressWarnings("resource")
	public static Properties readPropsFile(String filename) throws PropsReadException {

		FileInputStream fis;
		Properties property = new Properties();

		if (filename == null || filename.isEmpty()) {
			throw new PropsReadException("Eingabe kann nicht leer/null sein!");
		} else {

			try {
				fis = new FileInputStream(filename);

				if (!filename.contains(".properties"))
					throw new PropsReadException("Not a props file!");

				property.load(fis);
				fis.close();

			} catch (FileNotFoundException e) {
				throw new PropsReadException("File existiert nicht!");
			} catch (IOException e) {
				throw new PropsReadException("Props File ist nicht lesbar!");
			}

		}

		return property;

	}

}
