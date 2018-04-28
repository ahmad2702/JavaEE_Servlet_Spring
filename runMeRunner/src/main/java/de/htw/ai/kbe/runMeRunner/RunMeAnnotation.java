package de.htw.ai.kbe.runMeRunner;

public class RunMeAnnotation {

	@RunMe(input = "ClassNameExample")
	public boolean aufruf1(String input) {
		System.out.println("RunClassName usw");
		System.out.println(input);
		// return new ClassWithMethod().Method(input);
		return true; // TODO: löschen
	}

}
