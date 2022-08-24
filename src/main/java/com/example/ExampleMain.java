package com.example;

import com.ewon.ewonitf.DefaultEventHandler;
import com.ewon.ewonitf.IOServer;

public class ExampleMain {
  public static void main(String[] args) throws Exception {
    // Start log
    System.out.println("Starting TESTING IO Server");

    // Start IO server
    IOServer ioServer = new ExampleIoServer();
    ioServer.registerServer();
    DefaultEventHandler.addIOServerListener(ioServer);
    DefaultEventHandler.runEventManager();

    // Close log
    System.out.println("Closing TESTING IO Server");
  }
}
