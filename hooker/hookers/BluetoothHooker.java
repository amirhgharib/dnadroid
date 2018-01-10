package com.dnadroid.hooker.hookers;

import java.util.HashMap;
import java.util.Map;

import com.dnadroid.hooker.SubstrateMain;
import com.dnadroid.hooker.exceptions.HookerInitializationException;

/**
 * @author Georges Bossert
 * 
 */
public class BluetoothHooker extends Hooker {

  public static final String NAME = "Bluetooth";

  public BluetoothHooker() {
    super(BluetoothHooker.NAME);
  }


  @Override
  public void attach() {    
    attachOnBluetoothSocketClass();
    
    attachOnBluetoothServerSocketClass();
    
    attachOnBluetoothDeviceClass();
  }
  
  /**
   * Attach on BluetoothSocket class
   */
  private void attachOnBluetoothSocketClass() {
    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("connect", 2);
    
    try {
      hookMethods(null, "android.bluetooth.BluetoothSocket",
        methodsToHook);
      SubstrateMain.log("hooking android.bluetooth.BluetoothSocket methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.bluetooth.BluetoothSocket methods has failed", e);
    }
    
  }
  
  /**
   * Attach on BluetoothServerSocket class
   */
  private void attachOnBluetoothServerSocketClass() {
    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("accept", 2);
    methodsToHook.put("close", 2);
    
    try {
      hookMethods(null, "android.bluetooth.BluetoothServerSocket",
        methodsToHook);
      SubstrateMain.log("hooking android.bluetooth.BluetoothServerSocket methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.bluetooth.BluetoothServerSocket methods has failed", e);
    }
    
  }
  
  /**
   * Attach on BluetoothDevice class
   */
  private void attachOnBluetoothDeviceClass() {
    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("connectGatt", 2);
    methodsToHook.put("createBond", 2);
    methodsToHook.put("createInsecureRfcommSocketToServiceRecord", 2);
    methodsToHook.put("createRfcommSocketToServiceRecord", 2);
    methodsToHook.put("getAddress", 2);
    methodsToHook.put("getName", 2);
    methodsToHook.put("getType", 2);
    methodsToHook.put("setPin", 2);
    
    try {
      hookMethods(null, "android.bluetooth.BluetoothDevice",
        methodsToHook);
      SubstrateMain.log("hooking android.bluetooth.BluetoothDevice methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.bluetooth.BluetoothDevice methods has failed", e);
    }
    
  }
  
}
