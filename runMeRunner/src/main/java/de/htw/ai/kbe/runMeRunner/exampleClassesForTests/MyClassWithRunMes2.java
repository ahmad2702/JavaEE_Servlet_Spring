package de.htw.ai.kbe.runMeRunner.exampleClassesForTests;

import de.htw.ai.kbe.runMeRunner.RunMe;

/**
 * Class with example methods
 */
public class MyClassWithRunMes2 implements MethodsExample {

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
	public void annonMeth1() {

	}

	@RunMe()
	public void annonMeth2() {

	}

	@RunMe()
	public void annonMeth3() {

	}

}
