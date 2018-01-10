package com.dnadroid.hooker;

import java.lang.reflect.Method;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import com.saurik.substrate.MS;


public class GlobalApplicationContextAnalyzer {

  /**
   * @param resources
   */
  public static void loadContext(Class<?> resources, final List<String> filteredPackageNames) {
    SubstrateMain.log("Initializing Global Application Context with " + resources);

    /**
     * Retrieves method "getPackageName"
     */
    Method getPackageNameMethod = null;

    try {
      getPackageNameMethod = resources.getMethod("getPackageName", new Class<?>[] {});
    } catch (NoSuchMethodException e) {
      SubstrateMain.log("Impossible to find method 'getPackageName'.", e);
    }

    if (getPackageNameMethod == null) {
      return;
    }

    /**
     * Hooks found method
     */
    final MS.MethodPointer<Object, Object> oldPointer = new MS.MethodPointer<Object, Object>();
    MS.hookMethod(resources, getPackageNameMethod, new MS.MethodHook<Object, Object>() {

      @Override
      public Object invoked(Object _this, Object... args) throws Throwable {

        /**
         * Retrieves current package name
         */
        String packageName = (String) oldPointer.invoke(_this, args);

        /**
         * Note as filtered if its the case
         */
        boolean filtered = filteredPackageNames.contains(packageName);
        ApplicationConfig.setFiltered(filtered);
        ApplicationConfig.setPackageName(packageName);
        
        if (!filtered) {
          /**
           * We retrieve the Current Context of the application
           */
          try {
            Class<?> contextImplClass = Class.forName("android.app.ContextImpl");
  
            Class<?> noparams[] = {};
            Method getApplicationContextMethod =
                contextImplClass.getDeclaredMethod("getApplicationContext", noparams);
  
            Context context = (Context) getApplicationContextMethod.invoke(_this);
            ApplicationConfig.setContext(context);
  
            Method getPackageManagerMethod =
                contextImplClass.getDeclaredMethod("getPackageManager", noparams);
            PackageManager pm = (PackageManager) getPackageManagerMethod.invoke(_this);
  
            android.content.pm.ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
  
            /**
             * Verify before hooking, the application is :
             * - FLAG_UPDATED_SYSTEM_APP : "this application has been install as an update to a built-in system application."
             * - FLAG_SYSTEM : "this application is installed in the device's system image."
             */
            if ((ai.flags & 0x81) == 0) {
              try {
                PackageInfo p = pm.getPackageInfo(packageName, 0);
                ApplicationConfig.setDataDir(p.applicationInfo.dataDir);
              } catch (NameNotFoundException e) {
                SubstrateMain.log(new StringBuilder("Error Package info for ").append(packageName)
                    .append(" not found.").toString(), e);
              }
            } else {
              ApplicationConfig.setFiltered(true);
            }
          } catch (Exception e) {
            SubstrateMain
                .log(
                    new StringBuilder(
                        "Oups, something bad happened while retrieving current information on the context of the hooked application: ")
                        .append(packageName).toString(), e);
          }
        }
        return packageName;
      }
    }, oldPointer);

  }

}
