import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final int maxCapacity;
    private final Queue<String> tickets = new LinkedList<>();


    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    // Add tickets to the pool
    public synchronized void addTickets(int vendorId, int ticketCount) {
        while (tickets.size() + ticketCount > maxCapacity) {
            try {
                System.out.println("Vendor " + vendorId + " is waiting as the pool is full.");
                wait(); // Wait until there's room in the pool
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor " + vendorId + " interrupted.");
                return;
            }
        }


        for (int i = 0; i < ticketCount; i++) {
            tickets.add("Ticket-" + (tickets.size() + 1));
        }

        System.out.println("Vendor " + vendorId + " added " + ticketCount + " tickets. Total tickets: " + tickets.size());
        notifyAll(); // Notify waiting customers that tickets are available
    }

    // Remove a ticket from the pool
    public synchronized String removeTicket(int customerId) {
        while (tickets.isEmpty()) {
            try {
                System.out.println("Customer " + customerId + " is waiting as there are no tickets available.");
                wait(); // Wait until tickets are added
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer " + customerId + " interrupted.");
                return null;
            }
        }


        String ticket = tickets.poll(); // Retrieve and remove the ticket
        System.out.println("Customer " + customerId + " retrieved " + ticket + ". Remaining tickets: " + tickets.size());
        notifyAll(); // Notify waiting vendors that space is available
        return ticket;
    }

}
