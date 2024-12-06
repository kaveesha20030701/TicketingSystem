public class Customer implements Runnable {
    private final int customerId;
    private final int retrievalInterval;
    private final TicketPool ticketPool;
    private final Logger logger;

    public Customer(int customerId, int retrievalInterval, TicketPool ticketPool, Logger logger) {
        this.customerId = customerId;
        this.retrievalInterval = retrievalInterval;
        this.ticketPool = ticketPool;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Attempt to retrieve a ticket
                String ticket = ticketPool.removeTicket(customerId);

                // Log the retrieval action
                if (ticket != null) {
                    logger.log("Customer " + customerId + " retrieved " + ticket + ".");
                } else {
                    logger.log("Customer " + customerId + " found no tickets available.");
                }

                // Wait before trying to retrieve another ticket
                Thread.sleep(retrievalInterval);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.log("Customer " + customerId + " interrupted and stopped.");
        } catch (Exception e) {
            logger.log("Customer " + customerId + " encountered an error: " + e.getMessage());
        }
    }
}
