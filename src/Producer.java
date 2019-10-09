/**
 * Producer for lab.
 *
 * @author Leroy Valencia
 */

import java.util.Random;
import java.util.concurrent.BlockingQueue;

class Producer extends Thread {

    private BlockingQueue<String> outputQueue;
    private final int minCookTime = 1000; // 1 second
    private final int maxCookTime = 10000; // 10 seconds
    private int serial = 0;

    /**
     * Constructor
     *
     * @param q the queue shared with the consumer
     */
    public Producer(BlockingQueue<String> q) {
        super("Burger cook");
        this.outputQueue = q;
    }

    /**
     * Do the production work.
     */
    public void run() {
        Random r = new Random();
        int cookTime;
        float cookSec;
        String burger;
        String burgerID;
        BurgerType ranType;

        while (true) {
            //random burger type selector
            ranType = BurgerType.values()[new Random().nextInt(BurgerType.values().length)];
            System.out.println(ranType.toString());

            cookTime = minCookTime + r.nextInt(maxCookTime - minCookTime);
            cookSec = cookTime / 1000.0f;
            burgerID = Integer.toUnsignedString(r.nextInt());
            System.out.printf("%s about to cook burger %s for %.2f sec\n", getName(), burgerID, cookSec);
            burger = String.format("id %s", burgerID);
            try {
                Thread.sleep(cookTime);
                System.out.printf("%s cooked burger %s\n", getName(), burgerID);
                outputQueue.put(burger);
            } catch (InterruptedException e) {
                System.err.println("Producer got an InterruptedException; message: "
                    + e.getMessage());
            }
        }
    }
}