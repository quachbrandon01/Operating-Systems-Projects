/*
 * Names : Michael Kim, Brandon Quach
 * Student ID : 025633788, 029133232
 * Class : CECS 326  
 * Date : 04/26/2024
 * Project 4 
 * 
 * Interface for handling scheduling algorithm.
 *
 */

 public interface Algorithm
 {
    // Calls the scheduler
    public abstract void schedule();
 
    // Next task using the chosen scheduling algorithm
    public abstract Task pickNextTask();
 }
 