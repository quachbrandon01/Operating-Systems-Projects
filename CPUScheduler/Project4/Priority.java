/*
 * Names : Michael Kim, Brandon Quach
 * Student ID : 025633788, 029133232
 * Class : CECS 326  
 * Date : 04/26/2024
 * Project 4 
 * Non-preemptive priority scheduling algorithm.
 */
 
 import java.util.Collections;
 import java.util.List;
 
 public class Priority implements Algorithm {
    
    // the list of tasks we are going to schedule 
    private List<Task> queue;
    
    // Initialize the Priority Scheduler and execute the tasks 
    // Tasks are executed in order of their priotity with the higher number
    // having higher priority 
    public Priority(List<Task> queue) {
        this.queue = queue;
        // Sort the queue based on priority (highest priority first)
        Collections.sort(queue, (t1, t2) -> Integer.compare(t2.getPriority(), t1.getPriority()));
    }

    // Invoke the method of Priority Scheduler 
    @Override
    public void schedule() {
        while (!queue.isEmpty()) {
            // Pick the next task according to the priority 
            Task nextTask = pickNextTask();
            // Simulate running task using the CPU
            CPU.run(nextTask, nextTask.getBurst());
            // Print the message indicating when a task has been finished
            System.out.println("Task " + nextTask.getName() + " finished." + "\n") ;
            // Remove the completed task from the queue 
            queue.remove(nextTask);
        }
    }
    
    // Invoke picking the next task which is simply the first task in the sorted queue 
    @Override
    public Task pickNextTask() {
        // For Priority, simply pick the first task in the sorted queue
        return queue.get(0);
    }
}
 
