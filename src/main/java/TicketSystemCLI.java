import java.util.Scanner;

public class TicketSystemCLI {

    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public void configureSystem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Ticket System Configuration");

        // Set total tickets
        totalTickets = getValidatedInput(scanner, "Total Number of Tickets (totalTickets)");

        // Set ticket release rate
        ticketReleaseRate = getValidatedInput(scanner, "Ticket Release Rate (ticketReleaseRate)");

        // Set customer retrieval rate
        customerRetrievalRate = getValidatedInput(scanner, "Customer Retrieval Rate (customerRetrievalRate)");

        // Set max ticket capacity
        maxTicketCapacity = getValidatedInput(scanner, "Maximum Ticket Capacity (maxTicketCapacity)");

        // Check if max capacity is logical
        while (totalTickets > maxTicketCapacity) {
            System.out.println("Error: Total tickets cannot exceed maximum ticket capacity. Please re-enter values.");
            totalTickets = getValidatedInput(scanner, "Total Number of Tickets (totalTickets)");
            maxTicketCapacity = getValidatedInput(scanner, "Maximum Ticket Capacity (maxTicketCapacity)");
        }

        // Display configuration for confirmation
        System.out.println("\nConfiguration Summary:");
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Ticket Release Rate: " + ticketReleaseRate);
        System.out.println("Customer Retrieval Rate: " + customerRetrievalRate);
        System.out.println("Maximum Ticket Capacity: " + maxTicketCapacity);

        System.out.print("Confirm settings? (yes/no): ");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Configuration saved. System starting...");
            // Proceed to start the system with these settings
        } else {
            System.out.println("Configuration canceled. Please restart the configuration process.");
        }

        scanner.close();
    }

    private int getValidatedInput(Scanner scanner, String prompt) {
        int input;
        while (true) {
            System.out.print(prompt + ": ");
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input > 0) {
                    return input;
                } else {
                    System.out.println("Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    public static void main(String[] args) {
        TicketSystemCLI config = new TicketSystemCLI();
        config.configureSystem();
    }
}
