package com.dnadroid.hooker.hookers;

import java.util.HashMap;
import java.util.Map;

import com.dnadroid.hooker.SubstrateMain;
import com.dnadroid.hooker.exceptions.HookerInitializationException;

public class USBHooker extends Hooker {

  public static final String NAME = "USB";

  public USBHooker() {
    super(USBHooker.NAME);
  }


  @Override
  public void attach() {    
    attachOnUsbManagerClass();
    attachOnUsbDeviceConnection();    
  }
  
  /**
   * Hook main methods of the UsbDeviceConnection class
   */
  private void attachOnUsbDeviceConnection() {

    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("bulkTransfer", 0);
    methodsToHook.put("claimInterface", 0);
    methodsToHook.put("close", 0);
    methodsToHook.put("controlTransfer", 0);
    methodsToHook.put("getFileDescriptor", 0);
    methodsToHook.put("getSerial", 0);
    methodsToHook.put("getRawDescriptors", 0);
    methodsToHook.put("releaseInterface", 0);
    methodsToHook.put("requestWait", 0);
    
    try {
      hookMethods(null, "android.hardware.usb.UsbDeviceConnection",
        methodsToHook);
      SubstrateMain.log("hooking android.hardware.usb.UsbDeviceConnection methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.hardware.usb.UsbDeviceConnection methods has failed", e);
    }
  }

  /**
   * Hook main methods of the UsbManager class
   */
  private void attachOnUsbManagerClass() {

    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("getAccessoryList", 0);
    methodsToHook.put("getDeviceList", 0);
    methodsToHook.put("hasPermission", 0);
    methodsToHook.put("openAccessory", 0);
    methodsToHook.put("openDevice", 0);
    methodsToHook.put("requestPermission", 0);
    
    try {
      hookMethods(null, "android.hardware.usb.UsbManager",
        methodsToHook);
      SubstrateMain.log("hooking android.hardware.usb.UsbManager methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.hardware.usb.UsbManager methods has failed", e);
    }
  }
  
}
