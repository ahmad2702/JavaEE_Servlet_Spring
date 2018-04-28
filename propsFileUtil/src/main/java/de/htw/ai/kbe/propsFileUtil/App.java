package de.htw.ai.kbe.propsFileUtil;

public class App {
	public static void main(String[] args) throws PropsReadException {

		String filename = "src/main/java/resourcen/myProp.properties";
		PropsFileUtil.readPropsFile(filename);

	}
}
