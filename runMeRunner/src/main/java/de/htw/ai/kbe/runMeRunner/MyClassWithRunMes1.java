package de.htw.ai.kbe.runMeRunner;

import de.htw.ai.kbe.runMeRunner.exampleClassesForTests.MethodsExample;

/**
 * Test Methods without Annotation and with Annotation (public, private and
 * protected)
 *
 */
public class MyClassWithRunMes1 implements MethodsExample {

	public String method1() {
		return null;
	}

	public int method2() {
		return 0;
	}

	public float method3() {
		return 0;
	}

	public double method4() {
		return 0;
	}

	public String[] method5() {
		return null;
	}

	@RunMe()
	private void annonMeth1() {
	}

	@RunMe()
	protected void annonMeth2() {
	}

	@SuppressWarnings("unused")
	private void testPrivate() {
	}

	public void annonMeth3() throws Exception {
		throw new Exception("Exception for anonyme Methode");
	}

}
