package com.dnadroid.hooker.hookers;

import java.util.HashMap;
import java.util.Map;

import com.dnadroid.hooker.SubstrateMain;
import com.dnadroid.hooker.exceptions.HookerInitializationException;

/**
 * @author Georges Bossert
 * 
 */
public class TaskSchedulerHooker extends Hooker {

  public static final String NAME = "TaskScheduler";

  public TaskSchedulerHooker() {
    super(TaskSchedulerHooker.NAME);
  }


  @Override
  public void attach() {    
    attachOnTimerClass();
    
    attachOnScheduledThreadPoolExecutorClass();
    
  }

  /**
   * Attach on ScheduledThreadPoolExecutor
   */
  private void attachOnScheduledThreadPoolExecutorClass() {
    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("ScheduledThreadPoolExecutor", 0);
    methodsToHook.put("execute", 0);
    methodsToHook.put("schedule", 0);
    methodsToHook.put("scheduleAtFixedRate", 0);
    methodsToHook.put("scheduleWithFixedDelay", 0);
    methodsToHook.put("shutdown", 0);
    methodsToHook.put("shutdownNow", 0);
    methodsToHook.put("submit", 0);
    
    try {
      hookMethods(null, "java.util.concurrent.ScheduledThreadPoolExecutor",
        methodsToHook);
      SubstrateMain.log("hooking java.util.concurrent.ScheduledThreadPoolExecutor methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking java.util.concurrent.ScheduledThreadPoolExecutor methods has failed", e);
    }
    
  }
  /**
   * Attach on Timer class
   */
  private void attachOnTimerClass() {
    Map<String, Integer> methodsToHook = new HashMap<String, Integer>();

    methodsToHook.put("Timer", 0);
    methodsToHook.put("cancel", 0);
    methodsToHook.put("purge", 0);
    methodsToHook.put("schedule", 0);
    methodsToHook.put("scheduleAtFixedRate", 0);
    
    try {
      hookMethods(null, "java.util.Timer",
        methodsToHook);
      SubstrateMain.log("hooking java.util.Timer methods sucessful");

    } catch (HookerInitializationException e) {
      SubstrateMain.log("hooking java.util.Timer methods has failed", e);
    }
    
  }
  
  
  
}
