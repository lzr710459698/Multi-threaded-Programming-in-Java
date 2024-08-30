Multi-threaded Programming in Java

This repository contains the code for a multi-threaded Java application that simulates an automated package routing system for a logistics operation. The project demonstrates the use of concurrency in Java to manage shared resources efficiently.

Overview

This project simulates a package routing system where multiple routing stations operate concurrently to move packages between conveyors in a logistics facility. The simulation ensures proper synchronization between stations to prevent deadlocks while maximizing throughput.

Features

Multi-threaded Design: Implements Java's java.util.concurrent package to manage concurrent operations across multiple routing stations.

Concurrency Control: Uses ReentrantLock to synchronize access to shared conveyor lines, ensuring no two adjacent stations work simultaneously, preventing deadlock scenarios.

Thread Pool Management: Utilizes ExecutorService with a fixed thread pool to efficiently manage up to 10 routing stations.
	
Configurable Simulation: Reads from a config.txt file to set the number of routing stations and their respective workloads dynamically.

Real-time Simulation Output: Provides detailed output to track the state and actions of each routing station.
 
Requirements

Java 8 or higher
