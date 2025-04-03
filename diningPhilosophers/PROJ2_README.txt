Part 1 - Here is a solution to the Dining-Philosophers Problem using monitors

In our example, when the philosphers are thinking or eating the console will display 
"Philospher # is eating" or "Philosopher # is thinking"

To compile and run this progam(DiningPhilosphers, DiningServer, DiningServerImpl, Philosopher) 
code follow these steps:

1) Save the DiningPhilosphers.java, DiningServer.java, DiningServerImpl.java, and Philosopher.java 
files somewhere where you can access them

2) Have one command prompt open 

3) Navigate to the directory where the files were saved. An example would be:
	cd Desktop 

4) Using one of the command prompt compile all the files by typing:
	javac DiningPhilosophers.java
	javac DiningServer.java
	javac DiningServerImpl.java
	javac Philosopher.java

5) Since they are all complied, now compile the main class file by typing:
	java DiningPhilosphers

6) After that the messages mentioned at the start should appear
on the command prompt. They will go on foreverm, so hit ctrl c to kill the program.