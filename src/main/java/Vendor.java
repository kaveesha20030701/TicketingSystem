public class Vendor implements Runnable {
    private String vendorName;
    private TicketPool ticketPool;
    private int ticketsToAdd;

    // Constructor
    public Vendor(String vendorName, TicketPool ticketPool, int ticketsToAdd) {
        this.vendorName = vendorName;
        this.ticketPool = ticketPool;
        this.ticketsToAdd = ticketsToAdd;
    }

    @Override
    public void run() {
        System.out.println(vendorName + " started releasing tickets.");
        synchronized (ticketPool) {
            ticketPool.addTickets(ticketsToAdd);
            System.out.println(vendorName + " added " + ticketsToAdd + " tickets. Total tickets: " + ticketPool.getAvailableTickets());
        }
        System.out.println(vendorName + " finished releasing tickets.");
    }
}
