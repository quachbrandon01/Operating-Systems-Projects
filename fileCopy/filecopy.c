/*
filecopy.c

This program will open a file and write its contents to a pipe

Michael Kim and Brandon Quach
*/
#include <stdio.h>
#include <fcntl.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

#define BUFFER_SIZE 25

int main(int argc, char* argv[]) 
{
    int file[2];
    pid_t childID;
    char readBuffer[BUFFER_SIZE];
    pipe(file);

    //Error when user inputs more than 2 parameters
    if( argc != 3 ) 
    {
        printf("ERROR: Need exactly 2 parameters.\n");
        exit(1);
    }

    //Open source file
    int sourceFile = open(argv[1], 0);
    int destFile = open(argv[2], O_RDWR|O_CREAT|O_APPEND, 0666);
    //Display filenames by dereferencing
    const char *filename1 = argv[1];
    const char *filename2 = argv[2];

    //Error when the source file doesn't exist
    if (sourceFile == -1 || destFile == -1) 
    {
        printf("Error: Unable to open source file \'%s",filename1);
        printf("\'");
        exit(1);
    }

    //Fork a Child Process
    childID = fork();

    if (childID == 0) 
    {
        close(file[1]);
        while (read(file[0], readBuffer, sizeof(readBuffer)) > 0) 
        {
            write(destFile, readBuffer, strlen(readBuffer));
        }
        //close files
        close(file[0]);
        close(destFile);
    }

    //Write to destination file
    else 
    {
        close(file[0]);
        while (read(sourceFile, readBuffer, sizeof(readBuffer)) > 0) 
        {
            write(file[1], readBuffer, sizeof(readBuffer));
            memset(readBuffer, 0, BUFFER_SIZE);
        }
        close(file[1]);
        close(sourceFile);
        wait(NULL);
        //File copy successfully status message with file name
        printf("File successfully copied from \'%s",filename1);
        printf("\' to \'%s",filename2);
        printf("\'");
    }
}