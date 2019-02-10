# SuperPixelGrid
Lab for exploring 2D arrays.  Comes with an executable jar (verified under Java 8) to illustrate how the finished tool is intended to function.

Version 2 requires Princeton University's Standard Libraries (Robert Sedgewick and Kevin Wayne).  A single jar file, stdlib.jar, may be downloaded from https://introcs.cs.princeton.edu/java/stdlib/ and placed on the classpath.  More information is available on the link.

Documentation (API) is available at https://apcsjava.github.io/SuperPixelGrid/

NOTE FOR MacOS - Recent versions of MacOS respond to an extended key press by bringing up a context menu for selection of special accent characters rather than the traditional implementation that sends repeated keypress signals.  Short key presses are therefore unaffected but longer key holds will render the key listener useless.  To disable the character accent menu and enable auto-repeat, type the following at the command prompt:

    defaults write -g ApplePressAndHoldEnabled -bool false

This can be reversed with the following:

    defaults write -g ApplePressAndHoldEnabled -bool true 

