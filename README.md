# Multi-threaded Programming in Java

This repository contains the source code for a multi-threaded Java application that simulates an automated package routing system in a logistics facility. It was developed for **CNT 4714 - Enterprise Computing** to demonstrate advanced Java concurrency concepts.

## 🧭 Overview

The program models a package routing system consisting of multiple **routing stations** connected via shared **conveyor segments**. Each station attempts to route packages concurrently, requiring synchronized access to avoid conflicts and deadlocks.

## 🚀 Features

- **Multi-threaded Design:**  
  Leverages the `java.util.concurrent` package to manage parallel operations between routing stations.

- **Deadlock Prevention:**  
  Implements a locking protocol using `ReentrantLock` to safely acquire conveyor segments and avoid circular waits.

- **Thread Pool Execution:**  
  Uses `ExecutorService` with a fixed thread pool to manage up to 10 routing station threads.

- **Configurable Input:**  
  Reads from `config.txt` to determine the number of stations and their routing behaviors.

- **Simulation Logging:**  
  Outputs real-time logs of routing activity to track station behavior and system throughput.

## ⚙️ Technologies Used

- Java 8 or higher  
- ReentrantLock  
- ExecutorService  
- File I/O for configuration parsing  

## 📂 Project Structure

```
Multi-threaded-Programming-in-Java/
├── Main.java               # Entry point; initializes simulation
├── RoutingStation.java     # Implements Runnable; represents a station
├── config.txt              # Input file with routing instructions
├── README.md               # This file
```

## 📄 How It Works

Each routing station:
1. Waits to acquire locks on its two adjacent conveyor segments.
2. Routes a package from one segment to another.
3. Releases the locks to allow others to proceed.
4. Repeats this for a predefined number of routing tasks.

This simulates real-world package routing behavior, with proper concurrency management to maximize system efficiency.

## ✅ Requirements

- JDK 8 or higher
- A `config.txt` file formatted as required by the simulation

## 📝 Example `config.txt`

```
4
1 2 4
2 3 3
3 4 5
4 1 2
```

- First line: number of routing stations  
- Subsequent lines: station ID, conveyor segments, and number of packages to route

## 📦 Compilation & Execution

```bash
javac Main.java RoutingStation.java
java Main
```

## 📚 About

This project was developed as part of **Project One: Multi-threaded Programming in Java** for the **Summer 2024** session of **CNT 4714 - Enterprise Computing**.

---
