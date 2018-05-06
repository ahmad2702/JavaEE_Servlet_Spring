package de.htw.ai.kbe.runMeRunner;

import java.util.Properties;

import de.htw.ai.kbe.propsFileUtil.PropsFileUtil;
import de.htw.ai.kbe.propsFileUtil.PropsReadException;
import de.htw.ai.kbe.runMeRunner.analyse.ClassAnalyse;
import de.htw.ai.kbe.runMeRunner.consoleDataParser.Parser;

/**
 * 
 * Main Class instances ClassAnalyse, reads the property file and writes the
 * result to the result string
 *
 */
public class App {
	/**
	 * Declare a final string RunMe
	 */
	private static final String ANNOTATION_NAME = "RunMe";
	/**
	 * Declare a final string classToLoad
	 */
	private static final String CLASS_KEY_NAME = "classToLoad";
	/**
	 * Creates new Instance Result
	 */
	private static Result result = new Result();

	/**
	 * Main Method
	 * 
	 * @param args
	 *            arguments
	 * @throws Exception
	 *             Exceptions
	 */
	public static void main(String[] args) throws Exception {

		// Statement for Console Test
		Parser parser = new Parser();
		parser.parseCLI(args);

		String myClassName = "";
		Properties properties = null;

		try {
			properties = PropsFileUtil.readPropsFile(parser.getPropsFileName());

			if (!properties.containsKey(CLASS_KEY_NAME)) {
				throw new Exception("'classToLoad' ist nicht gefunden!");
			}

		} catch (PropsReadException e) { // throw new Exception("'classToLoad' istnicht gefunden!");
		}

		myClassName = properties.getProperty(CLASS_KEY_NAME);

		ClassAnalyse classAnalyse = new ClassAnalyse(myClassName);

		result.setMethodCount(classAnalyse.countMethodsInClass());
		result.setMethodNamesWithRunMe(classAnalyse.searchAllMethodsWithAnnotation(ANNOTATION_NAME));
		result.setMethodNamesNotInvokable(classAnalyse.searchAllMethodsWithAnnotationNotInvokable(ANNOTATION_NAME));
		Result.setRunMeReport(parser.getOutFileName());

		// System.out.println(result.toString());
		String resultString = result.toString();
		result.addStringToLogfile(resultString);

		// // Statement for .jar Test
		// String myClassName = "";
		// Properties properties = null;
		//
		// try {
		// /**
		// * read the property file
		// */
		// properties = PropsFileUtil.readPropsFile("./propsFile.properties");
		// /**
		// * If class can not be found -> Exception
		// */
		// if (!properties.containsKey(CLASS_KEY_NAME)) {
		// throw new Exception("'classToLoad' ist nicht gefunden!");
		// }
		//
		// } catch (PropsReadException e) {
		// // throw new Exception("'classToLoad' ist nicht gefunden!");
		// }
		//
		// myClassName = properties.getProperty(CLASS_KEY_NAME);
		// ClassAnalyse classAnalyse = new ClassAnalyse(myClassName);
		//
		// /**
		// * Run the methods to search the methods, more information about Use Cases
		// * {@see de.htw.ai.kbe.runMeRunner.ClassAnalyse#}
		// */
		// result.setMethodCount(classAnalyse.countMethodsInClass());
		// result.setMethodNamesWithRunMe(classAnalyse.searchAllMethodsWithAnnotation(ANNOTATION_NAME));
		// result.setMethodNamesNotInvokable(classAnalyse.searchAllMethodsWithAnnotationNotInvokable(ANNOTATION_NAME));
		// Result.setRunMeReport("");
		//
		// System.out.println(result.toString());
		//
		// }
	}
}