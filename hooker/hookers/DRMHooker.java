package com.dnadroid.hooker.hookers;

import java.util.HashMap;
import java.util.Map;

import com.dnadroid.hooker.SubstrateMain;
import com.dnadroid.hooker.exceptions.HookerInitializationException;

/**
 * @author Georges Bossert
 * 
 */
public class DRMHooker extends Hooker {

  public static final String NAME = "DRM";

  public DRMHooker() {
    super(DRMHooker.NAME);
  }


  @Override
  public void attach() {    
    attachOnDrmManagerClientClass();
  }
  
  /**
   * Attach on DrmManagerClient class
   */
  private void attachOnDrmManagerClientClass() {
    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("acquireDrmInfo", 0);
    methodsToHook.put("acquireRights", 0);
    methodsToHook.put("convertData", 0);
    methodsToHook.put("getMetadata", 0);
    methodsToHook.put("processDrmInfo", 0);
    methodsToHook.put("removeRights", 0);
    methodsToHook.put("saveRights", 0);
    
    try {
      hookMethods(null, "android.drm.DrmManagerClient",
        methodsToHook);
      SubstrateMain.log("hooking android.drm.DrmManagerClient methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.drm.DrmManagerClient methods has failed", e);
    }
    
  } 

  
}
