package de.htw.ai.kbe.runMeRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Result {

	private static File runMeReport;

	private int methodCount;
	private List<String> methodNamesWithRunMe = new ArrayList<String>();
	private List<String> methodNamesNotInvokable = new ArrayList<String>();

	public static File getRunMeReport() {
		return runMeReport;
	}

	public static void setRunMeReport(String outFileName) {
		Result.runMeReport = new File(outFileName);
	}

	public int getMethodCount() {
		return methodCount;
	}

	public void setMethodCount(int methodCount) {
		this.methodCount = methodCount;
	}

	public List<String> getMethodNamesWithRunMe() {
		return methodNamesWithRunMe;
	}

	public void setMethodNamesWithRunMe(List<String> methodNamesWithRunMe) {
		this.methodNamesWithRunMe = methodNamesWithRunMe;
	}

	public List<String> getMethodNamesNotInvokable() {
		return methodNamesNotInvokable;
	}

	public void setMethodNamesNotInvokable(List<String> methodNamesNotInvokable) {
		this.methodNamesNotInvokable = methodNamesNotInvokable;
	}

	@Override
	public String toString() {
		return "Gesamtanzahl der Klasse-Methoden: " + methodCount + "\n\n" +

				"Anzahl der @RunMe-Methoden " + methodNamesWithRunMe.size() + "\n" + "Auflistung @RunMe-Methoden: "
				+ methodNamesWithRunMe + "\n\n" +

				"Anzahl der NICHT ausfuehrbaren @RunMe-Methoden " + methodNamesNotInvokable.size() + "\n"
				+ "Auflistung der NICHT ausfuehrbaren : " + methodNamesNotInvokable + "\n";
	}

}
