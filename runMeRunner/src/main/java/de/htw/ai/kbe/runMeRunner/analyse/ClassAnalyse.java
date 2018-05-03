package de.htw.ai.kbe.runMeRunner.analyse;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassAnalyse {
	
	private Class<?> willAnalysed = null;
	private Object classObj = null;
	
	private String packageName;

	public ClassAnalyse(String className) throws Exception {
		
		if(className.isEmpty()) {
			throw new Exception("Value von 'classToLoad' ist leer!");
		}
		
		try {
			
			this.willAnalysed = (Class<?>) Class.forName(className);
			this.classObj = willAnalysed.newInstance();
			this.packageName = willAnalysed.getPackage().getName();
			
		} catch (ClassNotFoundException e1) {
			throw new Exception("Die Klasse ist nicht gefunden!");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}
	
	public int countMethodsInClass() {
		Method[] methods = willAnalysed.getDeclaredMethods();
		return methods.length;
	}
	
	public List<String> searchAllMethodsWithAnnotnnotation(String annotnnotationName) {
		List<String> methodNamesWithAnnotnnotation = new ArrayList<String>();
		
		Method[] methods = willAnalysed.getDeclaredMethods();
		
		for (Method methode : methods) {
			Annotation[] annos = methode.getDeclaredAnnotations();
			
			// Poisk vseh metodov s annotaziey
			if(annos.length != 0) {
				for(Annotation a : annos) {
					if(a.annotationType().getName().equals(packageName+"." + annotnnotationName)) {
						//System.out.println(methode.getName());
						methodNamesWithAnnotnnotation.add(methode.getName());
					}
				}
			}
		}
		
		return methodNamesWithAnnotnnotation;
	}
	
	public List<String> searchAllMethodsWithAnnotnnotationNotInvokable(String annotnnotationName) {
		List<String> methodNamesWithAnnotnnotationNotInvokable = new ArrayList<String>();
		
		Method[] methods = willAnalysed.getDeclaredMethods();
		
		for (Method methode : methods) {
			Annotation[] annos = methode.getDeclaredAnnotations();
			
			// Poisk vseh metodov s annotaziey
			if(annos.length != 0) {
				for(Annotation a : annos) {
					if(a.annotationType().getName().equals(packageName+"." + annotnnotationName)) {
						try {
							//methode.setAccessible(true); // for private Methods necessary
							methode.invoke(this.classObj);
						} catch (IllegalAccessException e) {
							methodNamesWithAnnotnnotationNotInvokable.add(methode.getName());
						} catch (IllegalArgumentException e) {
							methodNamesWithAnnotnnotationNotInvokable.add(methode.getName());
						} catch (InvocationTargetException e) {
							methodNamesWithAnnotnnotationNotInvokable.add(methode.getName());
						}
					}
				}
			}
		}
		
		return methodNamesWithAnnotnnotationNotInvokable;
	}
	
}
