package de.htw.ai.kbe.runMeRunner;

import java.util.Properties;

import de.htw.ai.kbe.propsFileUtil.PropsFileUtil;
import de.htw.ai.kbe.propsFileUtil.PropsReadException;
import de.htw.ai.kbe.runMeRunner.analyse.ClassAnalyse;
import de.htw.ai.kbe.runMeRunner.consoleDataParser.Parser;

public class App {
	
	private static final String ANNOTATION_NAME = "RunMe";
	private static final String CLASS_KEY_NAME = "classToLoad";
	private static Result result = new Result();
	
	public static void main(String[] args) throws Exception {
		
		/**
		Parser parser = new Parser();
		parser.parseCLI(args);
		
		String myClassName = "";
		Properties properties = null;
		
		try {
			properties = PropsFileUtil.readPropsFile(parser.getPropsFileName());
			
			if(!properties.containsKey(CLASS_KEY_NAME)) {
				throw new Exception("'classToLoad' ist nicht gefunden!");
			}
			
		} catch (PropsReadException e) {
			//throw new Exception("'classToLoad' ist nicht gefunden!");
		}
		
		myClassName = properties.getProperty(CLASS_KEY_NAME);
		
		ClassAnalyse classAnalyse = new ClassAnalyse(myClassName);
		
		result.setMethodCount(classAnalyse.countMethodsInClass());
		result.setMethodNamesWithRunMe(classAnalyse.searchAllMethodsWithAnnotnnotation(ANNOTATION_NAME));
		result.setMethodNamesNotInvokable(classAnalyse.searchAllMethodsWithAnnotnnotationNotInvokable(ANNOTATION_NAME));
		Result.setRunMeReport(parser.getOutFileName());
		
		System.out.println(result.toString());
		*/


		// Test bez konsoli
		String myClassName = "";
		Properties properties = null;
		
		try {
			properties = PropsFileUtil.readPropsFile("./propsFile.properties");
			
			if(!properties.containsKey(CLASS_KEY_NAME)) {
				throw new Exception("'classToLoad' ist nicht gefunden!");
			}

		} catch (PropsReadException e) {
			//throw new Exception("'classToLoad' ist nicht gefunden!");
		} 
		
		myClassName = properties.getProperty(CLASS_KEY_NAME);

		
		ClassAnalyse classAnalyse = new ClassAnalyse(myClassName);
		
		result.setMethodCount(classAnalyse.countMethodsInClass());
		result.setMethodNamesWithRunMe(classAnalyse.searchAllMethodsWithAnnotnnotation(ANNOTATION_NAME));
		result.setMethodNamesNotInvokable(classAnalyse.searchAllMethodsWithAnnotnnotationNotInvokable(ANNOTATION_NAME));
		Result.setRunMeReport("");
		
		System.out.println(result.toString());

		
		
	}

	


}
