/*
 * Names : Michael Kim, Brandon Quach
 * Student ID : 025633788, 029133232
 * Class : CECS 326  
 * Date : 03/15/2024
 * Project 2 
 * 
 * DiningServer.java
 *
 * This class contains the methods called by the philosophers.
 */

 public interface DiningServer 
 {  
     // Called by a Philosopher when they wish to eat
     public void takeForks(int philosopher_Num);
   
     // Called by a Philosopher when they are finished eating
     public void returnForks(int philosopher_Num);
 }