package com.dnadroid.hooker.reporting;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.dnadroid.hooker.SubstrateMain;
import com.dnadroid.hooker.common.InterceptEvent;

public class NetworkEventSender extends AbstractReporter {

  private String host;
  private int port;
  private String esDoctype;
  private String esIndex;

  // Events to send
  private Queue<InterceptEvent> toSend;

  // List of threads
  private List<NetworkEventSenderThread> threads = new ArrayList<NetworkEventSenderThread>();
  private int nbThread;

  /**
   * Default constructor
   */
  public NetworkEventSender(String host, int port, int nbThread, String esIndex, String esDoctype) {
    this.host = host;
    this.port = port;
    this.nbThread = nbThread;
    this.esIndex = esIndex;
    this.esDoctype = esDoctype;
    this.toSend = new ConcurrentLinkedQueue<InterceptEvent>();
    this.createThreads();

    SubstrateMain.log("Starting Network Event Sender");
  }


  @Override
  protected void report(InterceptEvent event) {
    if (event != null) {
      SubstrateMain.log("Network Event sender request its threads to send an event.");
      this.toSend.add(event);
    }
  }



  /**
   * Updates the target URI to which events are sent
   */
  private void createThreads() {
    this.stopThreads();

    // Waits 500ms before starting new threads
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    for (int iThread = 0; iThread < this.nbThread; iThread++) {
      this.startThread();
    }
  }

  /**
   * Create and register a new Thread
   */
  private void startThread() {
    NetworkEventSenderThread thread =
        new NetworkEventSenderThread(getHost(), getPort(), getEsIndex(), getEsDoctype(), toSend);
    this.threads.add(thread);

    Thread t = new Thread(thread);
    t.start();
  }

  /**
   * Stops all registered threads
   */
  private void stopThreads() {
    for (NetworkEventSenderThread thread : this.threads) {
      thread.stopThread();
    }
  }

  /**
   * @return the host
   */
  public String getHost() {
    return host;
  }

  /**
   * @return the port
   */
  public int getPort() {
    return port;
  }


  /**
   * @return the esDoctype
   */
  public String getEsDoctype() {
    return esDoctype;
  }

  /**
   * @return the esIndex
   */
  public String getEsIndex() {
    return esIndex;
  }



}
