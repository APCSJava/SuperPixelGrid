# SuperPixelGrid
Lab for exploring 2D arrays.  Comes with an executable jar (verified under Java 8) to illustrate how the finished tool is intended to function.

Version 2 requires Princeton University's Standard Libraries (Robert Sedgewick and Kevin Wayne).  A single jar file, stdlib.jar, may be downloaded from https://introcs.cs.princeton.edu/java/stdlib/ and placed on the classpath.  More information is available on the link.

Documentation (API) is available at https://apcsjava.github.io/SuperPixelGrid/

NOTE: MacOS - Recent versions of MacOS respond to an extended key press (ie. key held down) by bringing up a context menu for selection of special accent characters rather than sending a series of repeated keypress signals.  While short key presses are  unaffected, sustained key presses will behave erratically.  To avoid this outcome, disable the character accent menu and enable auto-repeat by entering the following at the command prompt:

    defaults write -g ApplePressAndHoldEnabled -bool false

The change may be reversed later with the following command:

    defaults write -g ApplePressAndHoldEnabled -bool true 

