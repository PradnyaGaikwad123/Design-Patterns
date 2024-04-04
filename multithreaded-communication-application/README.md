
## Name: Pradnya Rajesh Gaikwad

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in primeService/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile primeService/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:
Instruction: To run the program please make sure that you are under the following folder multithreaded-communication-application and then use the build and run command, if you are in some other directory then it will give file not found exception.

####Command: ant -buildfile primeService/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:
Instruction: To run the program please make sure that you are under the following folder multithreaded-communication-application and then use the build and run command, if you are in some other directory then it will give file not found exception.

####Command: 
Server:
ant -buildfile primeService/src/build.xml run -Darg0=<port_no> -Darg1=<debug_value>

Client:
ant -buildfile primeService/src/build.xml run -Darg0=<port_no> -Darg1=<hostname> -Darg2=<debug_value>

## Replace <values> with real values.

Server:
$ ant -buildfile primeService/src/build.xml run -Darg0=2345 -Darg1=1

Client:
$ ant -buildfile primeService/src/build.xml run -Darg0=2345 -Darg1=128.226.XXX.XXX -Darg2=2

Note: We have used "1" for Server and "2" for Client as DEBUG_VALUE.

Note: Client takes hostname as IP address of the machine on which you run the client code - Command used to get machine's IP address is "hostname -I"
First machine IP address = 128.226.XXX.XXX
Second machine IP address = 128.226.YYY.YYY

Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------
## Description: 
Developed a program, using Java, to design a simple multi-threaded client-server code with Prime Number Check.


>>>>Choice of Data Structures:
1) ConcurrentHashMap

Purpose: The ConcurrentHashMap is used to store mappings between client names and lists of query numbers. It provides a thread-safe and efficient way to manage concurrent access to the underlying data structure.

Thread Safety: The ConcurrentHashMap is inherently thread-safe for concurrent read and write operations. It achieves thread safety through mechanisms like partitioning the data structure and using fine-grained locking.

Time Complexity:
put and get Operations: O(1) on average for individual operations. These operations are efficient and do not require locking the entire map.
Space Complexity: 
O(n), where n is the number of client names. The space complexity is proportional to the number of mappings stored in the map.

2) CopyOnWriteArrayList

Purpose: Each client's list of query numbers is represented by a CopyOnWriteArrayList. This data structure is chosen for scenarios where reads are more frequent than writes, as it allows for efficient iteration and retrieval without locks during read operations.

Thread Safety: The CopyOnWriteArrayList is thread-safe for reads.

Time Complexity:
add Operation: O(n), where n is the size of the list. The add operation involves copying the entire array to a new one.
get Operation: O(1) for individual element access. Reads are efficient, making this data structure suitable for scenarios where reads are more frequent.
Space Complexity: O(n), where n is the number of elements in the list. The space complexity is proportional to the number of query numbers associated with a specific client.

-----------------------------------------------------------------------
## Output Format: 

Terminal 1: (SERVER)
Server side text-based menu: 
[1] Client Name [print the name and query integer]
[2] All Client Queries [print all names and queries so far]
[3] Quit [quit the server]

Terminal 2: (CLIENT)
Client side text-based menu: 
[1] Set client name
[2] Enter number to query for prime
[3] What is the server response?
[4] Quit

-----------------------------------------------------------------------
## Working: 

Created the initial file structure, starting client and server, implemented server side menu, code refactor
implemented Client side menu, worker classes, handling multiple client connections, designing Thread safe data structure.

-----------------------------------------------------------------------

## References: 
https://www.geeksforgeeks.org/multithreaded-servers-in-java/
https://stackoverflow.com/questions/12588476/multithreading-socket-communication-client-server 

-----------------------------------------------------------------------

