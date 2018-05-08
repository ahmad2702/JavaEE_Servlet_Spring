package de.htw.ai.kbe.runMeRunner;

public class ClassWithRunMeMethods {

    @RunMe
    public static String method() {
        System.out.println("In method: ich bin public und static!");
        return "method".toUpperCase();
    }
    
	@RunMe
	String method1() {
		System.out.println("In method1: ich bin package-private!");
		return "method1".toUpperCase();
	}

	@RunMe
	public boolean method2() {
		System.out.println("In method2: ich bin public!");
		return true;
	}
	
	@RunMe
	private boolean method3() {
		System.out.println("In method3: ich bin privat!");
		return true;
	}
	
	@RunMe
    boolean method4(String input) {
        System.out.println("In method4: ich bekomme einen Parameter!");
        return true;
    }
	
	public void method5() {
		System.out.println("Ich werde nicht aufgerufen!");
	}
}
