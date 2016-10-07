# elephant-java
Java implementation of motion behaviour of LEGO Mindstorms' elephant model, based on ev3dev platform.

Uses `com.github.jabrena:ev3dev-lang-java:1.0.0-SNAPSHOT` as ev3dev driver layer. Make sure that `ev3dev-lang-java-1.0.0-SNAPSHOT.jar` is in your classpath.

Compile by: 

`mvn package`

To run the package on EV3 controller running ev3dev, type:

`sudo java -cp elephant-1.0-SNAPSHOT.jar:ev3dev-lang-java-1.0.0-SNAPSHOT.jar com.smtsoftware.legoguild.elephant.App`
