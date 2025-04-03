/*
 * Names : Michael Kim, Brandon Quach
 * Student ID : 025633788, 029133232
 * Class : CECS 326  
 * Date : 03/15/2024
 * Project 2 
 *  
 * Philosopher.java
 *
 * This class represents each philosopher thread.
 * Philosophers alternate between eating and thinking.
 *
 */

public class Philosopher implements Runnable {
    // Unique indentifier for each philosopher
    private int philosopherNum; 
    // The dining server that manages resources
    private DiningServer diningSer; 

    // Constructor to initialize the philosopher's ID and the dining server
    // @param philosopheID: unique identifier for the philosopher
    //@param diningServer: the dining server managing resources
    public Philosopher(int philosopherNum, DiningServer diningSer) {
        this.philosopherNum = philosopherNum;
        this.diningSer = diningSer;
    }


    @Override
    public void run() {
        while (true) {
            // Randomly choose between 1, 2, or 3 seconds (1000, 2000, or 3000 milliseconds) for thinking and eating
            int thinkingDuration = (int) (Math.random() * 3) + 1;
            int eatingDuration = (int) (Math.random() * 3) + 1;
            long thinkingTimeMillis = thinkingDuration * 1000;
            long eatingTimeMillis = eatingDuration * 1000;

            // Record the start time before the philosopher starts thinking
            long startTime = System.currentTimeMillis();

            // Indicate that the philosopher is thinking
            System.out.println("Philosopher " + philosopherNum + " is thinking for " + thinkingDuration + " seconds.");

            // Simulate thinking for random chosen duration 
            try {
                Thread.sleep(thinkingTimeMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Record the start time before the philosopher tries to pick up forks
            startTime = System.currentTimeMillis();

            //Attempt to acquire forks
            diningSer.takeForks(philosopherNum);

            // Print a message indicating which philosopher has the forks 
            System.out.println("Philosopher " + philosopherNum + " has the forks and is eating for " + eatingDuration + " seconds");
            
            // Simulate eating for the randomly chosen duration
            try {
                Thread.sleep(eatingTimeMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Here the Philosophers put down forks and think
            diningSer.returnForks(philosopherNum);
        }
    }
}