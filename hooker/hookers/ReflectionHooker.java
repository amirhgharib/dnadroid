package com.dnadroid.hooker.hookers;

import java.util.HashMap;
import java.util.Map;

import com.dnadroid.hooker.SubstrateMain;
import com.dnadroid.hooker.exceptions.HookerInitializationException;

/**
 * @author Dimitri Kirchner.
 * Hooker to attach on reflection classes.
 * This class may sometimes trigger a stacktrace, but it's not impacting the analysis...
 */
public class ReflectionHooker extends Hooker {

	public static final String NAME = "ReflectionHooker";

	public ReflectionHooker() {
		super(ReflectionHooker.NAME);
	}

	@Override
	public void attach() {
		attachOnClass();
//		attachOnClassNotFoundException(); //It brings errors
		attachOnClassLoader();
		attachOnFieldClass();
		attachOnMethodClass();
	}
	
	/**
	 * Attach on class Class.
	 */
	private void attachOnClass() {
		final String className = "java.lang.Class";
	
		Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

		methodsToHook.put("forName", 0);
		methodsToHook.put("isInstance", 0);
		methodsToHook.put("getClasses", 0);
		methodsToHook.put("getClassLoader", 0);
		methodsToHook.put("getConstructor", 0);
		methodsToHook.put("getDeclaredClasses", 0);
		methodsToHook.put("getDeclaredFields", 0);
		methodsToHook.put("getDeclaredMethods", 0);
		methodsToHook.put("getDeclaredConstructors", 0);
		methodsToHook.put("getDeclaredField", 0);
		methodsToHook.put("getDeclaredMethod", 0);
		methodsToHook.put("getDeclaredConstructor", 0);
		methodsToHook.put("getResourceAsStream", 0);
		methodsToHook.put("getResource", 0);
		methodsToHook.put("getProtectionDomain", 0);
		methodsToHook.put("newInstance", 0);
		methodsToHook.put("getName", 0);
		methodsToHook.put("getPackage", 0);
		methodsToHook.put("getField", 0);
		methodsToHook.put("getFields", 0);

		try {
			hookMethods(null, className, methodsToHook);
			SubstrateMain.log(new StringBuilder("hooking ").append(className)
					.append(" methods sucessful").toString());

		} catch (HookerInitializationException e) {
			SubstrateMain.log(new StringBuilder("hooking ").append(className)
					.append(" methods has failed").toString(), e);
		}
	}
	
	/**
	 * Attach on class ClassLoader.
	 */
	private void attachOnClassLoader() {
		final String className = "java.lang.ClassLoader";
	
		Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

		//methodsToHook.put("loadClass", 0); //This is looping with our Hooker.
		methodsToHook.put("getSystemClassLoader", 0);
		methodsToHook.put("getSystemResource", 0);
		methodsToHook.put("getSystemResources", 0);
		methodsToHook.put("getResource", 0); //same in class
		methodsToHook.put("getResources", 0);
		methodsToHook.put("findResource", 0);
		methodsToHook.put("findResources", 0);
		methodsToHook.put("getResourceAsStream", 0); //same in class
		methodsToHook.put("getSystemResourceAsStream", 0);
		methodsToHook.put("setClassAssertionStatus", 0);
		methodsToHook.put("clearAssertionStatus", 0);
		methodsToHook.put("setPackageAssertionStatus", 0);
		methodsToHook.put("setDefaultAssertionStatus", 0);

		try {
			hookMethods(null, className, methodsToHook);
			SubstrateMain.log(new StringBuilder("hooking ").append(className)
					.append(" methods sucessful").toString());

		} catch (HookerInitializationException e) {
			SubstrateMain.log(new StringBuilder("hooking ").append(className)
					.append(" methods has failed").toString(), e);
		}
	}
	
	/**
	 * Attach on class Field.
	 */
	private void attachOnFieldClass() {
		final String className = "java.lang.reflect.Field";
	
		Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

		methodsToHook.put("get", 0);
		methodsToHook.put("getBoolean", 0);
		methodsToHook.put("getByte", 0);
		methodsToHook.put("getChar", 0);
		methodsToHook.put("getShort", 0);
		methodsToHook.put("getInt", 0);
		methodsToHook.put("getLong", 0);
		methodsToHook.put("getFloat", 0);
		methodsToHook.put("getDouble", 0);
		methodsToHook.put("set", 0);
		methodsToHook.put("setBoolean", 0);
		methodsToHook.put("setByte", 0);
		methodsToHook.put("setChar", 0);
		methodsToHook.put("setShort", 0);
		methodsToHook.put("setInt", 0);
		methodsToHook.put("setLong", 0);
		methodsToHook.put("setFloat", 0);
		methodsToHook.put("setDouble", 0);
		
		try {
			hookMethods(null, className, methodsToHook);
			SubstrateMain.log(new StringBuilder("hooking ").append(className)
					.append(" methods sucessful").toString());

		} catch (HookerInitializationException e) {
			SubstrateMain.log(new StringBuilder("hooking ").append(className)
					.append(" methods has failed").toString(), e);
		}
	}
	
	/**
	 * Attach on class Method.
	 */
	private void attachOnMethodClass() {
		final String className = "java.lang.reflect.Method";
	
		Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

		methodsToHook.put("getName", 0); //same in class
		methodsToHook.put("getModifiers", 0);
		methodsToHook.put("getTypeParameters", 0);
		methodsToHook.put("getReturnType", 0);
		methodsToHook.put("getGenericReturnType", 0);
		methodsToHook.put("getParameterTypes", 0);
		methodsToHook.put("getGenericParameterTypes", 0);
		methodsToHook.put("equals", 0);
		methodsToHook.put("invoke", 0);

		try {
			hookMethods(null, className, methodsToHook);
			SubstrateMain.log(new StringBuilder("hooking ").append(className)
					.append(" methods sucessful").toString());

		} catch (HookerInitializationException e) {
			SubstrateMain.log(new StringBuilder("hooking ").append(className)
					.append(" methods has failed").toString(), e);
		}
	}

}
