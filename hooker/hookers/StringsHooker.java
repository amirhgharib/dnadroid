package com.dnadroid.hooker.hookers;

import java.util.HashMap;
import java.util.Map;

import com.dnadroid.hooker.SubstrateMain;
import com.dnadroid.hooker.exceptions.HookerInitializationException;

/**
 * @author Georges Bossert
 * 
 */
public class StringsHooker extends Hooker {

  public static final String NAME = "Strings";

  public StringsHooker() {
    super(StringsHooker.NAME);
  }


  @Override
  public void attach() {
    attachOnStringBuilderClass();
    attachOnStringBufferClass();
    attachOnWidgetTextView();

  }
  
  /**
   * Attach on StringBuilder class
   */
  private void attachOnStringBuilderClass() {
    final String className = "java.lang.StringBuilder";

    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("StringBuilder", 0);
    methodsToHook.put("append", 0);
    methodsToHook.put("toString", 0);
   
    
    try {
      hookMethods(null, className, methodsToHook);
      SubstrateMain.log(new StringBuilder("hooking ").append(className)
          .append(" methods sucessful").toString());

    } catch (HookerInitializationException e) {
      SubstrateMain.log(
          new StringBuilder("hooking ").append(className).append(" methods has failed").toString(),
          e);
    }
  }

  /**
   * Attach on StringBuffer class
   */
  private void attachOnStringBufferClass() {
    final String className = "java.lang.StringBuffer";

    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("StringBuffer", 0);
    methodsToHook.put("append", 0);
    methodsToHook.put("toString", 0);
   
    
    try {
      hookMethods(null, className, methodsToHook);
      SubstrateMain.log(new StringBuilder("hooking ").append(className)
          .append(" methods sucessful").toString());

    } catch (HookerInitializationException e) {
      SubstrateMain.log(
          new StringBuilder("hooking ").append(className).append(" methods has failed").toString(),
          e);
    }
  }

   /**
   * Attach on WidgetTextView
   */
  private void attachOnWidgetTextView() {
    final String className = "android.widget.TextView";

    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("setText", 0);
    
    try {
      hookMethods(null, className, methodsToHook);
      SubstrateMain.log(new StringBuilder("hooking ").append(className)
          .append(" methods sucessful").toString());

    } catch (HookerInitializationException e) {
      SubstrateMain.log(
          new StringBuilder("hooking ").append(className).append(" methods has failed").toString(),
          e);
    }
  }

  


}
