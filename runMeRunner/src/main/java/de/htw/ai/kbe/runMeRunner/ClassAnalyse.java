package de.htw.ai.kbe.runMeRunner;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassAnalyse {
	
	private Class<?> x = null;
	private Object classObj = null;
	
	private String packageName;

	public ClassAnalyse(String className) {

		try {
			
			this.x = (Class<?>) Class.forName(className);
			this.classObj = x.newInstance();
			this.packageName = x.getPackage().getName();
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}
	
	public int countMethodsInClass() {
		Method[] methods = x.getDeclaredMethods();
		return methods.length;
	}
	
	public List<String> searchAllMethodsWithAnnotnnotation(String annotnnotationName) {
		List<String> methodNamesWithAnnotnnotation = new ArrayList<String>();
		
		Method[] methods = x.getDeclaredMethods();
		
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
		
		Method[] methods = x.getDeclaredMethods();
		
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
