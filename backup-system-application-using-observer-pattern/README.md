
## Name: Pradnya Rajesh Gaikwad

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in backupSystem/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile backupSystem/src/build.xml clean

Description: It cleans up all the .class files that were generated when the
code is compiled.

-----------------------------------------------------------------------
## Instruction to compile:
To compile this code please navigate to the folder /backup-system-application-using-observer-pattern and the run the following ant command.

####Command: ant -buildfile backupSystem/src/build.xml all

####Command usage:
pgaikwa2@remote05:~/backup-system-application-using-observer-pattern$ ant -buildfile backupSystem/src/build.xml all

Description: Compiles the code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile backupSystem/src/build.xml run -Darg0=bstInput.txt -Darg1=bstOutput.txt -Darg2=errorLog.txt -Darg3=2 -Darg4=1

Commandline arguments explaination:
-Darg0: Name of the input file
-Darg1: Name of the file to which output will be written
-Darg2: Name of the file to which errors/exceptions will be written (if any)
-Dargs3=2: DEBUG_LEVEL is set to 2, which corresponds to DebugLevel.TREE_BUILDER. I have used this
          logging level to print all the output related to trees required for this assignment.
-Dargs4=1: UPDATE_VALUE is set to 1, which will update the BST nodes (B-Number) with value 1. (Execpt for
          backup3 tree where the update depends on whether the value of BNumber, after the update, is a prime number.)

Note:   Before using the above command to run the program , it is required to have the input file 'bstInput.txt'
        in the folder: /backup-system-application-using-observer-pattern/backupSystem. 
        The file path should look like: /backup-system-application-using-observer-pattern/backupSystem/bstInput.txt
        If the file does not exist on the mentioned path, It will throw a FileNotFound Exception.

-----------------------------------------------------------------------
## Description:
In this assignment, I have used the following data structures to manage observers and filters
for updating the state of the nodes.

1. HashMap ('filterObserverMap'):
    The filterObserverMap is a HashMap that associates filters with lists of observers. It acts as a registry for registering and managing observers based on their associated filters. Filters determine whether an observer should receive updates when changes occur in the main node.
    
    Time Complexity: Insertion (registerObserver), Deletion (unregisterObserver), Search (Lookup): O(1) on average, as it involves key-value pair handling.

    Space Complexity: O(k + n)
                        k is the number of unique filters.
                        n is the total number of registered observers.

2. ArrayList ('observers'):
    This is a list of observers associated with a specific filter. Each entry in the filterObserverMap (key-value pair) contains a filter (key) and a list of observers (value) that are registered to receive updates when the filter criteria match.

    Time Complexity: ArrayLists offer O(1) average-case time complexity for insertions and O(n) for deletions and serach.

    Space Complexity: Space Complexity: O(n), n is the number of observers associated with a specific filter.

-----------------------------------------------------------------------


