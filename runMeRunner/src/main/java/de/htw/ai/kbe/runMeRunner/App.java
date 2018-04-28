package de.htw.ai.kbe.runMeRunner;

import java.lang.reflect.Method;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import de.htw.ai.kbe.propsFileUtil.PropsFileUtil;

@SuppressWarnings("unused")
public class App {

	public static void main(String[] args) {
		ConfigurationVars configuration = parseCLI(args);
	}

	// try {
	// String cl = new PropsFileUtil().readPropsFile(new File(args[0]));
	// callMethods(cl);
	// } catch (ArrayIndexOutOfBoundsException e) {
	// System.out.println("Config file name missing as parameter");
	// }

	private static void callMethods(String cl) {

		try {
			Object x = Class.forName(cl).newInstance();
			for (Method methode : x.getClass().getMethods()) {

				if (methode.isAnnotationPresent(RunMe.class)) {
					System.out.println(methode.invoke(x, methode.getAnnotation(RunMe.class).input()) + "\n");
				}

			}

		} catch (Exception e) {

		}
	}

	/**
	 * Parser for CommandLine Command-Line Handling with Apache Commons CLI
	 * 
	 * @author prakhov_k1
	 * @param pArgs
	 *            Arguments
	 * @return return
	 */
	private static ConfigurationVars parseCLI(String[] pArgs) {
		ConfigurationVars configVars = new ConfigurationVars();
		CommandLineParser parser = new DefaultParser();

		Options options = new Options();
		// File configurations, no optional.
		options.addOption("c", "configFile", true, "ConfigFile is required");
		options.addRequiredOption("l", "logFile", true, "LogFile is required");
		// schema options
		options.addOption("h", "help", false, "Show this help");
		options.addOption("i", "ignore", true, "Ignore specified object type");
		options.addOption("d", "ddl", false, "Create a ddl sync script (schema1 is main)");
		options.addOption(null, "url1", true, "Url of your first database");
		options.addOption(null, "user1", true, "Username for the first database");

		String header = "Welcome to RunMeRunner!\n";

		try {

			CommandLine commandLine = parser.parse(options, pArgs);

			if (commandLine.hasOption("h")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("Command Line Parameters", header, options, "");
			}
			if (commandLine.hasOption("d")) {
				// setConfigFilePath(commandLine.getOptionValue("c"));
				// configVars.setMakesDDL(true);
			}

			/**
			 * options to ignore
			 */
			if (commandLine.hasOption("i")) {
				// optionsToIgnore(configVars, prefix, commandLine);
			}

			if (commandLine.hasOption("l")) {
				// setLogFile(commandLine.getOptionValue("l"));
			}

			/**
			 * properties by command line
			 */
			if (commandLine.hasOption("option1")) {
				// configVars.setEnableUrl1(true);
				// configVars.setUrl1(commandLine.getOptionValue("url1"));
			}
			if (commandLine.hasOption("option2")) {
				// configVars.setEnableUser1(true);
				// configVars.setUser1(commandLine.getOptionValue("user1"));
			}

		} catch (ParseException e) {
			e.printStackTrace();

		}
		return configVars;
	}

	/**
	 * The Class CommandLineException.
	 */
	class CommandLineException extends Exception {

		/** serial number of the {@link CommandLineException} */
		private static final long serialVersionUID = 1L;

		/**
		 * Instantiates a new command line exception.
		 *
		 * @param pMessage
		 *            the message
		 */
		public CommandLineException(String pMessage) {
			super(pMessage);
		}
	}

}
