package de.htw.ai.kbe.runMeRunner;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import de.htw.ai.kbe.propsFileUtil.PropsFileUtil;
import de.htw.ai.kbe.propsFileUtil.PropsReadException;

@SuppressWarnings("unused")
public class App {
	
	private static String propsFile;
	private static Properties properties;
	
	private static Result result = new Result();

	public static void main(String[] args) {
		
		//ConfigurationVars configuration = parseCLI(args);
		
		//String key = properties.getProperty("classToLoad");

		
		searchMethods("de.htw.ai.kbe.runMeRunner.MyClassWithRunMes1");

		System.out.println(result.toString());
		
		
	}
	// try {
	// Properties cl = new PropsFileUtil().readPropsFile(args[0]);
	// callMethods(cl);
	// } catch (ArrayIndexOutOfBoundsException e) {
	// System.out.println("Config file name missing as parameter");
	// }
	//
	// private static void callMethods(String cl) {
	//
	// try {
	// Object x = Class.forName(cl).newInstance();
	// for (Method methode : x.getClass().getMethods()) {
	//
	// if (methode.isAnnotationPresent(RunMe.class)) {
	// System.out.println(methode.invoke(x,
	// methode.getAnnotation(RunMe.class).input()) + "\n");
	// }
	//
	// }
	//
	// } catch (Exception e) {
	//
	// }
	// }

	/**
	 * Parser for CommandLine Command-Line Handling with Apache Commons CLI
	 * 
	 * @param pArgs
	 *            Arguments
	 * @return return
	 */
	private static ConfigurationVars parseCLI(String[] pArgs) {
		ConfigurationVars configVars = new ConfigurationVars();
		CommandLineParser parser = new DefaultParser();

		Options options = new Options();
		// File configurations, no optional.
		options.addRequiredOption("p", "propsFile", true, "ConfigFile is required");
		options.addRequiredOption("o", "runMeReport", true, "LogFile is required");
		// optional arguments
		options.addOption("h", "help", false, "Show this help");
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
			
			
			
			
			if (commandLine.hasOption("p")) {
				propsFile = commandLine.getOptionValue("p");
				try {
					properties = PropsFileUtil.readPropsFile(propsFile);
					
				} catch (PropsReadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (commandLine.hasOption("o")) {
				File file = new File(commandLine.getOptionValue("o"));
				Result.setRunMeReport(file);
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
	
	private static void searchMethods(String key) {
		try {
			
			//Object x = Class.forName(App.class.getPackage().getName()+ "." + key);
			Class<?> x = (Class<?>) Class.forName(key);
			
			Object classObj = null;
			try {
				classObj = x.newInstance();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String packageName = x.getPackage().getName();	
			
			Method[] methods = x.getDeclaredMethods();
			result.setMethodCount(methods.length);
			
			for (Method methode : methods) {
				//System.out.println(methode.getName());
				
				Annotation[] annos = methode.getDeclaredAnnotations();
				
				// Poisk vseh methodov s RunMe
				if(annos.length != 0) {
					for(Annotation a : annos) {
						if(a.annotationType().getName().equals(packageName+".RunMe")) {
							//System.out.println(methode.getName());
							result.addMethodNamesWithRunMe(methode.getName());
						}
					}
				}
			      
				// Poisk "nerabochih" methodov 
				
				try {
					//methode.setAccessible(true);
					methode.invoke(classObj);
				} catch (IllegalAccessException e) {
					result.addMethodNamesNotInvokable(methode.getName());
				} catch (IllegalArgumentException e) {
					result.addMethodNamesNotInvokable(methode.getName());
				} catch (InvocationTargetException e) {
					result.addMethodNamesNotInvokable(methode.getName());
				}
				
				
				
			}
			
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
