package com.dnadroid.hooker.hookers;

import java.util.HashMap;
import java.util.Map;

import com.dnadroid.hooker.SubstrateMain;
import com.dnadroid.hooker.exceptions.HookerInitializationException;

public class TelephonyHooker extends Hooker {

  public static final String NAME = "Telephony";

  public TelephonyHooker() {
    super(TelephonyHooker.NAME);
  }


  @Override
  public void attach() {    
    attachOnTelephonyManagerClass();
    
    attachOnServiceStateClass();
    
    attachOnSmsMessageClass();
    
    attachOnSmsManagerClass();
  }
  
  /**
   * Hook main methods of the SmsManager class
   */
  private void attachOnSmsManagerClass() {

    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("sendDataMessage", 2);
    methodsToHook.put("sendMultipartTextMessage", 2);
    methodsToHook.put("sendTextMessage", 2);    
    
    try {
      hookMethods(null, "android.telephony.SmsManager",
        methodsToHook);
      SubstrateMain.log("hooking android.telephony.SmsManager methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.telephony.SmsManager methods has failed", e);
    }
  }
  
  /**
   * Hook main methods of the ServiceState class
   */
  private void attachOnServiceStateClass() {

    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("getIsManualSelection", 1);
    methodsToHook.put("getOperatorAlphaLong", 1);
    methodsToHook.put("getOperatorAlphaShort", 1);
    
    methodsToHook.put("getOperatorNumeric", 1);
    methodsToHook.put("getRoaming", 1);
    methodsToHook.put("getState", 1);
    
    methodsToHook.put("setIsManualSelection", 2);
    methodsToHook.put("setOperatorName", 2);
    methodsToHook.put("setRoaming", 2);
    
    methodsToHook.put("setState", 2);
    methodsToHook.put("setStateOff", 2);
    methodsToHook.put("setStateOutOfService", 2);
    
    try {
      hookMethods(null, "android.telephony.ServiceState",
        methodsToHook);
      SubstrateMain.log("hooking android.telephony.ServiceState methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.telephony.ServiceState methods has failed", e);
    }
  }

  /**
   * Hook main methods of the SmsMessage class
   */
  private void attachOnSmsMessageClass() {

    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("createFromPdu", 2);
    
    methodsToHook.put("getDisplayMessageBody", 1);
    methodsToHook.put("getDisplayOriginatingAddress", 21);
    methodsToHook.put("getEmailBody", 1);
    methodsToHook.put("getEmailFrom", 1);
    methodsToHook.put("getIndexOnIcc", 1);
    methodsToHook.put("getIndexOnSim", 1);
    methodsToHook.put("getMessageBody", 1);
    methodsToHook.put("getOriginatingAddress", 1);
    methodsToHook.put("getPdu", 1);
    methodsToHook.put("getProtocolIdentifier", 1);
    methodsToHook.put("getPseudoSubject", 1);
    methodsToHook.put("getServiceCenterAddress", 1);
    methodsToHook.put("getStatus", 1);
    methodsToHook.put("getStatusOnIcc", 1);
    methodsToHook.put("getStatusOnSim", 1);
    methodsToHook.put("getSubmitPdu", 1);
    methodsToHook.put("getUserData", 1);
    
    try {
      hookMethods(null, "android.telephony.SmsMessage",
        methodsToHook);
      SubstrateMain.log("hooking android.telephony.SmsMessage methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.telephony.SmsMessage methods has failed", e);
    }
    
    try {
      hookMethods(null, "android.telephony.gsm.SmsMessage",
        methodsToHook);
      SubstrateMain.log("hooking android.telephony.gsm.SmsMessage methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.telephony.gsm.SmsMessage methods has failed", e);
    }
  }
  
  /**
   * Hook main methods of the telephony manager class
   */
  private void attachOnTelephonyManagerClass() {

    Map<String, Integer> methodsFromTelephonyManagerToHook = new HashMap<String, Integer>();

    methodsFromTelephonyManagerToHook.put("getCellLocation", 1);
    methodsFromTelephonyManagerToHook.put("getSubscriberId", 1);
    methodsFromTelephonyManagerToHook.put("getDeviceId", 1);
    methodsFromTelephonyManagerToHook.put("getDeviceSoftwareVersion", 1);
    methodsFromTelephonyManagerToHook.put("getNeighboringCellInfo", 1);
    methodsFromTelephonyManagerToHook.put("getNetworkCountryIso", 1);
    methodsFromTelephonyManagerToHook.put("getNetworkOperator", 1);
    methodsFromTelephonyManagerToHook.put("getNetworkOperatorName", 1);
    methodsFromTelephonyManagerToHook.put("getLine1Number", 1);
    
    methodsFromTelephonyManagerToHook.put("getAllCellInfo", 1);
    methodsFromTelephonyManagerToHook.put("getCallState", 1);
    methodsFromTelephonyManagerToHook.put("getGroupIdLevel1", 1);    
    
    methodsFromTelephonyManagerToHook.put("getNetworkType", 1);
    methodsFromTelephonyManagerToHook.put("getPhoneType", 1);
    methodsFromTelephonyManagerToHook.put("getSimCountryIso", 1);
    methodsFromTelephonyManagerToHook.put("getSimOperator", 1);
    methodsFromTelephonyManagerToHook.put("getSimOperatorName", 1);
    methodsFromTelephonyManagerToHook.put("getSimSerialNumber", 1);
    methodsFromTelephonyManagerToHook.put("getSimState", 1);
    methodsFromTelephonyManagerToHook.put("getVoiceMailNumber", 1);
    methodsFromTelephonyManagerToHook.put("isNetworkRoaming", 1);
    
    Map<String, Object> outputs = new HashMap<String, Object>();
    outputs.put("getDeviceId", "134679718293842");
    
    try {
      hookMethodsWithOutputs(null, "android.telephony.TelephonyManager",methodsFromTelephonyManagerToHook, outputs);
      SubstrateMain.log("hooking android.telephony.TelephonyManager methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking android.telephony.TelephonyManager methods has failed", e);
    }
  }

}
