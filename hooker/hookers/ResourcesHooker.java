package com.dnadroid.hooker.hookers;

import java.util.HashMap;
import java.util.Map;

import com.dnadroid.hooker.SubstrateMain;
import com.dnadroid.hooker.exceptions.HookerInitializationException;

public class ResourcesHooker extends Hooker {

	public static final String NAME = "Resources";
	
	public ResourcesHooker() {
		super(ResourcesHooker.NAME);
	}

	@Override
	public void attach() {
		attachOnResourcesClass();
	}
	
	/**
	 * Attach on Resources class.
	 */
	private void attachOnResourcesClass(){
		
		final String className = "android.content.res.Resources";
		
		Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

		methodsToHook.put("Resources", 1);
		methodsToHook.put("getAssets", 1);
		methodsToHook.put("getBoolean", 1);
		methodsToHook.put("getConfiguration", 1);
		methodsToHook.put("getResourceEntryName", 1);
		methodsToHook.put("getResourceName", 1);
		methodsToHook.put("getResourcePackageName", 1);
		methodsToHook.put("getResourceTypeName", 1);
		methodsToHook.put("getString", 0);
		methodsToHook.put("getSystem", 2);
		methodsToHook.put("getValue", 0);
		methodsToHook.put("getXml", 0);
		methodsToHook.put("openRawResource", 2);
		methodsToHook.put("openRawResourceFd", 2);
		methodsToHook.put("parseBundleExtra", 1);
		methodsToHook.put("parseBundleExtras", 1);
		methodsToHook.put("updateConfiguration", 2);
		
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
