package com.dnadroid.hooker.hookers;

import java.util.HashMap;
import java.util.Map;

import com.dnadroid.hooker.SubstrateMain;
import com.dnadroid.hooker.exceptions.HookerInitializationException;

/**
 * @author Georges Bossert
 * 
 */
public class GeolocationDataHooker extends Hooker {

  /**
   * Name of the hooker
   */
  private static final String NAME_HOOKER = "Geolocation";

  /**
   * @param name
   */
  public GeolocationDataHooker() {
    super(NAME_HOOKER);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.dnadroid.hooker.hookers.Hooker#attach()
   */
  @Override
  public void attach() {

    this.attachOnLocationClass();
    
    this.attachOnGpsSatelliteClass();

    this.attachOnLocationManagerClass();    
    
  }

  /**
   * Hook calls to specific methods of the LocationManager class
   */
  private void attachOnLocationManagerClass() {
    Map<String, Integer> methodsFromLocationToHook = new HashMap<String, Integer>();
    methodsFromLocationToHook.put("getLastKnownLocation", 1);    
    
    try {
      hookMethods(null, "android.location.LocationManager",
          methodsFromLocationToHook);
      SubstrateMain.log("hooking android.location.LocationManager methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.location.LocationManager methods has failed", e);
    }
    
  }

  /**
   * Hook calls to specific methods of the GpsSatellite class
   */
  private void attachOnGpsSatelliteClass() {
    Map<String, Integer> methodsFromLocationToHook = new HashMap<String, Integer>();
    methodsFromLocationToHook.put("getAzimuth", 1);    
    methodsFromLocationToHook.put("getElevation", 1);    
    methodsFromLocationToHook.put("getPrn", 0);    
    methodsFromLocationToHook.put("getSnr()", 0);    
    methodsFromLocationToHook.put("hasAlmanac", 0);    
    methodsFromLocationToHook.put("hasEphemeris", 0);
    
    try {
      hookMethods(null, "android.location.GpsSatellite",
          methodsFromLocationToHook);
      SubstrateMain.log("hooking android.location.GpsSatellite methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.location.GpsSatellite methods has failed", e);
    }
    
  }

  /**
   * Hook calls to specific methods of the location class
   */
  private void attachOnLocationClass() {
    Map<String, Integer> methodsFromLocationToHook = new HashMap<String, Integer>();
    methodsFromLocationToHook.put("getBearing", 1);
    
    methodsFromLocationToHook.put("getLatitude", 1);
    methodsFromLocationToHook.put("setLatitude", 2);
    
    methodsFromLocationToHook.put("getLongitude", 1);
    methodsFromLocationToHook.put("setLongitude", 2);
    
    methodsFromLocationToHook.put("getAltitude", 1);
    methodsFromLocationToHook.put("setAltitude", 2);
    
    methodsFromLocationToHook.put("getSpeed", 1);
    methodsFromLocationToHook.put("SetSpeed", 2);
    
    methodsFromLocationToHook.put("getTime", 1);
    methodsFromLocationToHook.put("setTime", 2);
    
    methodsFromLocationToHook.put("hasBearing", 0);
    methodsFromLocationToHook.put("hasAccuracy", 0);
    methodsFromLocationToHook.put("hasAltitude", 0);
    methodsFromLocationToHook.put("hasSpeed", 0);

    try {
      hookMethods(null, "android.location.Location",
          methodsFromLocationToHook);
      SubstrateMain.log("hooking android.location.Location methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.location.Location methods has failed", e);
    }
  }

}
