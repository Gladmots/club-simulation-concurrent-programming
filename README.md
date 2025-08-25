# Club Simulation with Concurrent Programming in Java
## Overview
The Club simulation project aims to demonstrate concurrent programming concepts through a graphical user interface (GUI) developed with Java Swing. It simulates the behavior of patrons in a club using multithreading. Patrons enter the club, move around, interact with the bar, dance, and eventually leave. The simulation includes a graphical user interface (GUI) to visualize the patrons' movements and actions within the club.

![App Screenshot](https://github.com/Gladmots/club-simulation-concurrent-programming/blob/main/clubSimulation_Concurrent%20Programming/Screenshot%202025-08-24%20194719.png?raw=1)

## Features
- GUI Interface: Utilizes Java Swing for a user-friendly interface.
- Concurrency: Implements multithreading to manage concurrent tasks within the SClub simulation.
- Event Handling: Manages user interactions and internal events efficiently.
- Data Structures: Utilizes Java data structures to store and manage club-related data.
- Simulation Logic: Simulates various club operations and activities concurrently.
- Technologies Used
- Java: Backend logic and multithreading.
- Java Swing: Graphical interface.
- Concurrency Utilities: Java's built-in concurrency utilities for managing threads and tasks.

## Getting Started

### Dependencies/ Installation

- Java Development Kit (JDK) installed on your system.
- A Java development environment (e.g., Eclipse, IntelliJ IDEA) or command-line tools.

## Usage: How to run the classes

### Compile the classes:
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
