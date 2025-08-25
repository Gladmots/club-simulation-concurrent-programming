# ClubSimulation:
![App Screenshot](https://github.com/Gladmots/club-simulation-concurrent-programming/blob/main/clubSimulation_Concurrent%20Programming/Screenshot%202025-08-24%20194719.png?raw=1)

## Description
This Java application simulates the behavior of patrons in a club using multithreading. Patrons enter the club, move around, interact with the bar, dance, and eventually leave. The simulation includes a graphical user interface (GUI) to visualize the patrons' movements and actions within the club.

##Contents
Getting Started
Dependencies/ Installation
Usage: How to run the classes
Classes
GUI and User Interaction
Threading and Synchronization
Challenges and Considerations

## Getting Started

### Dependencies/ Installation

- Java Development Kit (JDK) installed on your system.
- A Java development environment (e.g., Eclipse, IntelliJ IDEA) or command-line tools.

## Usage: How to run the classes

#Compile the classes:
javac clubSimulation/*.java
#Run the main class:
$ java clubSimulation.ClubSimulation
The simulation GUI will open, showing the movement and actions of the patrons within the club. You can use the "Start," "Pause," and "Quit" buttons to control the simulation.

## Classes
The simulation consists of the following classes:

ClubSimulation: The main class that initializes and manages the simulation.
Clubgoer: Represents individual patrons in the club.
PeopleLocation: Manages the locations of patrons and important areas in the club.
PeopleCounter: Tracks the number of people inside and outside the club, along with other metrics.
ClubView: Displays the club's layout and patrons' movements graphically.
ClubGrid: Represents the layout of the club.
CounterDisplay: Displays counters for various metrics in the GUI.
GUI and User Interaction

## GUI and User Interaction

When executed simulation GUI will open, the club will be displayed alsp showing the patrons. Use the following buttons to interact with the simulation:
Start: To initiate the simulation and starts the movement of patrons.
Pause: Pauses the simulation, stops the patorns from moving
Quit: Exits the simulation.

## Threading and Synchronization

The simulation uses multithreading to simulate the behavior of multiple patrons concurrently.
Synchronization mechanisms, such as synchronized methods and wait()/notifyAll() are used.
The AtomicBoolean pauseFlag is used to manage the pausing and resuming of all threads simultaneously.
