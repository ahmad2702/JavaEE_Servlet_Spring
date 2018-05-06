package de.htw.ai.kbe.runMeRunner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Result {

	private int methodCount;
	private List<String> methodNamesWithRunMe = new ArrayList<String>();
	private List<String> methodNamesNotInvokable = new ArrayList<String>();

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

	/**
	 * Method to write the result string in output file
	 * 
	 * @param pathname
	 * @throws IOException
	 */
	public void writeResultToLogfile(String pathname) throws IOException {
		PrintWriter pWriter = null;
		try {
			pWriter = new PrintWriter(new BufferedWriter(new FileWriter(new File(pathname))));
			pWriter.println(toString());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (pWriter != null) {
				pWriter.flush();
				pWriter.close();
			}
		}
	}

}
