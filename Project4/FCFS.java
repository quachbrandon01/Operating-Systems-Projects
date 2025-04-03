/*
 * Names : Michael Kim, Brandon Quach
 * Student ID : 025633788, 029133232
 * Class : CECS 326  
 * Date : 04/26/2024
 * Project 4 
 * 
 * First Come, First Served (FCFS) Algorithm
 * 
 */
 
 import java.util.List;

 public class FCFS implements Algorithm {
 
    // Scheduled list of tasks
    private List<Task> queue;
    
    // Queue of tasks are executed in the order
    public FCFS(List<Task> queue) {
        this.queue = queue;
    }
 
    // Tasks are executed in the order they were added
    @Override
    public void schedule() {
        while (!queue.isEmpty()) {
            // Selects the new task from top of queue
            Task nextTask = pickNextTask();
            // CPU.java simulates the task
            CPU.run(nextTask, nextTask.getBurst());
            // Status message to inform task has been completed
            System.out.println("Task " + nextTask.getName() + " completed." + "\n") ;
            // Completed task is removed from queue
            queue.remove(nextTask);
        }
    }
    
    // Handles the "first come" aspect of the algorithm by selecting next task
    @Override
    public Task pickNextTask() {
        // First task is selected in queue
        return queue.get(0);
    }
 }
 