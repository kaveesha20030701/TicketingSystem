import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class TicketSystemCLI {

    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    private static final String JSON_FILE_PATH = "configuration.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void configureSystem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Ticket System Configuration");

        // Set total tickets
        totalTickets = getValidatedInput(scanner, "Total Number of Tickets");

        // Set ticket release rate
        ticketReleaseRate = getValidatedInput(scanner, "Ticket Release Rate");

        // Set customer retrieval rate
        customerRetrievalRate = getValidatedInput(scanner, "Customer Retrieval Rate ");

        // Set max ticket capacity
        maxTicketCapacity = getValidatedInput(scanner, "Maximum Ticket Capacity");

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

    public static void saveConfigurationAsJson(Configuration config) {
        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            gson.toJson(config, writer);
            System.out.println("Configuration saved as JSON.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configuration loadConfigurationFromJson() {
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            System.out.println("No previous configuration found, starting fresh.");
            return null;
        }
    }


    public static void main(String[] args) {
        TicketSystemCLI config = new TicketSystemCLI();
        config.configureSystem();

        Configuration loadedConfig = loadConfigurationFromJson();
        if (loadedConfig != null) {
            System.out.println("Loaded Configuration:");
            System.out.println("Total Tickets: " + loadedConfig.getTotalTickets());
            System.out.println("Ticket Release Rate: " + loadedConfig.getTicketReleaseRate());
            System.out.println("Customer Retrieval Rate: " + loadedConfig.getCustomerRetrievalRate());
            System.out.println("Maximum Ticket Capacity: " + loadedConfig.getMaxTicketCapacity());
        }
    }
}
