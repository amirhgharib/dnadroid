package com.dnadroid.hooker.hookers;

import java.util.HashMap;
import java.util.Map;

import com.dnadroid.hooker.SubstrateMain;
import com.dnadroid.hooker.exceptions.HookerInitializationException;

/**
 * @author Georges Bossert
 * 
 */
public class SQLiHooker extends Hooker {

  public static final String NAME = "SQLi";

  public SQLiHooker() {
    super(SQLiHooker.NAME);
  }


  @Override
  public void attach() {    
    attachOnSQLiteDatabaseClass();
    attachOnContextWrapperClass();
    //Cursor functions are implemented in ContentsDataHooker.
  }
  
  /**
   * Attach on SQLiteDatabase class
   */
  private void attachOnSQLiteDatabaseClass() {
    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("create", 2);
    methodsToHook.put("delete", 2);
    methodsToHook.put("deleteDatabase", 2);
    methodsToHook.put("execSQL", 2);
    methodsToHook.put("getPath", 0);
    methodsToHook.put("insert", 2);
    methodsToHook.put("insertOrThrow", 2);
    methodsToHook.put("insertWithOnConflict", 2);
    
    methodsToHook.put("openDatabase", 2);
    methodsToHook.put("openOrCreateDatabase", 2);
    methodsToHook.put("query", 2);
    methodsToHook.put("queryWithFactory", 2);
    methodsToHook.put("rawQuery", 2);
    methodsToHook.put("rawQueryWithFactory", 2);
    methodsToHook.put("replace", 2);
    methodsToHook.put("replaceOrThrow", 2);
    methodsToHook.put("update", 2);
    methodsToHook.put("updateWithOnConflict", 2);
    methodsToHook.put("close", 0);
    
    try {
      hookMethods(null, "android.database.sqlite.SQLiteDatabase",
        methodsToHook);
      SubstrateMain.log("hooking android.database.sqlite.SQLiteDatabase methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.database.sqlite.SQLiteDatabaser methods has failed", e);
    }
    
  } 
  
  private void attachOnContextWrapperClass() {
  	 Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

     methodsToHook.put("deleteDatabase", 2);
     methodsToHook.put("databaseList", 1);
     methodsToHook.put("getDatabasePath", 1);
     methodsToHook.put("openOrCreateDatabase", 2);
     
     try {
       hookMethods(null, "android.content.ContextWrapper",
         methodsToHook);
       SubstrateMain.log("hooking android.content.ContextWrapper methods sucessful");

     } catch (HookerInitializationException e) {
       SubstrateMain.log("hooking android.content.ContextWrapper methods has failed", e);
     }
  }

  
}
