public class Vendor implements Runnable {
    private final int vendorId;
    private final int ticketsPerRelease;
    private final TicketPool ticketPool;
    private final Logger logger;

    public Vendor(int vendorId, int ticketsPerRelease, TicketPool ticketPool, Logger logger) {
        this.vendorId = vendorId;
        this.ticketsPerRelease = ticketsPerRelease;
        this.ticketPool = ticketPool;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Add tickets to the pool
                ticketPool.addTickets(vendorId, ticketsPerRelease);

                // Log the action
                logger.log("Vendor " + vendorId + " added " + ticketsPerRelease + " tickets.");

                if (Thread.currentThread().isInterrupted()) break;

                // Wait before adding tickets again
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.log("Vendor " + vendorId + " interrupted and stopped.");
        } catch (Exception e) {
            logger.log("Vendor " + vendorId + " encountered an error: " + e.getMessage());
        }
    }
}





