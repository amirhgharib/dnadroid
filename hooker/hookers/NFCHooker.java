package com.dnadroid.hooker.hookers;

import java.util.HashMap;
import java.util.Map;

import com.dnadroid.hooker.SubstrateMain;
import com.dnadroid.hooker.exceptions.HookerInitializationException;

/**
 * @author Georges Bossert
 * 
 */
public class NFCHooker extends Hooker {

  public static final String NAME = "NFC";

  public NFCHooker() {
    super(NFCHooker.NAME);
  }


  @Override
  public void attach() {    
    attachOnNfcAdapterClass();
    attachOnNdefRecordClass();
    attachOnNdefMessageClass();
  }
  
  /**
   * Attach on NfcAdapter class
   */
  private void attachOnNfcAdapterClass() {
    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("disableForegroundDispatch", 2);
    methodsToHook.put("disableForegroundNdefPush", 2);
    methodsToHook.put("disableReaderMode", 2);
    methodsToHook.put("enableForegroundDispatch", 2);
    methodsToHook.put("enableForegroundNdefPush", 2);
    methodsToHook.put("enableReaderMode", 2);
    methodsToHook.put("isEnabled", 1);
    methodsToHook.put("isNdefPushEnabled", 2);
    methodsToHook.put("setBeamPushUris", 2);
    methodsToHook.put("setNdefPushMessage", 2);
    methodsToHook.put("enableForegroundDispatch", 2);
    
    
    try {
      hookMethods(null, "android.nfc.NfcAdapter",
        methodsToHook);
      SubstrateMain.log("hooking android.nfc.NfcAdapter methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.nfc.NfcAdapter methods has failed", e);
    }
    
  } 
  
  /**
   * Attach on NdefRecord class
   */
  private void attachOnNdefRecordClass() {
    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("NdefRecord", 2);
    methodsToHook.put("createApplicationRecord", 2);
    methodsToHook.put("createExternal", 2);
    methodsToHook.put("createMime", 2);
    methodsToHook.put("createUri", 2);
    methodsToHook.put("getId", 1);
    methodsToHook.put("getPayload", 1);
    methodsToHook.put("getTnf", 1);
    methodsToHook.put("getType", 1);
    methodsToHook.put("toMineType", 1);
    methodsToHook.put("toByteArray", 1);
    
    try {
      hookMethods(null, "android.nfc.NfcAdapter",
        methodsToHook);
      SubstrateMain.log("hooking android.nfc.NfcAdapter methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.nfc.NfcAdapter methods has failed", e);
    }
    
  } 
  
  /**
   * Attach on NdefMessage class
   */
  private void attachOnNdefMessageClass() {
    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("NdefMessage", 2);
    methodsToHook.put("getRecords", 1);
    
    try {
      hookMethods(null, "android.nfc.NfcAdapter",
        methodsToHook);
      SubstrateMain.log("hooking android.nfc.NfcAdapter methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.nfc.NfcAdapter methods has failed", e);
    }
    
  } 
  

}
