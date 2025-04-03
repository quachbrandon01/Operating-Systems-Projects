/*
 * Names : Michael Kim, Brandon Quach
 * Student ID : 025633788, 029133232
 * Class : CECS 326  
 * Date : 04/26/2024
 * Project 4 
 * Non-preemptive priority scheduling algorithm using RR.
 *
 * This algorithm will run tasks according to round-robin scheduling.
 */
 
 import java.util.List;
 import java.util.LinkedList;
 import java.util.Queue;
 
 public class RR implements Algorithm {
    // queue of tasks we are going to schedule
    private Queue<Task> queue;
    
    // Initialize the RR scheduler with list of tasks
    // Added to the queue are scheduled using Round Robin
    public RR(List<Task> taskList) {
        this.queue = new LinkedList<>(taskList);
    }

    // Method to invotke the RR scheduler and execute the tasks  with the same priority
    // Executed in a round-robin schedule with the quantum time of 10 
    @Override
    public void schedule() {
        while (!queue.isEmpty()) {
            // Pick the next task based on the scheduling algorithm 
            Task nextTask = pickNextTask();
            // time quantum is 10
            int timeSlice = 10;  
            // simulate running the task in the CPU with the specified time quantum
            CPU.run(nextTask, timeSlice);
            // Check if the task has any remaining burst time
            if (nextTask.getBurst() > timeSlice) {
                // Update the tasks burst time for the next round 
                nextTask.setBurst(nextTask.getBurst() - timeSlice);
                // Move the task to the end of the queue for the next round   
                queue.offer(queue.poll());
                
            } else {
                // Print out the message when a task is finished when no burst time is left
                System.out.println("Task " + nextTask.getName() + " finished." + "\n") ;
                // Task is complete, remove it from the queue
                queue.poll(); 
            }
        }
    }
    // Method to pick the next task for execution 
    @Override
    public Task pickNextTask() {
        // For RR, simply pick the first task in the queue
        return queue.peek();
    }
}
 