/*
 * Names : Michael Kim, Brandon Quach
 * Student ID : 025633788, 029133232
 * Class : CECS 326  
 * Date : 04/26/2024
 * Project 4 
 * 
 * Handles different scheduling algorithms.
 * algorithm = [FCFS, PRI, RR]
 * 
 */
  
 import java.util.*;
 import java.io.*;
 
 public class Driver
 {
    // Error handling  
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java Driver <algorithm> <schedule>");
            System.exit(0);
         }
 
        BufferedReader inFile = new BufferedReader(new FileReader(args[1]));
 
        String schedule;
 
        // Array list for the queue of tasks
        List<Task> queue = new ArrayList<Task>();
 
        // Opens text file to read in the tasks and initialize the ready queue        
        while ((schedule = inFile.readLine()) != null) {
            String[] param = schedule.split(",\\s*");
            queue.add(new Task(param[0], Integer.parseInt(param[1]), Integer.parseInt(param[2])));
         }
        
        // Closes the file
        inFile.close();
         
        Algorithm scheduler = null;
        String algchoice = args[0].toUpperCase();
         
        // Switch cases for the chosen algorithm
        switch(algchoice) {
            // First-come, First-served
            case "FCFS":
                scheduler = new FCFS(queue);
                break;
            // Priority scheduling
            case "PRI":
                scheduler = new Priority(queue);
                break;
            // Round-robin
            case "RR":
                scheduler = new RR(queue);
                break;
            // Default case
            default:
                System.err.println("Invalid algorithm. Please try again.");
                System.exit(0);
         }
 
        // Start the scheduler
        scheduler.schedule();
     }
 }