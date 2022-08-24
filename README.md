# Temporary Code Storage/Review Repository

The current code is a basic IO server implementation which spawns a thread to update each
registered IO server tag with an incrementing value.

Currently, for an unknown reason, the IOTag.setValueXYZ() methods do not work on additional threads.

This code can be executed by updating the information for your Ewon (ip address, username, password)
in the `flexy.properties` file, then running `mvn deploy` or using your IDE to
run `DEPLOY (RUN ON DEVICE, NO DEBUG)`.