package com.dnadroid.hooker.hookers;

import java.util.HashMap;
import java.util.Map;

import com.dnadroid.hooker.SubstrateMain;
import com.dnadroid.hooker.exceptions.HookerInitializationException;

/**
 * @author Georges Bossert
 * 
 */
public class AccountsHooker extends Hooker {

  public static final String NAME = "Accounts";

  public AccountsHooker() {
    super(AccountsHooker.NAME);
  }


  @Override
  public void attach() {    
    attachOnAccountManagerClass();
    attachOnAccountClass();

  }
  
  /**
   * Attach on Account class
   */
  private void attachOnAccountClass() {
    
    final String className = "android.accounts.Account";
    
    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("Account", 0);    
    
    try {
      hookMethods(null, className,
        methodsToHook);
      SubstrateMain.log(new StringBuilder("hooking ").append(className).append(" methods sucessful").toString());

    } catch (HookerInitializationException e) {
      SubstrateMain.log(new StringBuilder("hooking ").append(className).append(" methods has failed").toString(), e);
    }
    
  } 
  
  /**
   * Attach on AccountManager class
   */
  private void attachOnAccountManagerClass() {
    
    final String className = "android.accounts.AccountManager";
    
    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("addAccount", 2);
    methodsToHook.put("addAccountExplicitly", 2);
    methodsToHook.put("addOnAccountsUpdatedListener", 1);
    methodsToHook.put("blockingGetAuthToken", 1);
    methodsToHook.put("clearPassword", 2);
    methodsToHook.put("confirmCredentials", 0);
    methodsToHook.put("editProperties", 2);
    methodsToHook.put("get", 0);
    methodsToHook.put("getAccounts", 1);
    methodsToHook.put("getAccountsByTypeAndFeatures", 1);
    methodsToHook.put("getAccountsByTypeForPackage", 1);
    methodsToHook.put("getAuthToken", 1);
    methodsToHook.put("getAuthTokenByFeatures", 1);
    methodsToHook.put("getAuthenticatorTypes", 1);
    methodsToHook.put("getPassword", 1);
    methodsToHook.put("getUserData", 1);
    methodsToHook.put("hasFeatures", 1);
    methodsToHook.put("invalidateAuthTokens", 2);
    methodsToHook.put("newChooseAccountIntent", 0);
    methodsToHook.put("peekAuthToken", 1);
    methodsToHook.put("removeAccount", 2);
    methodsToHook.put("removeOnAccountsUpdatedListener", 1);
    methodsToHook.put("setAuthToken", 2);
    methodsToHook.put("setPassword", 2);
    methodsToHook.put("setUserData", 2);
    methodsToHook.put("updateCredentials", 1);    
    
    try {
      hookMethods(null, className,
        methodsToHook);
      SubstrateMain.log(new StringBuilder("hooking ").append(className).append(" methods sucessful").toString());

    } catch (HookerInitializationException e) {
      SubstrateMain.log(new StringBuilder("hooking ").append(className).append(" methods has failed").toString(), e);
    }
    
  } 
  
}
