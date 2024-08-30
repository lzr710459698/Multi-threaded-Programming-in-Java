import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Name: Felix Liu
Course: CNT 4714 Summer 2024
Assignment title: Project 1 – Multi-threaded programming in Java
Date: June 16, 2024
Class: CNT 4714
*/


public class Main {
    public static void main(String[] args) {
        System.out.println("Summer 2024 - Project 1 - Package Management Facility Simulator");
        System.out.println();
        System.out.println();
        System.out.println("***PACKAGE MANAGEMENT FACILITY SIMULATION BEGINS***");

        int stations = getStations();
        ArrayList<RoutingStation> factory = generateFactory(stations);
        ExecutorService executor = Executors.newFixedThreadPool(stations);

        try {
            for (int i = stations - 1; i >= 0; i--) {
                executor.execute(factory.get(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        executor.shutdown();
    }

    private static ArrayList<RoutingStation> generateFactory(int numStations) {
        File configFile = new File("config.txt");
        ArrayList<RoutingStation> factory = new ArrayList<>();

        try (FileReader fr = new FileReader(configFile);
             BufferedReader line = new BufferedReader(fr)) {
            line.readLine();  // Skip the first line

            for (int i = 0; i < numStations; i++) {
                int workload = Integer.parseInt(line.readLine());
                factory.add(new RoutingStation(i, workload));
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Config file not found or error reading config file.");
        }
        return factory;
    }

    public static int getStations() {
        File configFile = new File("config.txt");
        int numStations = -1;

        try (FileReader fr = new FileReader(configFile);
             BufferedReader line = new BufferedReader(fr)) {
            numStations = Integer.parseInt(line.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Config file not found or error reading config file.");
        }
        return numStations;
    }
}
