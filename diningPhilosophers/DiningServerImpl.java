/*
 * Names : Michael Kim, Brandon Quach
 * Student ID : 025633788, 029133232
 * Class : CECS 326  
 * Date : 03/15/2024
 * Project 2 
 * 
 * DiningServerImpl.java
 *
 * This class contains the methods called by the philosophers.
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DiningServerImpl implements DiningServer
{
    // Array of locks representing the forks 
    private Lock[] forks;
    // Array of conditions indicates fork availability 
    private Condition[] forksAvailable;

    // Constructor to initialize forks and fork availablity for specfic numbers of philosophers
    // @param numPhilosophers: number of philosophers
    public DiningServerImpl(int numPhilosophers) {
        forks = new Lock[numPhilosophers];
        forksAvailable = new Condition[numPhilosophers];
        
        // Initialized locks and conditions for each fork
        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new ReentrantLock();
            forksAvailable[i] = forks[i].newCondition();
        }
    }

    @Override
    // Method to handle philosophers picking up forks to eat 
    // @param philNumber: unique indentifier of the philosopher
    public void takeForks(int philosopherID) {
        int leftFork = philosopherID;
        int rightFork = (philosopherID + 1) % forks.length;
        
        // Lock the left fork
        forks[leftFork].lock();
        // Lock the right fork
        forks[rightFork].lock();
    }

    @Override
    // Method to handle the Philosophers when they put down the forks after eating to think
    // @param is the unique identifier of the philosopher
    public void returnForks(int philosopherID) {
        int leftFork = philosopherID;
        int rightFork = (philosopherID + 1) % forks.length;
        
        // Unlock the left fork
        forks[leftFork].unlock();
        // Unlock the right fork
        forks[rightFork].unlock();
    }
}
