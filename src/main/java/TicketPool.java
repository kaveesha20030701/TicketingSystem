public class TicketPool {
    private int availableTickets;

    // Constructor
    public TicketPool(int initialTickets) {
        this.availableTickets = initialTickets;
    }

    // Synchronized addTickets() to make it thread-safe
    public synchronized void addTickets(int ticketsToAdd) {
        if (ticketsToAdd > 0) {
            availableTickets += ticketsToAdd;
            System.out.println(Thread.currentThread().getName() + " added " + ticketsToAdd + " tickets. Total tickets: " + availableTickets);
        } else {
            System.out.println(Thread.currentThread().getName() + " tried to add invalid tickets.");
        }
    }
    public synchronized void reduceTickets(int ticketsToRetrieve) {
        if (ticketsToRetrieve > 0 && availableTickets >= ticketsToRetrieve) {
            availableTickets -= ticketsToRetrieve;
        }
    }

    // Method to get the current ticket count
    public synchronized int getAvailableTickets() {
        return availableTickets;
    }
}
