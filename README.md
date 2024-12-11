Ticket System CLI

Overview

The Ticket System CLI is a command-line interface for configuring and simulating a ticket distribution system. 
Users can define system parameters, start the simulation, and observe interactions between vendors, customers, and a shared ticket pool.

Features

Configure the ticketing system with custom parameters.
Save and load configurations in JSON format.
Run a multi-threaded simulation with vendors adding tickets and customers retrieving them.
Log system activities to console and files for detailed tracking.
System Components

Classes

TicketSystemCLI
Entry point for the CLI application.
Handles user inputs and configuration.
Coordinates system startup and shutdown.

Configuration
Stores system settings such as total tickets, ticket release rate, customer retrieval rate, and maximum ticket capacity.
Supports JSON serialization and deserialization.

TicketPool
Manages ticket availability.
Provides synchronized methods for adding and removing tickets.

Vendor
Simulates ticket vendors releasing tickets into the pool.
Operates in its own thread.

Customer
Simulates customers retrieving tickets from the pool.
Operates in its own thread.

Logger
Logs messages to the console and a file for audit and debugging purposes.

Usage

Running the Application
Compile the project:
            javac *.java
Run the application:
            java TicketSystemCLI

CLI Workflow

Configuration:
Input parameters for total tickets, ticket release rate, customer retrieval rate, and maximum ticket capacity.
Save configuration to a JSON file.

Start Simulation:
Specify the number of vendors and customers.
Observe the simulation in real-time through console logs.

Stop Simulation:
Type stop to terminate the simulation.

Key Commands

start: Begins the simulation.
stop: Terminates the simulation.

Configuration Parameters

Total Tickets: Total number of tickets available for distribution.
Ticket Release Rate: Number of tickets a vendor releases per second.
Customer Retrieval Rate: Frequency at which customers attempt to retrieve tickets.
Maximum Ticket Capacity: Maximum number of tickets the pool can hold.

Logging

Logs are saved in the following files:
system_logs.txt: Detailed logs of system activities.
ticket_pool_log.txt: Logs specific to ticket pool operations.

Error Handling
Handles invalid user inputs with prompts for re-entry.
Ensures synchronization for thread safety in TicketPool.

Example

Welcome to the Ticket System Configuration
Total Number of Tickets: 10
Ticket Release Rate: 3
Customer Retrieval Rate : 3
Maximum Ticket Capacity: 50

Configuration Summary:
Total Tickets: 10
Ticket Release Rate: 3
Customer Retrieval Rate: 3
Maximum Ticket Capacity: 50
Confirm settings? (yes/no): no
Configuration canceled. Please restart the configuration process.
Do you want to start the program? (start/stop): start
System is running. Enter 'stop' to terminate.
Enter number of vendors: 2
Enter number of customers: 3
[2024-12-12T01:12:00.306544500] Vendor 2 added 2 tickets. Total tickets: 4
[2024-12-12T01:12:00.321434400] Customer 3 retrieved Ticket-1. Remaining tickets: 3
[2024-12-12T01:12:00.322182200] Customer 2 retrieved Ticket-2. Remaining tickets: 2
[2024-12-12T01:12:00.323454200] Customer 1 retrieved Ticket-3. Remaining tickets: 1
[2024-12-12T01:12:00.324049300] Vendor 1 added 2 tickets. Total tickets: 5
Customer 3 retrieved Ticket-1.
Customer 2 retrieved Ticket-2.
Customer 1 retrieved Ticket-3.
[2024-12-12T01:12:05.316148400] Vendor 2 added 2 tickets. Total tickets: 9
[2024-12-12T01:12:05.341813400] Vendor 1 is waiting as the pool is full. Current tickets: 9
[2024-12-12T01:12:06.325479] Customer 3 retrieved Ticket-4. Remaining tickets: 8
Customer 3 retrieved Ticket-4.
[2024-12-12T01:12:06.326482400] Vendor 1 added 2 tickets. Total tickets: 12
[2024-12-12T01:12:06.327657300] Customer 2 retrieved Ticket-2. Remaining tickets: 11
Customer 2 retrieved Ticket-2.
[2024-12-12T01:12:06.335818400] Customer 1 retrieved Ticket-3. Remaining tickets: 10
Customer 1 retrieved Ticket-3.
stop
System stopping...

Process finished with exit code 0

Dependencies
Gson library for JSON operations.

Future Enhancements

Add a graphical user interface (GUI).
Implement advanced logging and analytics.
Support for dynamic configuration changes during runtime.

License

This project is licensed under the MIT License.

