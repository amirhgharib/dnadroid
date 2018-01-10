package com.dnadroid.hooker.reporting;

import com.dnadroid.hooker.common.InterceptEvent;
public abstract class AbstractReporter {

  private String idXp = "0";
  
  public void reportEvent(InterceptEvent event) {
    event.setIDXP(this.getIdXp());
    this.report(event);    
  }
  protected abstract void report(InterceptEvent parcelableEvent);
  
  /**
   * @return the idXp
   */
  public String getIdXp() {
    return idXp;
  }

  /**
   * @param idXp the idXp to set
   */
  public void setIdXp(String idXp) {
    this.idXp = idXp;
  }

}
