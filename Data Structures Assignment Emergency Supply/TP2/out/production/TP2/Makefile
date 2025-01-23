# Makefile for Emergency Supply Network Project

# Compiler and Executable
JAVAC = javac
JAVA = java

# JSON Library
CLASSPATH = .;gson-2.10.1.jar

# Source Files
SRC = EmergencySupplyNetwork.java ResourceRedistribution.java DynamicResourceSharing.java NetworkApp.java

# Targets
.PHONY: all clean run

# Default target
all: compile

# Compile all Java files
compile:
	$(JAVAC) -cp $(CLASSPATH) $(SRC)

# Run the program
run:
	$(JAVA) -cp $(CLASSPATH) NetworkApp TestCase1.txt

# Clean compiled files
clean:
	del *.class

