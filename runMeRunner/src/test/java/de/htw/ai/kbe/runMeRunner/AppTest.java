package de.htw.ai.kbe.runMeRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Properties;
import java.util.Scanner;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import de.htw.ai.kbe.propsFileUtil.PropsFileUtil;
import de.htw.ai.kbe.propsFileUtil.PropsReadException;
import de.htw.ai.kbe.runMeRunner.analyse.ClassAnalyse;

/**
 * Unit tests for simple Application
 */
public class AppTest {
	private static final String CLASS_NAME = "de.htw.ai.kbe.runMeRunner.MyClassWithRunMes1";
	private static final int COUNT_METHODS = 9;
	private static final int COUNT_METHODS_WITH_RUNME = 2;
	private static final int COUNT_METHODS_WITH_RUNME_NON_INVOKABLE = 2;
	ClassAnalyse classAnalyse;

	@After
	public void cleanUp() {
		System.out.println("test is completed");
	}

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
		int countFromClassName = 0;
		for (@SuppressWarnings("unused")
		String iterable_element : classAnalyse.searchAllMethodsWithAnnotation("RunMe")) {
			countFromClassName += 1;
		}
		assertEquals(countFromClassName, COUNT_METHODS_WITH_RUNME);

	}

	@Test
	public void countMethodsWithAnnotationRunMeNonInvokable() throws Exception {
		ClassAnalyse classAnalyse = new ClassAnalyse(CLASS_NAME);
		int countFromClassName = 0;
		for (@SuppressWarnings("unused")
		String iterable_element : classAnalyse.searchAllMethodsWithAnnotationNotInvokable("RunMe")) {
			countFromClassName += 1;
		}
		assertEquals(countFromClassName, COUNT_METHODS_WITH_RUNME_NON_INVOKABLE);

	}

	@Test
	public void compareRunMeReportAndTheResultString() throws Exception {
		ClassAnalyse classAnalyse = new ClassAnalyse(CLASS_NAME);
		Result result = new Result();
		result.setMethodCount(classAnalyse.countMethodsInClass());
		result.setMethodNamesWithRunMe(classAnalyse.searchAllMethodsWithAnnotation("RunMe"));
		result.setMethodNamesNotInvokable(classAnalyse.searchAllMethodsWithAnnotationNotInvokable("RunMe"));
		Result.setRunMeReport("testRunMeReport");
		// System.out.println(result.toString());
		String resultString = result.toString();
		result.addStringToLogfile(resultString);
		File file = new File("testRunMeReport");
		@SuppressWarnings("resource")
		String contents = new Scanner(file).useDelimiter("\\Z").next();
		// System.out.println(contents);
		assertEquals(contents, resultString);

	}

	@Test
	public void classToLoadIsEmptyException() {
		try {
			classAnalyse = new ClassAnalyse("");
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void classToLoadIsNotFoundException() {
		try {
			classAnalyse = new ClassAnalyse("ClassToLoadName");
		} catch (Exception e) {
			assertTrue(true);
		}
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
