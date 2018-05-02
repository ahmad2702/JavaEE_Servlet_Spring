package de.htw.ai.kbe.runMeRunner;

import de.htw.ai.kbe.propsFileUtil.PropsFileUtil;
import de.htw.ai.kbe.propsFileUtil.PropsReadException;
import de.htw.ai.kbe.runMeRunner.consoleDataParser.Parser;

public class App {
	
	private static final String ANNOTATION_NAME = "RunMe";
	private static final String CLASS_KEY_NAME = "classToLoad";
	private static Result result = new Result();
	
	public static void main(String[] args) {
		
		// Test s Console
		//ConfigurationVars configuration = parseCLI(args);
		
		Parser parser = new Parser();
		parser.parseCLI(args);
		
		String key = "";
		try {
			key = PropsFileUtil.readPropsFile(parser.getPropsFileName()).getProperty(CLASS_KEY_NAME);
		} catch (PropsReadException e) {
			e.printStackTrace();
		}
		
		ClassAnalyse classAnalyse = new ClassAnalyse(key);
		
		// Test bez konsoli
		//ClassAnalyse classAnalyse = new ClassAnalyse("de.htw.ai.kbe.runMeRunner.MyClassWithRunMes1");
		
		result.setMethodCount(classAnalyse.countMethodsInClass());
		result.setMethodNamesWithRunMe(classAnalyse.searchAllMethodsWithAnnotnnotation(ANNOTATION_NAME));
		result.setMethodNamesNotInvokable(classAnalyse.searchAllMethodsWithAnnotnnotationNotInvokable(ANNOTATION_NAME));
		Result.setRunMeReport(parser.getOutFileName());
		
		System.out.println(result.toString());
	}

	


}
