@echo off
echo Creating JAR file for Student Registration System...

:: Compile the Java file
javac StudentRegistrationSystem.java

:: Create the JAR file
jar cfm StudentRegistrationSystem.jar META-INF/MANIFEST.MF *.class *.png

echo JAR file created successfully!
echo You can now run the application using: java -jar StudentRegistrationSystem.jar
pause 