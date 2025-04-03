/*
 * Names : Michael Kim, Brandon Quach
 * Student ID : 025633788, 029133232
 * Class : CECS 326  
 * Date : 03/15/2024
 * Project 2 
 * 
 * DiningPhilosophers.java
 *
 * This program starts the dining philosophers problem.
 */


 public class DiningPhilosophers {
    public static void main(String args[]) {
        // Storing the number of philosophers
        int numPhilosophers = 5; 
        // Dining server implementation
        DiningServer diningServ = new DiningServerImpl(numPhilosophers);
        // Philosophers threads
        Thread[] philosophersThreads = new Thread[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            // Create Philosophers thread
            philosophersThreads[i] = new Thread(new Philosopher(i, diningServ));
            // Start Philosophers thread
            philosophersThreads[i].start();
        }
        // Wait for all Philosophers threads to finish
        for (int i = 0; i < numPhilosophers; i++) {
            // Waits for the Philosophers thread to finish
            try {
                philosophersThreads[i].join();
            } 
            // Handles interrupted exceptions
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}