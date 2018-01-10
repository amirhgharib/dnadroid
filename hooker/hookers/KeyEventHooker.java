package com.dnadroid.hooker.hookers;

import java.util.HashMap;
import java.util.Map;

import com.dnadroid.hooker.SubstrateMain;
import com.dnadroid.hooker.exceptions.HookerInitializationException;

/**
 * @author Georges Bossert
 * 
 */
public class KeyEventHooker extends Hooker {

  public static final String NAME = "KeyEvent";

  public KeyEventHooker() {
    super(KeyEventHooker.NAME);
  }


  @Override
  public void attach() {
    attachOnKeyEventClass();

  }


	private void attachOnKeyEventClass() {
		final String className = "android.view.KeyEvent.Callback";

		Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

		methodsToHook.put("OnKeyDown", 0);
		methodsToHook.put("onKeyLongPress", 0);
		methodsToHook.put("onKeyMultiple", 0);
		methodsToHook.put("onKeyUp", 0);

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
