package de.htw.ai.kbe.propsFileUtil;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testFileIsNull() throws PropsReadException {
		exception.expect(PropsReadException.class);
		exception.expectMessage("Eingabe kann nicht leer/null sein!");

		String filename = null;
		PropsFileUtil.readPropsFile(filename);
	}

	@Test
	public void testFileIsEmpty() throws PropsReadException {
		exception.expect(PropsReadException.class);
		exception.expectMessage("Eingabe kann nicht leer/null sein!");

		String filename = "";
		PropsFileUtil.readPropsFile(filename);
	}

	@Test
	public void testFileIsProp() throws PropsReadException {
		exception.expect(PropsReadException.class);
		exception.expectMessage("Not a props file!");

		String filename = "src/main/java/resourcen/myProp.txt";
		PropsFileUtil.readPropsFile(filename);
	}

	@Test
	public void testGetPropFile() throws PropsReadException {
		String filename = "src/main/java/resourcen/myProp.properties";
		Properties properties = PropsFileUtil.readPropsFile(filename);

		String username = "testUser";

		assertTrue(properties.get("username").equals(username));
	}

}
