package com.dnadroid.hooker;

import java.util.List;

import com.saurik.substrate.MS;
public class GlobalContextHooker {

  /**
   * Main class to inject in memory
   */
  private static final String CLASSNAME_ANDROID_APPCONTEXTIMPL = "android.app.ContextImpl";

  /**
   * Attach on ConTextImpl to retrieve the global context
   */
  public static void attach(final List<String> filteredPackageNames) {
    
    MS.hookClassLoad(CLASSNAME_ANDROID_APPCONTEXTIMPL, new MS.ClassLoadHook() {
      public void classLoaded(Class<?> resources) {
        GlobalApplicationContextAnalyzer.loadContext(resources, filteredPackageNames);
      }
    });    
  }

}
