package de.htw.ai.kbe.runMeRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Result {
	
	private static File runMeReport;
	
	private int methodCount;
	private List<String> methodNamesWithRunMe = new ArrayList<String>();
	private List<String> methodNamesNotInvokable = new ArrayList<String>();
	
	public int getMethodCount() {
		return methodCount;
	}
	
	public void setMethodCount(int methodCount) {
		this.methodCount = methodCount;
	}
	
	public List<String> getMethodNamesWithRunMe() {
		return methodNamesWithRunMe;
	}
	
	public void addMethodNamesWithRunMe(String methodeName) {
		this.methodNamesWithRunMe.add(methodeName);
	}
	
	public List<String> getMethodNamesNotInvokable() {
		return methodNamesNotInvokable;
	}
	
	public void addMethodNamesNotInvokable(String methodeName) {
		this.methodNamesNotInvokable.add(methodeName);
	}
	
	public static File getRunMeReport() {
		return runMeReport;
	}
	
	public static void setRunMeReport(File runMeReport) {
		Result.runMeReport = runMeReport;
	}

	@Override
	public String toString() {
		return "MethodCount= " + methodCount + "\n" + 
				"methodNamesWithRunMe= " + methodNamesWithRunMe + "\n" +
				"MethodNamesNotInvokable= " + methodNamesNotInvokable + "\n";
	}
	
	
	
	
}
