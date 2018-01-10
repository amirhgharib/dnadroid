package com.dnadroid.hooker.hookers.interfaces;

import java.lang.reflect.GenericDeclaration;

import com.dnadroid.hooker.common.InterceptEvent;

public interface HookerListener {

  /**
   * @param className
   * @param pMethod
   * @param resources
   * @param event
   */
  void before(String className, GenericDeclaration pMethod, Object resources, InterceptEvent event);

  /**
   * @param className
   * @param pMethod
   * @param resources
   * @param event
   */
  void after(String className, GenericDeclaration pMethod, Object resources, InterceptEvent event);

}
