package com.dnadroid.hooker.hookers;

import java.util.HashMap;
import java.util.Map;

import com.dnadroid.hooker.SubstrateMain;
import com.dnadroid.hooker.exceptions.HookerInitializationException;

/**
 * Hooks for Contents data which is permitted by ContentProvider and ContentResolver classes.
 * @author Dimitri Kirchner
 *
 */
public class ContentsDataHooker extends Hooker {

	/**
   * Name of the hooker
   */
  private static final String NAME_HOOKER = "ContentsData";

  /**
   * @param name
   */
  public ContentsDataHooker() {
    super(NAME_HOOKER);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.dnadroid.hooker.hookers.Hooker#attach()
   */
  @Override
  public void attach() {
    this.attachOnContentProviderClass();
    this.attachOnContentResolverClass();
    this.attachOnCursorWrapperClass();
    this.attachOnSQLiteCursorClass();
    this.attachOnContextWrapperClass();
  }
	
  /**
   * Hook calls to specific methods of the ContentProvider class.
   * Do NOT hook methods query/insert/delete/update since they are abstract.
   */
  private void attachOnContentProviderClass() {
    Map<String, Integer> methodsFromLocationToHook = new HashMap<String, Integer>();
    methodsFromLocationToHook.put("applyBatch", 2);
    methodsFromLocationToHook.put("bulkInsert", 2);
    methodsFromLocationToHook.put("call", 2);
    methodsFromLocationToHook.put("dump", 1);
    methodsFromLocationToHook.put("getWritePermission", 0);
    methodsFromLocationToHook.put("getReadPermission", 0);
    methodsFromLocationToHook.put("getPathPermissions", 0);
    methodsFromLocationToHook.put("openAssetFile", 1);
    methodsFromLocationToHook.put("openFile", 1);
    methodsFromLocationToHook.put("openPipeHelper", 1);
    methodsFromLocationToHook.put("openTypedAssetFile", 1);

    //These methods are protected.
//    methodsFromLocationToHook.put("setPathPermissions", 2);
//    methodsFromLocationToHook.put("setReadPermission", 2);
//    methodsFromLocationToHook.put("setWritePermission", 2);

    try {
      hookMethods(null, "android.content.ContentProvider",
          methodsFromLocationToHook);
      SubstrateMain.log("hooking android.content.ContentProvider methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.content.ContentProvider methods has failed", e);
    }
    
  }
  
  /**
   * Hook calls to specific methods of the ContentResolver class
   */
  private void attachOnContentResolverClass() {
  	Map<String, Integer> methodsFromLocationToHook = new HashMap<String, Integer>();
    methodsFromLocationToHook.put("acquireContentProviderClient", 0);
    methodsFromLocationToHook.put("acquireUnstableContentProviderClient", 0);
    methodsFromLocationToHook.put("addPeriodicSync", 0);
    methodsFromLocationToHook.put("applyBatch", 2);
    methodsFromLocationToHook.put("call", 1);
    methodsFromLocationToHook.put("delete", 2);
    methodsFromLocationToHook.put("getStreamTypes", 0);
    methodsFromLocationToHook.put("getType", 0);
    methodsFromLocationToHook.put("insert", 2);
    methodsFromLocationToHook.put("notifyChange", 1);
    methodsFromLocationToHook.put("openAssetFileDescriptor", 1);
    methodsFromLocationToHook.put("openFileDescriptor", 1);
    methodsFromLocationToHook.put("openInputStream", 1);
    methodsFromLocationToHook.put("openOutputStream", 2);
    methodsFromLocationToHook.put("openTypedAssetFileDescriptor", 1);
    methodsFromLocationToHook.put("query", 1);
    methodsFromLocationToHook.put("registerContentObserver", 2);
    methodsFromLocationToHook.put("requestSync", 0);
    methodsFromLocationToHook.put("unregisterContentObserver", 2);
    methodsFromLocationToHook.put("update", 2);

    try {
      hookMethods(null, "android.content.ContentResolver",
          methodsFromLocationToHook);
      
      SubstrateMain.log("hooking android.content.ContentResolver methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.content.ContentResolver methods has failed", e);
    }
  }
  
  /**
   * Attach on CursorWrapper class.
   */
  private void attachOnCursorWrapperClass () {
  	
  	Map<String, Integer> methodsFromLocationToHook = new HashMap<String, Integer>();
    methodsFromLocationToHook.put("close", 1);
    methodsFromLocationToHook.put("copyStringToBuffer", 2);
    
    methodsFromLocationToHook.put("getBlob", 0);
    methodsFromLocationToHook.put("getColumnCount", 0);
    methodsFromLocationToHook.put("getColumnIndex", 0);
    methodsFromLocationToHook.put("getColumnIndexOrThrow", 0);
    methodsFromLocationToHook.put("getColumnName", 0);
    methodsFromLocationToHook.put("getColumnNames", 0);
    methodsFromLocationToHook.put("getPosition", 0);
    methodsFromLocationToHook.put("getString", 0);
    methodsFromLocationToHook.put("registerContentObserver", 2);
    methodsFromLocationToHook.put("registerDataSetObserver", 2);
    methodsFromLocationToHook.put("requery", 1);
    methodsFromLocationToHook.put("respond", 1);
    methodsFromLocationToHook.put("setNotificationUri", 2);
    methodsFromLocationToHook.put("unregisterContentObserver", 2);
    
    try {
      hookMethods(null, "android.database.CursorWrapper",
          methodsFromLocationToHook);
      
      SubstrateMain.log("hooking android.database.CursorWrapper methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.database.CursorWrapper methods has failed", e);
    }
  }
  
  /**
   * Attach on CursorWrapper class.
   */
  private void attachOnSQLiteCursorClass () {
  
  	Map<String, Integer> methodsFromLocationToHook = new HashMap<String, Integer>();
    methodsFromLocationToHook.put("close", 1);
    methodsFromLocationToHook.put("getColumnIndex", 1);
    methodsFromLocationToHook.put("getColumnNames", 1);
    methodsFromLocationToHook.put("getCount", 0);
    methodsFromLocationToHook.put("getDatabase", 1);
    methodsFromLocationToHook.put("onMove", 0);
    methodsFromLocationToHook.put("setSelectionArguments", 2);
    methodsFromLocationToHook.put("setWindow", 1);
    
    try {
      hookMethods(null, "android.database.sqlite.SQLiteCursor",
          methodsFromLocationToHook);
      
      SubstrateMain.log("hooking android.database.sqlite.SQLiteCursor methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.database.sqlite.SQLiteCursor methods has failed", e);
    } 
  }
  
  private void attachOnContextWrapperClass() {
    Map<String, Integer> methodsFromLocationToHook = new HashMap<String, Integer>();
    
    methodsFromLocationToHook.put("getContentResolver", 1);
    
    try {
      hookMethods(null, "android.content.ContextWrapper",
          methodsFromLocationToHook);
      SubstrateMain.log("hooking android.content.ContextWrapper methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.content.ContextWrapper methods has failed", e);
    }
  }
}
