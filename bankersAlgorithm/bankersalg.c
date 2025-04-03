/*
 * Names : Michael Kim, Brandon Quach
 * Student ID : 025633788, 029133232
 * Class : CECS 326  
 * Date : 03/31/2024
 * Project 3 
 * 
 * bankersalg.c
 *
 * This program implements the banker’s algorithm
 * Customers request and release resources from the bank.
 * The banker will grant a request only if it leaves the system in a safe state.
 * A request that leaves the system in an unsafe state will be denied. 
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define NUMBER_OF_CUSTOMERS 5   // Maximum number of customers
#define NUMBER_OF_RESOURCES 4    // Maximum number of resources

int available[NUMBER_OF_RESOURCES];        // Available resources
int maximum[NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES];   // Maximum resources required by each customer
int allocation[NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES];    // Resources currently allocated to each customer
int need[NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES];      // Remaining needs of each customer

int num_res;   // Number of resource types

void init();   
bool request_resources(int customer_num, int request[]);   
void release_resources(int customer_num, int release[]);   
void display();   


// Function to initialize all arrays to 0
void init() {
    for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
        for (int j = 0; j < NUMBER_OF_RESOURCES; j++) {
            maximum[i][j] = 0;
            allocation[i][j] = 0;
            need[i][j] = 0;
        }
    }
}

// Function to handle resource request
bool request_resources(int customer_num, int request[]) {
    // Check if request is valid
    for (int i = 0; i < NUMBER_OF_RESOURCES; i++) {
        if (request[i] > need[customer_num][i] || request[i] > available[i]) {
            return false; // Request cannot be granted
        }
    }

    // Try allocating resources temporarily
    for (int i = 0; i < num_res; i++) {
        available[i] -= request[i];
        allocation[customer_num][i] += request[i];
        need[customer_num][i] -= request[i];
    }

    return true; // Request granted
}

// Function to release resources
void release_resources(int customer_num, int release[]) {
    // Check if release is valid
    for (int i = 0; i < num_res; i++) {
        if (release[i] > allocation[customer_num][i]) {
            printf("Error: Attempting to release more resources than allocated.\n");
            return;
        }
    }

    // Release resources
    for (int i = 0; i < num_res; i++) {
        available[i] += release[i];
        allocation[customer_num][i] -= release[i];
        need[customer_num][i] += release[i];
    }

    printf("Resources released.\n");
}

// Function to display current state
void display() {
    printf("\nAvailable resources:\n");
    for (int i = 0; i < num_res; i++) {
        printf("%d ", available[i]);
    }
    printf("\n");

    printf("\nMaximum resources:\n");
    for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
        for (int j = 0; j < num_res; j++) {
            printf("%d ", maximum[i][j]);
        }
        printf("\n");
    }

    printf("\nAllocated:\n");
    for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
        for (int j = 0; j < num_res; j++) {
            printf("%d ", allocation[i][j]);
        }
        printf("\n");
    }

    printf("\nNeed:\n");
    for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
        for (int j = 0; j < num_res; j++) {
            printf("%d ", need[i][j]);
        }
        printf("\n");
    }
}

int main(int argc, char *argv[]) {
    if (argc < 2) {
        printf("Usage: %s <av res>\n", argv[0]);
        return 1;
    }

    // Initialize available resources from command line arguments
    num_res = argc - 1;
    for (int i = 0; i < num_res; i++) {
        available[i] = atoi(argv[i + 1]);
    }

    // Read maximum resources from input file
    FILE *file = fopen("input.txt", "r");
    if (file == NULL) {
        printf("Error opening file.\n");
        return 1;
    }

    int num_cust = 0;
    // Read each line from the file to get maximum resource allocation for each customer
    while (!feof(file) && num_cust < NUMBER_OF_CUSTOMERS) {
        for (int i = 0; i < num_res; i++) {
            fscanf(file, "%d,", &maximum[num_cust][i]);
            allocation[num_cust][i] = 0;
            need[num_cust][i] = maximum[num_cust][i];
        }
        num_cust++;
    }
    fclose(file);

    char command[3];   // Command entered by user
    int customers;  // Customer number
    int request[NUMBER_OF_RESOURCES];  // Resource request
    int releases[NUMBER_OF_RESOURCES];  // Resource release

    // Interactive menu for user input
    while (true) {
        printf("\nEnter command (RQ, RL, *): ");
        scanf("%2s", command);   // Read command as a string of maximum 2 characters

        // Process the command entered by the user
        if (strcmp(command, "RQ") == 0) { // Compare cmd with "RQ"
            scanf("%d", &customers);
            for (int i = 0; i < num_res; i++) {
                scanf("%d", &request[i]);
            }
            // Call function to handle resource request
            if (request_resources(customers, request)) {
                printf("Request granted.\n");
            } else {
                printf("Request denied.\n");
            }
        } else if (strcmp(command, "RL") == 0) { // Compare cmd with "RL"
            scanf("%d", &customers);
            for (int i = 0; i < num_res; i++) {
                scanf("%d", &releases[i]);
            }
            // Call function to handle resource release
            release_resources(customers, releases);
        } else if (strcmp(command, "*") == 0) { // Compare cmd with "*"
            // Display current state
            display();
        } else {
            printf("Invalid command. Please enter RQ, RL, or *.\n");
        }
    }

    return 0;
}

