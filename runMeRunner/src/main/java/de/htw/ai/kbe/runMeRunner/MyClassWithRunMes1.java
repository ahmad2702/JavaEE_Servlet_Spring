package de.htw.ai.kbe.runMeRunner;

import de.htw.ai.kbe.runMeRunner.exampleClassesForTests.MethodsExample;

public class MyClassWithRunMes1 implements MethodsExample{
	
	public String method1() {
		// TODO Auto-generated method stub
		return null;
	}

	public int method2() {
		// TODO Auto-generated method stub
		return 0;
	}

	public float method3() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double method4() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String[] method5() {
		// TODO Auto-generated method stub
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
	
	private void testPrivate() {
		
	}

	
}