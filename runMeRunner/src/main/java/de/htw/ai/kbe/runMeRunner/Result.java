package de.htw.ai.kbe.runMeRunner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Result {

	/**
	 * Log File
	 */
	private static File runMeReport;
	private int methodCount;
	private List<String> methodNamesWithRunMe = new ArrayList<String>();
	private List<String> methodNamesNotInvokable = new ArrayList<String>();

	/**
	 * Getter for runMeReport
	 * 
	 * @return runMeReport
	 */
	public static File getRunMeReport() {
		return runMeReport;
	}

	/**
	 * Setter for runMeReport
	 * 
	 * @param outFileName
	 */
	public static void setRunMeReport(String outFileName) {
		Result.runMeReport = new File(outFileName);
	}

	/**
	 * Getter for methodCount
	 * 
	 * @return methodCount
	 */
	public int getMethodCount() {
		return methodCount;
	}

	/**
	 * Setter for methodCount
	 * 
	 * @param methodCount
	 */
	public void setMethodCount(int methodCount) {
		this.methodCount = methodCount;
	}

	/**
	 * Getter for methodNamesWithRunMe
	 * 
	 * @return methodNamesWithRunMe
	 */
	public List<String> getMethodNamesWithRunMe() {
		return methodNamesWithRunMe;
	}

	/**
	 * Setter for methodNamesWithRunMe
	 * 
	 * @param methodNamesWithRunMe
	 */
	public void setMethodNamesWithRunMe(List<String> methodNamesWithRunMe) {
		this.methodNamesWithRunMe = methodNamesWithRunMe;
	}

	/**
	 * Getter for methodNamesNotInvokable
	 * 
	 * @return methodNamesNotInvokable
	 */
	public List<String> getMethodNamesNotInvokable() {
		return methodNamesNotInvokable;
	}

	/**
	 * Setter for methodNamesNotInvokable
	 * 
	 * @param methodNamesNotInvokable
	 */
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

	public void addStringToLogfile(String string) throws IOException {
	     PrintWriter pWriter = null;
	     try {
	       pWriter = new PrintWriter(new BufferedWriter(new FileWriter(getRunMeReport())));
	       pWriter.println(toString());
	     } catch (IOException ioe) {
	       ioe.printStackTrace();
	     } finally {
	       if (pWriter != null){
	         pWriter.flush();
	         pWriter.close();
	       }
	     }
	   }


}
