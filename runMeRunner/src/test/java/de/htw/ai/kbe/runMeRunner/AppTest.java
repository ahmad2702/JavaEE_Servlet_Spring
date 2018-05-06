package de.htw.ai.kbe.runMeRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Properties;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.htw.ai.kbe.propsFileUtil.PropsFileUtil;
import de.htw.ai.kbe.propsFileUtil.PropsReadException;
import de.htw.ai.kbe.runMeRunner.analyse.ClassAnalyse;

public class AppTest {
	private static final String CLASS_NAME = "de.htw.ai.kbe.runMeRunner.MyClassWithRunMes1";
	private static final int COUNT_METHODS = 9;
	private static final int COUNT_METHODS_WITH_RUNME = 2;
	private static final int COUNT_METHODS_WITH_RUNME_NON_INVOKABLE = 2;
	private Scanner scanner;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void countAllMethods() throws Exception {
		ClassAnalyse classAnalyse = new ClassAnalyse(CLASS_NAME);
		
		Result result = new Result();
		result.setMethodCount(classAnalyse.countMethodsInClass());
		
		int countFromClass = result.getMethodCount();
		assertTrue(countFromClass == COUNT_METHODS);
	}

	@Test
	public void countMethodsWithAnnotationRunMe() throws Exception {
		ClassAnalyse classAnalyse = new ClassAnalyse(CLASS_NAME);
		
		Result result = new Result();
		result.setMethodNamesWithRunMe(classAnalyse.searchAllMethodsWithAnnotation("RunMe"));
		
		int countRunMeFunc = result.getMethodNamesWithRunMe().size();
		assertTrue(countRunMeFunc == COUNT_METHODS_WITH_RUNME);
	}

	@Test
	public void countMethodsWithAnnotationRunMeNonInvokable() throws Exception {
		ClassAnalyse classAnalyse = new ClassAnalyse(CLASS_NAME);
		
		Result result = new Result();
		result.setMethodNamesNotInvokable(classAnalyse.searchAllMethodsWithAnnotationNotInvokable("RunMe"));
		
		int countRunMeFuncNonInvok = result.getMethodNamesNotInvokable().size();
		assertTrue(countRunMeFuncNonInvok == COUNT_METHODS_WITH_RUNME_NON_INVOKABLE);
	}

	@Test
	public void compareRunMeReportAndTheResultString() throws Exception {
		ClassAnalyse classAnalyse = new ClassAnalyse(CLASS_NAME);
		
		Result result = new Result();
		result.setMethodCount(classAnalyse.countMethodsInClass());
		result.setMethodNamesWithRunMe(classAnalyse.searchAllMethodsWithAnnotation("RunMe"));
		result.setMethodNamesNotInvokable(classAnalyse.searchAllMethodsWithAnnotationNotInvokable("RunMe"));

		String pathname = "testRunMeReport.txt";
		result.writeResultToLogfile(pathname);
		
		File file = new File(pathname);
		scanner = new Scanner(file);
		String contents = scanner.useDelimiter("\\Z").next();
		assertEquals(contents, result.toString());
	}

	@Test
	public void classToLoadIsEmptyException() throws Exception {
		exception.expect(Exception.class);
		exception.expectMessage("Value von 'classToLoad' ist leer!");
		
		new ClassAnalyse("");
	}

	@Test
	public void classToLoadIsNotFoundException() throws Exception {
		exception.expect(Exception.class);
		exception.expectMessage("Die Klasse ist nicht gefunden!");
		
		new ClassAnalyse("ClassToLoadName");
	}

	@Test
	public void propertyClassToLoadExistsAndNotEmpty() throws PropsReadException {
		Properties prop = PropsFileUtil.readPropsFile("./propsFile.properties");
		
		Assert.assertEquals("de.htw.ai.kbe.runMeRunner.MyClassWithRunMes1", prop.getProperty("classToLoad"));
	}

	@Test
	public void propertyFileIsEmpty() throws PropsReadException {
		Properties prop = PropsFileUtil.readPropsFile("./propsFile2.properties");
		
		Assert.assertEquals("[]", prop.keySet().toString());
	}

}
