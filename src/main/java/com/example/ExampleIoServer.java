package com.example;

import com.ewon.ewonitf.IOServer;
import com.ewon.ewonitf.IOTag;
import com.ewon.ewonitf.IOValue;
import java.util.ArrayList;
import java.util.List;

public class ExampleIoServer extends IOServer {

  private final List tagList = new ArrayList();

  private int valueCounter = 0;

  public ExampleIoServer() {
    // Call super constructor
    super();

    // Set up IO Server
    setServerName("TESTING");

    // Create and start value update thread
    new Thread(
            new Runnable() {
              public void run() {
                // Loop and update tag values until 1000
                while (valueCounter < 1000) {
                  // Set all tags in tag list to value counter
                  System.out.println(
                      "Setting all TESTING IO Server tags to value: " + valueCounter);
                  for (int x = 0; x < tagList.size(); x++) {
                    IOTag tag = (IOTag) tagList.get(x);
                    try {
                      tag.setValueInt32(valueCounter);
                    } catch (Exception e) {
                      throw new RuntimeException(e);
                    }
                  }

                  // Increment value counter
                  valueCounter++;

                  // Sleep for 2 seconds
                  try {
                    Thread.sleep(2000);
                  } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                  }
                }
              }
            })
        .start();
  }

  public void onPassConfig(boolean applyConfig, boolean checkConfig) throws Exception {
    // DO NOTHING
  }

  public IOTag onGetIoInfo(String topicName, String ioName) throws Exception {
    // Create IO tag object with int32 type
    IOTag ioTag = null;
    if (ioName != null && ioName.length() != 0) {
      ioTag = new IOTag(topicName, ioName, IOValue.DATATYPE_INT32, true);
    }
    return ioTag;
  }

  public void onAdviseIo(IOTag ioTag) throws Exception {
    // Add tag to list
    tagList.add(ioTag);

    // Set initial value and good quality
    ioTag.setValueInt32(valueCounter);
    ioTag.setQuality(IOTag.QUALITY_GOOD);
  }

  public void onUnadviseIo(IOTag ioTag) throws Exception {
    // Remove tag from list
    tagList.remove(ioTag);
  }
}
