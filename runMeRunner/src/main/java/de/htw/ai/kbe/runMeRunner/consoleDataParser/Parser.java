package de.htw.ai.kbe.runMeRunner.consoleDataParser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Parser {

	private String propsFileName;
	private String outFileName;
		
	public void parseCLI(String[] pArgs) {

		CommandLineParser parser = new DefaultParser();

		Options options = new Options();
		
		// File configurations, no optional.
		options.addRequiredOption("p", "propsFile", true, "ConfigFile is required");
		options.addRequiredOption("o", "runMeReport", true, "LogFile is required");
		
		CommandLine commandLine;
		try {
			commandLine = parser.parse(options, pArgs);
			
			if (commandLine.hasOption("p")) {
				propsFileName = commandLine.getOptionValue("p");
			}
			
			if (commandLine.hasOption("o")) {
				outFileName = commandLine.getOptionValue("o");
			}
			
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

	public String getPropsFileName() {
		return propsFileName;
	}

	public String getOutFileName() {
		return outFileName;
	}
	
	
	


	/**
	class CommandLineException extends Exception {

		private static final long serialVersionUID = 1L;

		public CommandLineException(String pMessage) {
			super(pMessage);
		}
	}
	*/
	
}
