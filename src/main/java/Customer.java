public class Customer implements Runnable {
    private String customerName;
    private TicketPool ticketPool;
    private int ticketsToRetrieve;

    // Constructor
    public Customer(String customerName, TicketPool ticketPool, int ticketsToRetrieve) {
        this.customerName = customerName;
        this.ticketPool = ticketPool;
        this.ticketsToRetrieve = ticketsToRetrieve;
    }

    @Override
    public void run() {
        System.out.println(customerName + " started retrieving tickets.");
        try {
            synchronized (ticketPool) {
                if (ticketPool.getAvailableTickets() >= ticketsToRetrieve) {
                    ticketPool.reduceTickets(ticketsToRetrieve);
                    System.out.println(customerName + " successfully retrieved " + ticketsToRetrieve + " tickets. Remaining tickets: " + ticketPool.getAvailableTickets());
                } else {
                    System.out.println(customerName + " failed to retrieve tickets. Not enough tickets available.");
                }
            }
        } catch (Exception e) {
            System.out.println(customerName + " encountered an error: " + e.getMessage());
        }
        System.out.println(customerName + " finished ticket retrieval.");
    }
}
